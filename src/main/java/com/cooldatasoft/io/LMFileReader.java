package com.cooldatasoft.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class LMFileReader {

	BufferedReader bf = null;

	public LMFileReader(File fPath) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(fPath);
		InputStreamReader isr = new InputStreamReader(fis);
		bf = new BufferedReader(isr);
	}

	public LMFileReader(String fPath) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(new File(fPath));
		InputStreamReader isr = new InputStreamReader(fis);
		bf = new BufferedReader(isr);
	}

	public String readLine() throws IOException {
		return bf.readLine();
	}

	public int read() throws IOException {
		return bf.read();
	}

	public String readAll() throws IOException {
		String line = bf.readLine();
		StringBuffer result = new StringBuffer();

		while (line != null) {
			result.append(line + "\n");
			line = bf.readLine();
		}

		return new String(result);
	}

}
