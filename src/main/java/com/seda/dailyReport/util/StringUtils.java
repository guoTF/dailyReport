/*
 * Copyright 2009-2012 Evun Technology.
 *
 * This software is the confidential and proprietary information of
 * Evun Technology. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with evun.cn.
 */
package com.seda.dailyReport.util;

import org.apache.commons.lang.WordUtils;
import org.joda.time.DateTime;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * StringUtils
 *
 * @author 张纯奎
 * @created 2012-11-21 上午8:27:52
 * @since v1.3.1
 */
public final class StringUtils {

	public static final String[] EMPTY_STRING_ARRAY = new String[0];

	private static final Pattern KVP_PATTERN = Pattern.compile("([_.a-zA-Z0-9][-_.a-zA-Z0-9]*)[=](.*)"); //key value pair pattern.

	public static final String NUMBER_REGEX = "^-?[0-9]{1,20}$";//看是否为数字

	private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");

	public static final Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");

	public static final String EMPTY_STRING = "";

	public static final String QUOTE_CHARACTER = "'";// 英文单引号

	public static final char DELIMITER_CHARACTER = '"';

	public static final String POINT_CHARACTER = ".";

	public static final char EQUALS_CHARACTER = ':';

	public static final String SEMICOLON_SEPARATOR = ";";

	public static final String COMMA = ",";

	public static final String BLANK_REGEX = "\\s";


	private StringUtils() {
	}

