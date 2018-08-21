package com.seda.dailyReport.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seda.dailyReport.model.vo.UserExcelVo;
import com.seda.dailyReport.service.util.UtilService;
import com.seda.dailyReport.util.ExportExcel;
import com.seda.dailyReport.util.WordUtil;

/**
 * 公用controller类
 * @author 郭腾飞 20180625
 *
 */
@Controller
@RequestMapping("/util")
public class UtilController {

	@Resource
	private UtilService utilService;
	
	/**
	 * 导出Excel
	 * @param request
	 * @param response
	 * @param accountTradeFlowVO
	 * @throws Exception
	 */
	@RequestMapping(value="/export")
	public void export(HttpServletRequest request, HttpServletResponse response, UserExcelVo userExcelVo) throws Exception {  
		List<UserExcelVo> excelVoList = this.utilService.getUserExcelVo(userExcelVo);
        ExportExcel<UserExcelVo> ee= new ExportExcel<UserExcelVo>();  
        String[] headers = { "序号", "姓名", "年龄", "电话"};  
        String fileName = "用户表";  
        ee.exportExcel(headers,excelVoList,fileName,response);  
    }
	
	/**
	 * 合同下载
	 * 
	 * @param bidId
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadFile", method = { RequestMethod.GET })
	public String downloadFile(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> dataMap = this.utilService.getDataMap(id);
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			// 调用工具类WordGenerator的createDoc方法生成Word文档
			file = WordUtil.createDoc(dataMap, "借款合同电子档");
			fin = new FileInputStream(file);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/msword");
			// 设置浏览器以下载的方式处理该文件默认名为resume.doc
			String fileName = "借款合同.doc";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器
			} else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
			} else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");// 谷歌
			}
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

			out = response.getOutputStream();
			byte[] buffer = new byte[1024]; // 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			while ((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} finally {
			if (fin != null)
				fin.close();
			if (out != null)
				out.close();
			if (file != null)
				file.delete(); // 删除临时文件
		}
		return null;
	}
}
