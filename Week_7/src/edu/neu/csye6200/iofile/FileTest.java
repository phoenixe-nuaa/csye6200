package edu.neu.csye6200.iofile;

import java.io.File;

public class FileTest {
	
	public void run() {
		File baseDir = new File(".");
//		File baseDir = new File("csye6200");
		// Does it exist?
		// If not, create the directory
		if (!baseDir.exists()) 
			baseDir.mkdirs();
		listDir(baseDir);
	}
	
	public void listDir(File dirFile) {
		// reject non-directory
		if (!dirFile.isDirectory()) return;
		System.out.println("Dir: " + dirFile.getAbsolutePath());
		// Handle data files
		for (File file: dirFile.listFiles()) {
			// Skip Directories
			if (file.isDirectory()) continue;
			String fTxt =  String.format("%1$32s %2$10d", file.getName(), file.length());
			System.out.println(fTxt);
		}
		
		// Handle Directory files
		for (File file: dirFile.listFiles()) {
			// Skip data files - keep directories
			// Recursive
			if (!file.isDirectory())
				listDir(file);
		}
	}

	public static void main(String[] args) {
		FileTest fileTest = new FileTest();
		fileTest.run();
	}
}