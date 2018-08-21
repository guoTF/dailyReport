package com.seda.dailyReport.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * word文档下载工具类
 * @author 郭腾飞 20180613
 *
 */
public class WordUtil {
	private static Configuration configuration = null;
	private static Map<String, Template> allTemplate = null;

	static {
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		try {
			configuration.setDirectoryForTemplateLoading(new File("/opt/xlr/templates"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		allTemplate = new HashMap<String, Template>();
		try {
			// 这里要加上你所有要初始化的.ftl文档，下面createDoc方法要引用此处的key才能找到模版
			allTemplate.put("test", configuration.getTemplate("HT.ftl"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private WordUtil() {
	}

	public static File createDoc(Map<?, ?> dataMap, String type) {
		String name = type + (int) (Math.random() * 100000) + ".doc";
//		File f = new File(name);
		File f = new File(name);
		// “test”为上面初始化的key值
		Template t = allTemplate.get("test");
		try {
			// 这个地方不能使用FileWriter因为需要指定编码类型否则声场的word文档会因为有无法识别的编码而无法打开
			Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(dataMap, w);
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return f;
	}
}
