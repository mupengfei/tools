package net.jesse.framework.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyWordFilter {
	private static Pattern pattern = null;

	// ??words.properties???????????????
	private static void initPattern() {
		StringBuffer patternBuf = new StringBuffer("");
		try {
			BufferedReader read = new BufferedReader(new FileReader(new File(
					"src/net/jesse/framework/tools/words.txt")));

			patternBuf.append("(");
			String s = "";
			while ((s = read.readLine()) != null) {
				patternBuf.append(s + "|");
			}
			patternBuf.deleteCharAt(patternBuf.length() - 1);
			patternBuf.append(")");

			pattern = Pattern.compile(patternBuf.toString());
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}
	}

	private static String doFilter(String str) {
		Matcher m = pattern.matcher(str);
		str = m.replaceAll("jjj");
		return str;
	}

	public static void main(String[] args) {
		String str = "敏感词XXX";
		System.out.println("str:" + str);
		initPattern();
		Date d1 = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(
				"EEE, d MMM yyyy HH:mm:ss:SSS Z");
		System.out.println("start:" + formatter.format(d1));
		System.out.println("开始长度" + str.length() + "过滤后"
				+ KeyWordFilter.doFilter(str));
		Date d2 = new Date();
		System.out.println("end:" + formatter.format(d2));
	}

}
