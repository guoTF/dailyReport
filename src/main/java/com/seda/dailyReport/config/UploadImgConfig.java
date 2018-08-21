package com.seda.dailyReport.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.seda.dailyReport.enmus.ImageCompressMode;

@Component("uploadImgConfig")
public class UploadImgConfig {

	@Value("${upload.img.isCompress}")
	private boolean isCompress;

	@Value("${upload.img.isThumb}")
	private boolean isThumb;

	@Value("${upload.img.compressMode}")
	private String compressMode;

	@Value("${upload.img.compressScale}")
	private Float compressScale;

	@Value("${upload.img.thumbScale}")
	private Float thumbScale;

	@Value("${upload.img.compressSize}")
	private String compressSize;

	@Value("${upload.img.thumbSize}")
	private String thumbSize;

	@Value("${upload.img.compressIsFixedSize}")
	private boolean compressIsFixedSize;

	@Value("${upload.img.compressOutputFormat}")
	private String compressOutputFormat;

	@Value("${upload.img.compressOutputQuality}")
	private Float compressOutputQuality;

	@Value("${upload.img.path}")
	private String path;

	@Value("${upload.img.compressPath}")
	private String compressPath;

	@Value("${upload.img.thumbPath}")
	private String thumbPath;

	@Value("${upload.img.extensions}")
	private String extensions;

	@Value("${upload.img.maxSize}")
	private Integer maxSize;

	public class ImageSize {
		private int width;
		private int height;

		public ImageSize() {
			super();
		}

		public ImageSize(int width, int height) {
			this.width = width;
			this.height = height;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public void setHeight(int height) {
			this.height = height;
		}
	}

	public boolean isCompress() {
		return isCompress;
	}

	public boolean isThumb() {
		return isThumb;
	}

	public ImageCompressMode getCompressMode() {
		return ImageCompressMode.fromCode(compressMode);
	}

	public Float getCompressScale() {
		return compressScale;
	}

	public Float getThumbScale() {
		return thumbScale;
	}

	public ImageSize getCompressSize() {
		if (StringUtils.isBlank(compressSize)) {
			return null;
		}
		String[] size = compressSize.split("[*]");
		return new ImageSize(Integer.valueOf(size[0]), Integer.valueOf(size[1]));
	}

	public ImageSize getThumbSize() {
		if (StringUtils.isBlank(thumbSize)) {
			return null;
		}
		String[] size = thumbSize.split("[*]");
		return new ImageSize(Integer.valueOf(size[0]), Integer.valueOf(size[1]));
	}

	public boolean isCompressIsFixedSize() {
		return compressIsFixedSize;
	}

	public String getCompressOutputFormat() {
		return compressOutputFormat;
	}

	public Float getCompressOutputQuality() {
		return compressOutputQuality;
	}

	public String getPath() {
		return path;
	}

	public String getCompressPath() {
		return compressPath;
	}

	public String getThumbPath() {
		return thumbPath;
	}

	public String[] getExtensions() {
		return StringUtils.isBlank(extensions) ? null : extensions.split(",");
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setCompress(boolean isCompress) {
		this.isCompress = isCompress;
	}

	public void setThumb(boolean isThumb) {
		this.isThumb = isThumb;
	}

	public void setCompressMode(String compressMode) {
		this.compressMode = compressMode;
	}

	public void setCompressScale(Float compressScale) {
		this.compressScale = compressScale;
	}

	public void setThumbScale(Float thumbScale) {
		this.thumbScale = thumbScale;
	}

	public void setCompressSize(String compressSize) {
		this.compressSize = compressSize;
	}

	public void setThumbSize(String thumbSize) {
		this.thumbSize = thumbSize;
	}

	public void setCompressIsFixedSize(boolean compressIsFixedSize) {
		this.compressIsFixedSize = compressIsFixedSize;
	}

	public void setCompressOutputFormat(String compressOutputFormat) {
		this.compressOutputFormat = compressOutputFormat;
	}

	public void setCompressOutputQuality(Float compressOutputQuality) {
		this.compressOutputQuality = compressOutputQuality;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setCompressPath(String compressPath) {
		this.compressPath = compressPath;
	}

	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}

	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
}
