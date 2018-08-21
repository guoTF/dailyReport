package com.seda.dailyReport.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.seda.dailyReport.model.LoginUser;

public class GenerateLinkUtils {
	private static final String CHECK_CODE = "checkCode";

	public static String generateActivateLink(LoginUser user) {

		return "http://localhost:8091/email/activate?id=" + user.getId() + "&" + CHECK_CODE + "="
				+ generateCheckcode(user);
	}

	/**
	 * 生成校验码，用户名+UUID唯一标识符，为安全把他们加密发送
	 * 
	 * @param user
	 * @return
	 */
	private static String generateCheckcode(LoginUser user) {
		String userName = user.getUserName();
		String randomCode = user.getCodeUrl();
		return md5(userName + ":" + randomCode);
	}

	private static String md5(String string) {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("md5");// 返回实现指定摘要算法的 MessageDigest
													// 对象。
			md.update(string.getBytes());// 使用指定的 byte 数组更新摘要。
			byte[] md5Bytes = md.digest();// 通过执行诸如填充之类的最终操作完成哈希计算。在调用此方法之后，摘要被重置。
			return bytes2Hex(md5Bytes);// 本信息摘要

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("md5这里出错了");
		}
		return null;
	}

	// 二行制转字符串
	private static String bytes2Hex(byte[] byteArray) {

		StringBuffer strBuf = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (byteArray[i] >= 0 && byteArray[i] < 16) {
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}

	public static boolean verifyCheckcode(LoginUser user, String checkCode) {

		boolean flag = generateCheckcode(user).equals(checkCode);

		return flag;
	}

	/**
	 * 补充： 为了显示一个byte型的单字节十六进制(两位十六进制表示)的编码，请使用： Integer.toHexString((byteVar &
	 * 0x000000FF) | 0xFFFFFF00).substring(6); byteVar &
	 * 0x000000FF的作用是，如果byteVar 是负数，则会清除前面24个零，正的byte整型不受影响。 (...) |
	 * 0xFFFFFF00的作用是，如果byteVar 是正数，则置前24位为一，
	 * 这样toHexString输出一个小于等于15的byte整型的十六进制时，倒数第二位为零且不会被丢弃，
	 * 这样可以通过substring方法进行截取最后两位即可。
	 */
}
