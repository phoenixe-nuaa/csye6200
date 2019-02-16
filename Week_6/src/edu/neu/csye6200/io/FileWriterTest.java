package edu.neu.csye6200.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
	private String base = "src/neu/edu/csye6200/io/";
	
	public void run() {
		try {
			// Open source and destination files
			FileReader reader = new FileReader(base + "FileWriterTest.java");
		    FileWriter writer = new FileWriter(base + "FWDDuplicateX.txt");
			
			int inval = reader.read();
			while(inval > 0) {
				writer.write(""+ (char)inval);
				inval = reader.read();
			}
			// Your responsibility to close open files
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			// File can't be found
			e.printStackTrace();
		} catch (IOException e) {
			// All other IO problems
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FileWriterTest fwtDemo = new FileWriterTest();
		fwtDemo.run();
	}

}