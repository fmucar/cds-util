package com.cooldatasoft.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author fmucar@cooldatasoft.com
 * 
 */

@Slf4j
public class FileObjectUtil {

	public static void write(String fileName, Object obj) {
		try {
			// use buffering
			log.debug("File Name : {} , object : {} ", fileName, obj);
			OutputStream file = new FileOutputStream(fileName);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			try {
				output.writeObject(obj);
				log.trace("File write completed");
			} finally {
				output.close();
			}
		} catch (IOException ex) {
		}

	}

	public static Object read(String fileName) {
		try {
			// use buffering
			InputStream file = new FileInputStream(fileName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			try {
				// deserialize the List
				log.trace("Deserializing object");
				return input.readObject();
			} finally {
				input.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
