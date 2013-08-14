package net.jesse.framework.file;
 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.nio.channels.FileChannel;
import java.util.Date;
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
					copeFile_New(new File(target.getPath() + File.separator
							+ file.getName()), file);
				}
			}
		} else {
			copeFile_New(target, src);
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
	
	public void copeFile_New(File target, File src) {
		try {
			FileChannel srcfc = new FileInputStream(src).getChannel();
			FileChannel targetfc = new FileOutputStream(target).getChannel();
			srcfc.transferTo(0, srcfc.size(), targetfc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DirectoryCope d = new DirectoryCope();
		Date d1 = new Date();
		d.copeDirectory(new File("d:/java.pdf"), new File("D:/百度云/图书/Java/Java编程思想(第4版).pdf"));
		Date d2 = new Date();
		System.out.println((d2.getTime() - d1.getTime()));
	}

}
