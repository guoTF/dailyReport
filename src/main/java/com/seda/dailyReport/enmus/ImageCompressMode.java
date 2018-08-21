package com.seda.dailyReport.enmus;

public enum ImageCompressMode {

	/**
	 * 按比例压缩
	 */
	SCALE("SCALE", "按比例压缩"),
	/**
	 * 按尺寸压缩
	 */
	SIZE("SIZE", "按尺寸压缩");

	private ImageCompressMode(String code, String value) {
		this.code = code;
		this.value = value;
	}

	private String code;
	private String value;

	public String getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static ImageCompressMode fromCode(String code) {
		for (ImageCompressMode enumItem : ImageCompressMode.values()) {
			if (enumItem.getCode().equals(code)) {
				return enumItem;
			}
		}
		return null;
	}
}