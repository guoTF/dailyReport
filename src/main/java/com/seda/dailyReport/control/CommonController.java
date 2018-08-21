package com.seda.dailyReport.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.seda.dailyReport.config.UploadImgConfig;
import com.seda.dailyReport.model.dto.OperationDto;
import com.seda.dailyReport.util.CipherUtils;
import com.seda.dailyReport.util.DateUtils;
import com.seda.dailyReport.util.DownloadUtils;
import com.seda.dailyReport.util.UploadUtil;
import com.seda.dailyReport.util.ValidateCodeUtils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 上传/下载公共controller
 * @author admin
 *
 */
@Controller
@RequestMapping("common")
public class CommonController {

    private static Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Resource(name = "uploadImgConfig")
    private UploadImgConfig uploadImgConfig;

    @RequestMapping("uploadImg")
    @ResponseBody
    public OperationDto uploadImg(HttpServletRequest request, HttpServletResponse response, Model model)
            throws Exception {

        OperationDto resultDTO = new OperationDto();
        MultipartHttpServletRequest multipartHttpservletRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartHttpservletRequest.getFileMap();
        if (fileMap != null && !fileMap.isEmpty()) {
            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                    if (!UploadUtil
                            .isExtension(entry.getValue().getOriginalFilename(), uploadImgConfig.getExtensions())) {
                        resultDTO.fail(
                                "",
                                "仅支持" + Arrays.toString(uploadImgConfig.getExtensions())
                                        + uploadImgConfig.getExtensions().length + "种图片格式");
                        return resultDTO;
                    }
                    if (entry.getValue().getSize() > 1024 * 1024 * uploadImgConfig.getMaxSize()) {
                        resultDTO.fail("", "文件大小不允许超过" + uploadImgConfig.getMaxSize() + "M");
                        return resultDTO;
                    }
                }
            }
            //读取水印图片
            BufferedImage watermarkImage = ImageIO.read(new File(request.getRealPath("/static/img/watermark.png")));
            Map<String, String> dataMap = new HashMap<String, String>();
            resultDTO.success(dataMap);
            for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                    String filename = UploadUtil.getNewFilename(entry.getValue());
                    File file = new File(uploadImgConfig.getPath() + File.separator
                            + DateUtils.format(new Date(), "yyyyMMdd") + File.separator + filename);
                    FileUtils.writeByteArrayToFile(file, entry.getValue().getBytes());

                    Thumbnails.of(file).scale(1).watermark(Positions.CENTER, watermarkImage, 0.5f).toFile(file);    //原图增加水印

                    dataMap.put(entry.getKey().replace("Upload", ""),
                            CipherUtils.encodeFilePath(file.getAbsolutePath()));

                    // 生成压缩图
                    if (uploadImgConfig.isCompress()) {
                        File destDir = new File(uploadImgConfig.getCompressPath() + File.separator
                                + DateUtils.format(new Date(), "yyyyMMdd"));
                        List<File> destFileList = UploadUtil.compressImg(file, destDir,
                                uploadImgConfig.getCompressOutputFormat(), uploadImgConfig.getCompressOutputQuality(),
                                uploadImgConfig.getCompressMode(), uploadImgConfig.getCompressScale(),
                                uploadImgConfig.getCompressSize(), uploadImgConfig.isCompressIsFixedSize());
                        for (File destFile : destFileList) {
                            dataMap.put(entry.getKey().replace("Upload", "") + "Zip",
                                    CipherUtils.encodeFilePath(destFile.getAbsolutePath()));
                        }
                    }

                    // 生成缩略图
                    if (uploadImgConfig.isThumb()) {
                        File destDir = new File(uploadImgConfig.getThumbPath() + File.separator
                                + DateUtils.format(new Date(), "yyyyMMdd"));
                        List<File> destFileList = UploadUtil.compressImg(file, destDir,
                                uploadImgConfig.getCompressOutputFormat(), uploadImgConfig.getCompressOutputQuality(),
                                uploadImgConfig.getCompressMode(), uploadImgConfig.getThumbScale(),
                                uploadImgConfig.getThumbSize(), uploadImgConfig.isCompressIsFixedSize());
                        for (File destFile : destFileList) {
                            dataMap.put(entry.getKey().replace("Upload", "") + "Thum",
                                    CipherUtils.encodeFilePath(destFile.getAbsolutePath()));
                        }
                    }
                }
            }
        }
        return resultDTO;
    }

    @RequestMapping("showImg")
    public String viewFile(@RequestParam(value = "f", required = true) String filePath, HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        OutputStream os = response.getOutputStream();
        if (StringUtils.isNotBlank(filePath) && !"null".equals(filePath)) {
            String decryptFilePath = CipherUtils.decryptFilePath(filePath);
            logger.info("decryptFilePath:" + decryptFilePath);
            File file = new File(decryptFilePath);
            if (file != null && file.exists()) {
                response.setContentType("image/png");
                Thumbnails.of(file).scale(1).toOutputStream(os);
            }
        }
        return null;
    }

    @RequestMapping("file/download")
    public String downloadFile(@RequestParam(value = "f", required = true) String filePath, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        if (StringUtils.isNotBlank(filePath) && !"null".equals(filePath)) {
            String decryptFilePath = CipherUtils.decryptFilePath(filePath);
            logger.info("decryptFilePath:" + decryptFilePath);
            File file = new File(decryptFilePath);
            if (file != null && file.exists()) {
                DownloadUtils.downloadFile(file.getParent(), file.getName(), request, response);
            } else {
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.getWriter().write("文件不存在或已删除！");
                return null;
            }
        } else {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.getWriter().write("文件路径不能为空！");
            return null;
        }
        return null;
    }

    @RequestMapping("getImgCode")
    public void getImgCode(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        ValidateCodeUtils.getValidateCode(request, response);
    }
}
