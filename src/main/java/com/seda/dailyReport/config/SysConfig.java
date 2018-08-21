package com.seda.dailyReport.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("sysConfig")
public class SysConfig {

	@Value("${filePathAESKey}")
	private String filePathAESKey;

	public String getFilePathAESKey() {
		return filePathAESKey;
	}

	public void setFilePathAESKey(String filePathAESKey) {
		this.filePathAESKey = filePathAESKey;
	}
}