	/**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int unicodeLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }
	
	/**
     * 字符串是否为null或""
     * null return true
     * "" return true
     * "hello" return false
     *
     * @author 张纯奎
     * @created 2012-12-4 下午2:14:56
     * @since v1.3.1
     */
	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0)
			return true;
		return false;
	}

	/**
     * is not empty string.
     * null return false
     * "" return false
     * "hello" return true
     *
     * @param str source string.
     * @return is not empty.
     */
	public static boolean isNotEmpty(String str) {
		return str != null && str.length() > 0;
	}

	/**
     * Check whether the given CharSequence has actual text.
     * More specifically, returns {@code true} if the string not {@code null},
     * its length is greater than 0, and it contains at least one non-whitespace character.
     * <p><pre>
     * StringUtils.hasText(null) = false
     * StringUtils.hasText("") = false
     * StringUtils.hasText(" ") = false
     * StringUtils.hasText("12345") = true
     * StringUtils.hasText(" 12345 ") = true
     * </pre>
     *
     * @param str the CharSequence to check (may be {@code null})
     * @return {@code true} if the CharSequence is not {@code null},
     * its length is greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
	public static boolean hasText(String str) {
		if (isEmpty(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
     * 两字符串内容是否相等
     *
     * @return equals
     */
	public static boolean isEquals(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		if (s1 == null || s2 == null)
			return false;
		return s1.equals(s2);
	}

	/**
     * is integer string.
     *
     * isInteger("3") return true;
     * isInteger(null) return false;
     * isInteger("") return false;
     *
     * @return is integer
     */
	public static boolean isInteger(String str) {
		if (str == null || str.length() == 0)
			return false;
		return INT_PATTERN.matcher(str).matches();
	}

	public static int parseInteger(String str) {
		if (!isInteger(str))
			return 0;
		return Integer.parseInt(str);
	}

	/**
     * Returns true if s is a legal Java identifier.<p>
     * <a href="http://www.exampledepot.com/egs/java.lang/IsJavaId.html">more info.</a>
     */
	public static boolean isJavaIdentifier(String s) {
		if (s.length() == 0 || !Character.isJavaIdentifierStart(s.charAt(0))) {
			return false;
		}
		for (int i = 1; i < s.length(); i++) {
			if (!Character.isJavaIdentifierPart(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
     * 逗号分隔的字符串是否包含value
     *
     * @author 张纯奎
     * @created 2012-12-4 下午2:23:25
     * @since v1.3.1
     */
	public static boolean isContains(String values, String value) {
		if (values == null || values.length() == 0) {
			return false;
		}
		return isContains(COMMA_SPLIT_PATTERN.split(values), value);
	}

	/**
     * values中是否包含与value相同的值
     *
     * @return contains
     */
	public static boolean isContains(String[] values, String value) {
		if (value != null && value.length() > 0 && values != null && values.length > 0) {
			for (String v : values) {
				if (value.equals(v)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
     * str中是否都是数字组成
     * isNumberic("3333") return true
     * isNumberic("3333.33") return false
     *
     * @author 张纯奎
     * @created 2012-12-4 下午2:35:23
     * @since v1.3.1
     */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
     * src中的字符如果在from中存在的话替换成to中对应位置的值
     * translat("hello","from","toe") return helle
     *
     * @param src  source string.
     * @param from src char table.
     * @param to   target char table.
     * @return String.
     */
	public static String translat(String src, String from, String to) {
		if (isEmpty(src))
			return src;
		StringBuilder sb = null;
		int ix;
		char c;
		for (int i = 0, len = src.length(); i < len; i++) {
			c = src.charAt(i);
			ix = from.indexOf(c);
			if (ix == -1) {
				if (sb != null)
					sb.append(c);
			} else {
				if (sb == null) {
					sb = new StringBuilder(len);
					sb.append(src, 0, i);
				}
				if (ix < to.length())
					sb.append(to.charAt(ix));
			}
		}
		return sb == null ? src : sb.toString();
	}

	/**
     * 返回ch分隔的字符串数组
     * StringUtils.split("hello,wo,rld",',') return ["hello", "wo", "rld"]
     *
     * @param ch char.
     * @return string array.
     */
	public static String[] split(String str, char ch) {
		List<String> list = null;
		char c;
		int ix = 0, len = str.length();
		for (int i = 0; i < len; i++) {
			c = str.charAt(i);
			if (c == ch) {
				if (list == null)
					list = new ArrayList<String>();
				list.add(str.substring(ix, i));
				ix = i + 1;
			}
		}
		if (ix > 0)
			list.add(str.substring(ix));
		return list == null ? EMPTY_STRING_ARRAY : (String[]) list.toArray(EMPTY_STRING_ARRAY);
	}

	/**
     * 返回字符串数组拼接后字符串.
     * join(new String[]{"hello","world"}) return helloworld
     *
     * @param array String array.
     * @return String.
     */
	public static String join(String[] array) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (String s : array)
			sb.append(s);
		return sb.toString();
	}

	/**
     * join string like javascript.
     *
     * @author 张纯奎
     * @created 2012-12-4 下午3:52:09
     * @since v1.3.1
     */
	public static String join(String[] array, char split) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				sb.append(split);
			sb.append(array[i]);
		}
		return sb.toString();
	}

	/**
     * join string like javascript.
     *
     * @author 张纯奎
     * @created 2012-12-4 下午3:51:59
     * @since v1.3.1
     */
	public static String join(String[] array, String split) {
		if (array.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i > 0)
				sb.append(split);
			sb.append(array[i]);
		}
		return sb.toString();
	}

	/**
     * join string like javascript.
     *
     * @author 张纯奎
     * @created 2012-12-4 下午3:51:43
     * @since v1.3.1
     */
	public static String join(Collection<String> coll, String split) {
		if (coll.isEmpty())
			return "";

		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (String s : coll) {
			if (isFirst)
				isFirst = false;
			else
				sb.append(split);
			sb.append(s);
		}
		return sb.toString();
	}

	/**
     * parse key-value pair.
     * parseKeyValuePair("a=a,b=b,c=c",",") return map<string,string> contains a=a,b=b,c=c
     *
     * @param str           string.
     * @param itemSeparator item separator.
     * @return key-value map;
     */
	public static Map<String, String> parseKeyValuePair(String str, String itemSeparator) {
		String[] tmp = str.split(itemSeparator);
		Map<String, String> map = new HashMap<String, String>(tmp.length);
		for (int i = 0; i < tmp.length; i++) {
			Matcher matcher = KVP_PATTERN.matcher(tmp[i]);
			if (matcher.matches()) {
				map.put(matcher.group(1), matcher.group(2));
			}
		}
		return map;
	}

	/**
     * get query string by key.
     *
     * @author 张纯奎
     * @created 2012-12-4 下午4:00:29
     * @since v1.3.1
     */
	public static String getQueryStringValue(String qs, String key) {
		Map<String, String> map = StringUtils.parseQueryString(qs);
		return map.get(key);
	}

	/**
     * parse query string to Parameters.
     *
     * @param qs query string.
     * @return Parameters instance.
     */
	public static Map<String, String> parseQueryString(String qs) {
		if (qs == null || qs.length() == 0)
			return new HashMap<String, String>();
		return parseKeyValuePair(qs, "\\&");
	}

	/**
     * 返回URL查询参数格式字符串
     *
     * @author 张纯奎
     * @created 2012-12-4 下午4:03:54
     * @since v1.3.1
     */
	public static String toQueryString(Map<String, String> ps) {
		StringBuilder buf = new StringBuilder();
		if (ps != null && ps.size() > 0) {
			for (Map.Entry<String, String> entry : new TreeMap<String, String>(ps).entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if (key != null && key.length() > 0 && value != null && value.length() > 0) {
					if (buf.length() > 0) {
						buf.append("&");
					}
					buf.append(key);
					buf.append("=");
					buf.append(value);
				}
			}
		}
		return buf.toString();
	}

	/**
     * 驼峰名称按split分隔camelToSplitName("camelToSplitName",",") return camel,to,split,name
     *
     * @author 张纯奎
     * @created 2012-12-4 下午4:11:53
     * @since v1.3.1
     */
	public static String camelToSplitName(String camelName, String split) {
		if (camelName == null || camelName.length() == 0) {
			return camelName;
		}
		StringBuilder buf = null;
		for (int i = 0; i < camelName.length(); i++) {
			char ch = camelName.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				if (buf == null) {
					buf = new StringBuilder();
					if (i > 0) {
						buf.append(camelName.substring(0, i));
					}
				}
				if (i > 0) {
					buf.append(split);
				}
				buf.append(Character.toLowerCase(ch));
			} else if (buf != null) {
				buf.append(ch);
			}
		}
		return buf == null ? camelName : buf.toString();
	}

	/**
     * <p>Capitalizes a String changing the first letter to title case as
     * per {@link Character#toTitleCase(char)}. No other letters are changed.</p>
     *
     * <p>For a word based algorithm, see {@link WordUtils#capitalize(String)}.
     * A <code>null</code> input String returns <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.capitalize(null)  = null
     * StringUtils.capitalize("")    = ""
     * StringUtils.capitalize("cat") = "Cat"
     * StringUtils.capitalize("cAt") = "CAt"
     * </pre>
     *
     * @param str the String to capitalize, may be null
     * @return the capitalized String, <code>null</code> if null String input
     * @see WordUtils#capitalize(String)
     * @see #uncapitalize(String)
     * @since 2.0
     */
	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
	}

	/**
     * <p>Removes control characters (char &lt;= 32) from both
     * ends of this String, handling <code>null</code> by returning
     * <code>null</code>.</p>
     *
     * <p>The String is trimmed using {@link String#trim()}.
     * Trim removes start and end characters &lt;= 32.
     * To strip whitespace use {@link #strip(String)}.</p>
     *
     * <p>To trim your choice of characters, use the
     * {@link #strip(String, String)} methods.</p>
     *
     * <pre>
     * StringUtils.trim(null)          = null
     * StringUtils.trim("")            = ""
     * StringUtils.trim("     ")       = ""
     * StringUtils.trim("fundManage")         = "fundManage"
     * StringUtils.trim("    fundManage    ") = "fundManage"
     * </pre>
     *
     * @param str the String to be trimmed, may be null
     * @return the trimmed string, <code>null</code> if null String input
     */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
     * Replace all occurences of a substring within a string with
     * another string.
     *
     * @param inString   String to examine
     * @param oldPattern String to replace
     * @param newPattern String to insert
     * @return a String with the replacements
     */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (isEmpty(inString) || isEmpty(oldPattern) || newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sb.append(inString.substring(pos));
		// remember to append any characters to the right of a match
		return sb.toString();
	}

	/**
     * Delete any character in a given String.
     *
     * @param inString      the original String
     * @param charsToDelete a set of characters to delete.
     *                      E.g. "az\n" will delete 'a's, 'z's and new lines.
     * @return the resulting String
     */
	public static String deleteAny(String inString, String charsToDelete) {
		if (isEmpty(inString) || isEmpty(charsToDelete)) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
     * <p>Right pad a String with spaces (' ').</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *)   = null
     * StringUtils.rightPad("", 3)     = "   "
     * StringUtils.rightPad("bat", 3)  = "bat"
     * StringUtils.rightPad("bat", 5)  = "bat  "
     * StringUtils.rightPad("bat", 1)  = "bat"
     * StringUtils.rightPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size the size to pad to
     * @return right padded String or original String if no padding is necessary,
     * <code>null</code> if null String input
     */
	public static String rightPad(String str, int size) {
		return rightPad(str, size, ' ');
	}

	/**
     * <p>Right pad a String with a specified character.</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *, *)     = null
     * StringUtils.rightPad("", 3, 'z')     = "zzz"
     * StringUtils.rightPad("bat", 3, 'z')  = "bat"
     * StringUtils.rightPad("bat", 5, 'z')  = "batzz"
     * StringUtils.rightPad("bat", 1, 'z')  = "bat"
     * StringUtils.rightPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str     the String to pad out, may be null
     * @param size    the size to pad to
     * @param padChar the character to pad with
     * @return right padded String or original String if no padding is necessary,
     * <code>null</code> if null String input
     * @since 2.0
     */
	public static String rightPad(String str, int size, char padChar) {
		return org.apache.commons.lang.StringUtils.rightPad(str, size, padChar);
	}

	/**
     * <p>Right pad a String with a specified String.</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.rightPad(null, *, *)      = null
     * StringUtils.rightPad("", 3, "z")      = "zzz"
     * StringUtils.rightPad("bat", 3, "yz")  = "bat"
     * StringUtils.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtils.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtils.rightPad("bat", 1, "yz")  = "bat"
     * StringUtils.rightPad("bat", -1, "yz") = "bat"
     * StringUtils.rightPad("bat", 5, null)  = "bat  "
     * StringUtils.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str    the String to pad out, may be null
     * @param size   the size to pad to
     * @param padStr the String to pad with, null or empty treated as single space
     * @return right padded String or original String if no padding is necessary,
     * <code>null</code> if null String input
     */
	public static String rightPad(String str, int size, String padStr) {
		return org.apache.commons.lang.StringUtils.rightPad(str, size, padStr);
	}

	/**
     * <p>Left pad a String with spaces (' ').</p>
     *
     * <p>The String is padded to the size of <code>size<code>.</p>
     *
     * <pre>
     * StringUtils.leftPad(null, *)   = null
     * StringUtils.leftPad("", 3)     = "   "
     * StringUtils.leftPad("bat", 3)  = "bat"
     * StringUtils.leftPad("bat", 5)  = "  bat"
     * StringUtils.leftPad("bat", 1)  = "bat"
     * StringUtils.leftPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size the size to pad to
     * @return left padded String or original String if no padding is necessary,
     * <code>null</code> if null String input
     */
	public static String leftPad(String str, int size) {
		return leftPad(str, size, ' ');
	}

	/**
     * <p>Left pad a String with a specified character.</p>
     *
     * <p>Pad to a size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.leftPad(null, *, *)     = null
     * StringUtils.leftPad("", 3, 'z')     = "zzz"
     * StringUtils.leftPad("bat", 3, 'z')  = "bat"
     * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
     * StringUtils.leftPad("bat", 1, 'z')  = "bat"
     * StringUtils.leftPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str     the String to pad out, may be null
     * @param size    the size to pad to
     * @param padChar the character to pad with
     * @return left padded String or original String if no padding is necessary,
     * <code>null</code> if null String input
     * @since 2.0
     */
	public static String leftPad(String str, int size, char padChar) {
		return org.apache.commons.lang.StringUtils.leftPad(str, size, padChar);
	}

	/**
     * <p>Left pad a String with a specified String.</p>
     *
     * <p>Pad to a size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtils.leftPad(null, *, *)      = null
     * StringUtils.leftPad("", 3, "z")      = "zzz"
     * StringUtils.leftPad("bat", 3, "yz")  = "bat"
     * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtils.leftPad("bat", 1, "yz")  = "bat"
     * StringUtils.leftPad("bat", -1, "yz") = "bat"
     * StringUtils.leftPad("bat", 5, null)  = "  bat"
     * StringUtils.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str    the String to pad out, may be null
     * @param size   the size to pad to
     * @param padStr the String to pad with, null or empty treated as single space
     * @return left padded String or original String if no padding is necessary,
     * <code>null</code> if null String input
     */
	public static String leftPad(String str, int size, String padStr) {
		return org.apache.commons.lang.StringUtils.leftPad(str, size, padStr);
	}

	public static int toInt(String str) {
		return toInt(str, 0);
	}

	/**
     * <p>Convert a <code>String</code> to an <code>int</code>, returning a
     * default value if the conversion fails.</p>
     *
     * <p>If the string is <code>null</code>, the default value is returned.</p>
     *
     * <pre>
     *   NumberUtils.toInt(null, 1) = 1
     *   NumberUtils.toInt("", 1)   = 1
     *   NumberUtils.toInt("1", 0)  = 1
     * </pre>
     *
     * @param str          the string to convert, may be null
     * @param defaultValue the default value
     * @return the int represented by the string, or the default if conversion fails
     * @since 2.1
     */
	public static int toInt(String str, int defaultValue) {
		if (str == null) {
			return defaultValue;
		}
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return defaultValue;
		}
	}

	/**
     * Trim all occurences of the supplied leading character from the given String.
     *
     * @param str              the String to check
     * @param leadingCharacter the leading character to be trimmed
     * @return the trimmed String
     */
	public static String trimLeadingCharacter(String str, char leadingCharacter) {
		if (isEmpty(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && sb.charAt(0) == leadingCharacter) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	private static boolean isIE(String userAgent){
		return userAgent.indexOf("msie") != -1;
	}
	private static boolean isChrome(String userAgent){
		return userAgent.indexOf("chrome") != -1;
	}
	private static boolean isFirefox(String userAgent){
		return userAgent.indexOf("firefox") != -1;
	}

	private static boolean isSafari(String userAgent){
		return userAgent.indexOf("safari") != -1;
	}
	private static boolean isIE11(String userAgent){
		return userAgent.indexOf("msie")== -1&&userAgent.indexOf("trident")>-1;
	}
	private static boolean isOpera(String userAgent){
		return userAgent.startsWith("opera");
	}
	private static boolean isUCweb(String userAgent){
		return userAgent.indexOf("ucweb") != -1;
	}

	public static String getBrowse(String userAgent){
		userAgent = userAgent.toLowerCase();
		String browse = "";
		if (isIE(userAgent)) {
			StringTokenizer st = new StringTokenizer(userAgent, ";");
			st.nextToken();
			browse = st.nextToken();
		} else if (isChrome(userAgent)) {
			browse = userAgent.substring(userAgent.indexOf("chrome"));
			try {
				browse = browse.split(" ")[0];
			} catch (Exception e) {
			}
		} else if (isFirefox(userAgent)) {
			browse = userAgent.substring(userAgent.indexOf("firefox"));
		} else if (isSafari(userAgent)) {
			browse = userAgent.substring(userAgent.indexOf("safari"));
		}else if(isIE11(userAgent)){
			browse = "ie11";
		}else if(isOpera(userAgent)){
			browse = userAgent.substring(0,userAgent.indexOf("("));
		}else if(isUCweb(userAgent)){
			browse = userAgent.substring(userAgent.indexOf("ucweb"));
		}
		return browse;
	}

	public static String getOperSystem(String userAgent){
		userAgent = userAgent.toLowerCase();
		String oper = "";
		if (isIE(userAgent)) {
			StringTokenizer st = new StringTokenizer(userAgent, ";");
			st.nextToken();
			st.nextToken();
			oper = st.nextToken();
		} else if (isFirefox(userAgent)) {
			oper = userAgent.substring(userAgent.indexOf('('), userAgent.indexOf(')') + 1);
			try {
				oper = oper.split(";")[0];
			} catch (Exception e) {

			}
		} else if (isSafari(userAgent) || isChrome(userAgent) || isIE11(userAgent) || isOpera(userAgent)||isUCweb(userAgent)) {
			oper = userAgent.substring(userAgent.indexOf('('), userAgent.indexOf(')') + 1);
		} else{
			oper = userAgent.substring(userAgent.indexOf('('), userAgent.indexOf(')') + 1);
			if(oper.length() == 0){
				oper = "unknown";
			}
		}
		oper = oper.replace("(", "").replace(")", "");
		return oper;
	}

	/**
     * 生成固定长度的数字
     */
	public static String getFixLenthString(int strLength) {
		long nanoTime = System.nanoTime();
		if (nanoTime < 0) {
			nanoTime = nanoTime + Long.MAX_VALUE + 1;
		}
		String nanoTimeStr = String.valueOf(nanoTime);
		int len = nanoTimeStr.length();
		if (strLength == len) {
			return nanoTimeStr;
		} else if (strLength > len) {
			return nanoTimeStr + getFixLenthString(strLength - len);
		} else {
			return nanoTimeStr.substring(len - strLength);
		}
	}

	/**
     * 生产外部交易订单后，用于余额或者优惠劵支付时填充outTradeNo
     */
	public static String generateOutTradeNo() {
		String now = DateTime.now().toString("yyMMddHHmmss");
		return now.concat(getFixLenthString(8));
	}

	public static String unicode2String(String unicode){
		if(isBlank(unicode))return null;
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;

		while((i=unicode.indexOf("\\u", pos)) != -1){
			sb.append(unicode.substring(pos, i));
			if(i+5 < unicode.length()){
				pos = i+6;
				sb.append((char)Integer.parseInt(unicode.substring(i+2, i+6), 16));
			}
		}

		return sb.toString();
	}

	public static String string2Unicode(String string) {

		if(isBlank(string))return null;
		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {

			// 取出每一个字符
			char c = string.charAt(i);

			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}
	/**
     * 根据乘客或者订单自增id生成32位订单号；
     *
     * 规则 yyMMddHHmmss + id（不足前面补0）+ <code>System.nanoTime()</code> 截取后10位
     * 招行的订单号最大长度为10位，当天不重复，所以传给招商的BillNo为后10位
     *
     * @param id 乘客或者订单自增id
     */
	public static String generateOrderNo(Integer id) {
		String now = DateTime.now().toString("yyMMddHHmmss");
		return now.concat(StringUtils.leftPad(id + "", 10, "0"))
				.concat(StringUtils.getFixLenthString(10));
	}

	public static void printRequest(HttpServletRequest request){
		//获取客户端向服务器端传送数据的协议名称
		//System.out.println("rotocol:" + request.getProtocol());
		//返回的协议名称.默认是http
		//System.out.println("Scheme:" + request.getScheme());
		//可以返回当前页面所在的服务器的名字;如果你的应用部署在本机那么其就返回localhost或者127.0.0.1 ，这两个是等价的
		//System.out.println("Server Name:" + request.getServerName());
		//可以返回当前页面所在的服务器使用的端口,就是8083
		//System.out.println("Server Port:" + request.getServerPort());
		//request.getRemoteAddr()是获得客户端的ip地址
		//System.out.println("Remote Addr:"+ request.getRemoteAddr());
		//request.getRemoteHost()是获得客户端的主机名。
		//System.out.println("Remote Host:"+ request.getRemoteHost());
		//返回字符编码 System.out.println("Character Encoding:"+ request.getCharacterEncoding());
		//System.out.println("Content Length:" + request.getContentLength());
		//定义网络文件的类型和网页的编码，决定浏览器将以什么形式、什么编码读取这个文件，
		//System.out.println("Content Type:"+ request.getContentType());
		//如果servlet由一个鉴定方案所保护，如HTTP基本鉴定，则返回方案名称
		//System.out.println("Auth Type:"+ request.getAuthType());
		//返回HTTP请求方法（例如GET、POST等等）
		//System.out.println("HTTP Method:"+ request.getMethod());
		//返回在URL中指定的任意附加路径信息。
		//System.out.println("path Info:"+ request.getPathInfo());
		//返回在URL中指定的任意附加路径信息，被子转换成一个实际路径
		//System.out.println("path Trans:"+ request.getPathTranslated());
		//返回查询字符串，即URL中?后面的部份。
		//System.out.println("Query String:"+ request.getQueryString());
		//如果用户通过鉴定，返回远程用户名，否则为null。
		//System.out.println("Remote User:"+ request.getRemoteUser());
		//返回客户端的会话ID
		//System.out.println("Session Id:"+ request.getRequestedSessionId());
		//返回URL中一部分，从“/”开始，包括上下文，但不包括任意查询字符串。
		//System.out.println("Request URI:"+ request.getRequestURI());
		//返回请求URI上下文后的子串
		//System.out.println("Servlet Path:"+ request.getServletPath());
		//返回指定的HTTP头标指。如果其由请求给出，则名字应为大小写不敏感。
		//System.out.println("Accept:"+ request.getHeader("Accept"));
		/*System.out.println("Host:"+ request.getHeader("Host"));
		System.out.println("Referer :"+ request.getHeader("Referer"));
		System.out.println("Accept-Language :"+ request.getHeader("Accept-Language"));
		System.out.println("Accept-Encoding :"+ request.getHeader("Accept-Encoding"));
		System.out.println("User-Agent :"+ request.getHeader("User-Agent"));
		System.out.println("Connection :"+ request.getHeader("Connection"));
		System.out.println("Cookie :"+ request.getHeader("Cookie"));*/
	}

/*	public static void main(String args[]) throws UnsupportedEncodingException {
		String a= string2Unicode("浦发银行昆山高新区支行");
		System.out.println("a:"+a.toUpperCase());
	}*/

}
