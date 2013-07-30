package net.jesse.framework.file.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDemo {
	public void goZip(String tarPath, String srcPath) {
		File file = new File(srcPath);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (file.isFile()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", goUtf8(file.getName()) );
			map.put("path", srcPath);
			list.add(map);
			putZip(tarPath, list);
		} else {
			dire2Str(file.getName() + "/", file, list);
			putZip(tarPath, list);
		}
	}

	public static void dire2Str(String prefix, File path,
			List<Map<String, String>> list) {
		File[] files = path.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", prefix + file.getName());
				map.put("path", file.getPath());
				list.add(map);
			} else {
				dire2Str(prefix + file.getName() + "/", file, list);
			}
		}

	}

	public static void putZip(String tarPath, List<Map<String, String>> srcPath) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			FileOutputStream fou = new FileOutputStream(tarPath);
			CheckedOutputStream cou = new CheckedOutputStream(fou,
					new Adler32());
			ZipOutputStream zou = new ZipOutputStream(cou);
			out = new BufferedOutputStream(zou);
			for (Map<String, String> path : srcPath) {
				zou.putNextEntry(new ZipEntry(path.get("name")));
				FileInputStream is = new FileInputStream(path.get("path"));
				in = new BufferedInputStream(is);
				int s;
				int nu = 0;
				while ((s = in.read()) != -1) {
					out.write(s);nu++;
				}
				System.out.println(nu*2/1024/1024);
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	public String goUtf8(String src){
		try {
			return new String(src.getBytes(),"iso8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return src;
	}
	
	public static void main(String[] args) {
		// e:/�ٶ���/Java���˼��(��4��).pdf
		
		Date date1 = new Date();
		new ZipDemo().goZip("d:/aa.zip", "d:/��.txt");
		Date date2 = new Date();
		System.out.println(date2.getTime() - date1.getTime());
	}

}
