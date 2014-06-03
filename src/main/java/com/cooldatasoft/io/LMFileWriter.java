package com.cooldatasoft.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LMFileWriter {

	public LMFileWriter(boolean isStatic, String s) {
		try {
			init(s, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LMFileWriter(File file) throws IOException {
		init(file);
	}

	public LMFileWriter(File file, boolean append) throws IOException {
		init(file, append);
	}

	public LMFileWriter(String s) throws IOException {
		init(s);
	}

	public LMFileWriter(String s, boolean append) throws IOException {
		init(s, append);
	}

	private void init(File file) throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	}

	private void init(File file, boolean append) throws IOException {
		writer = new PrintWriter(new BufferedWriter(
				new FileWriter(file, append)));
	}

	private void init(String s) throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(s)));
	}

	private void init(String s, boolean append) throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(s, append)));
	}

	public void write(Object obj) {
		writer.print(obj);
	}

	public void write(boolean flag) {
		writer.print(flag);
	}

	public void write(double d) {
		writer.print(d);
	}

	public void write(char ac[]) {
		writer.print(ac);
	}

	public void write(char c) {
		writer.print(c);
	}

	public void write(int i) {
		writer.print(i);
	}

	public void write(long l) {
		writer.print(l);
	}

	public void write(float f) {
		writer.print(f);
	}

	public void write(String s) {
		writer.print(s);
	}

	public void writeln(Object obj) {
		writer.println(obj);
	}

	public void writeln(boolean flag) {
		writer.println(flag);
	}

	public void writeln(double d) {
		writer.println(d);
	}

	public void writeln(char ac[]) {
		writer.println(ac);
	}

	public void writeln(char c) {
		writer.println(c);
	}

	public void writeln(int i) {
		writer.println(i);
	}

	public void writeln(long l) {
		writer.println(l);
	}

	public void writeln(float f) {
		writer.println(f);
	}

	public void writeln(String s) {
		writer.println(s);
	}

	public void write(String[] s) {
		for (int i = 0; i < s.length; i++) {
			writer.println(s[i]);
		}
	}

	public void close() {
		writer.close();
	}

	public void flush() {
		writer.flush();
	}

	private PrintWriter writer;
}
