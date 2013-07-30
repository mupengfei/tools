package com.framework.file;
 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringBufferInputStream;
public class DirectoryCope {

	/**
	 * @param args
	 */

	// TODO Auto-generated method stub
	public void delete(File directory) {
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			for (File file : files) {
				delete(file);
			}
		}
		directory.delete();
	}

	public void copeDirectory(File target, File src) {
		if (target.exists()) {
			delete(target);
		}
		if (src.isFile()) {
			target.getParentFile().mkdirs();
		} else {
			target.mkdirs();
		}
		if (src.isDirectory()) {
			File[] files = src.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					copeDirectory(new File(target.getPath() + File.separator
							+ file.getName()), file);
				} else {
					copeFile(new File(target.getPath() + File.separator
							+ file.getName()), file);
				}
			}
		} else {
			copeFile(target, src);
		}
	}

	public void copeFile(File target, File src) {
		String read = readFile(src);
		writeFile(target, read);
	}

	public String readFile(File file) {
		FileInputStream input = null;
		BufferedInputStream inBuff = null;
		StringBuffer sb = new StringBuffer();
		try {
			input = new FileInputStream(file);
			inBuff = new BufferedInputStream(input);
			int s;
			while ((s = inBuff.read()) != -1) {
				sb.append((char)s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
				inBuff.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public void writeFile(File file, String content) {
		FileOutputStream output = null;
		BufferedOutputStream outBuff = null;
		BufferedInputStream sr = null;
		try {
			sr = new BufferedInputStream(new StringBufferInputStream(content));
			output = new FileOutputStream(file);
			outBuff = new BufferedOutputStream(output);
			int s;
			while ((s = sr.read()) != -1) {
				outBuff.write((char)s);
			}
			outBuff.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
				outBuff.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		DirectoryCope d = new DirectoryCope();
		d.copeDirectory(new File("d:/java.pdf"), new File("e:/百度云/Java编程思想(第4版).pdf"));
		
	}

}
