package com.seda.dailyReport.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.seda.dailyReport.config.UploadImgConfig.ImageSize;
import com.seda.dailyReport.enmus.ImageCompressMode;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.name.Rename;

public class UploadUtil {

	public static List<File> compressImg(File srcFile, File destDir, String outputFormat, Float outputQuality,
			ImageCompressMode imgCompressMode, Float scale, ImageSize imageSize, boolean isForceSize)
			throws IOException {
		Builder<? extends File> thumbBuilder = Thumbnails.of(srcFile);
		if (StringUtils.isNotBlank(outputFormat)) {
			thumbBuilder.outputFormat(outputFormat);
		}
		if (outputQuality != null) {
			thumbBuilder.outputQuality(outputQuality);
		}
		switch (imgCompressMode) {
		case SCALE:
			thumbBuilder.scale(scale);
			break;
		case SIZE:
			if (!isForceSize) {
				thumbBuilder.size(imageSize.getWidth(), imageSize.getHeight());
			} else {
				thumbBuilder.forceSize(imageSize.getWidth(), imageSize.getHeight());
			}
			break;
		}
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		return thumbBuilder.asFiles(destDir, Rename.SUFFIX_DOT_THUMBNAIL);
	}

	public static String getNewFilename(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		String filename = UUID.randomUUID().toString().replace("-", "") + "."
				+ FilenameUtils.getExtension(originalFilename);
		return filename;
	}

	public static boolean isExtension(String filename, String[] extensions) {
		if (filename == null) {
			return false;
		}
		if (extensions == null || extensions.length == 0) {
			return (FilenameUtils.indexOfExtension(filename) == -1);
		}
		String fileExt = FilenameUtils.getExtension(filename);
		for (int i = 0; i < extensions.length; i++) {
			if (fileExt.toLowerCase().equals(extensions[i].toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
