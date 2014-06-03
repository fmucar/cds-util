package com.cooldatasoft.io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class FileDownload {

	private final static String ENCODING = "windows-1254";

	public static String getURLContent(String urlStr) throws IOException {

		StringBuffer resultXml = new StringBuffer();
		HttpURLConnection httpUrlConnection = null;
		InputStream inputStream = null;

		if (!urlStr.startsWith("http://")) {
			urlStr = "http://" + urlStr;
		}
		URL url = new URL(urlStr);
		httpUrlConnection = (HttpURLConnection) url.openConnection();

		httpUrlConnection.setRequestMethod("GET");

		// String requestContent = " ";
		byte[] buffer = new byte[1024];
		// byte[] bytes = requestContent.getBytes();
		httpUrlConnection.setDoInput(true);
		inputStream = httpUrlConnection.getInputStream();

		inputStream = url.openStream();

		while (true) {
			int r = inputStream.read(buffer);
			if (r <= 0)
				break;
			String s = new String(buffer, 0, r, ENCODING);
			resultXml.append(s);
		}
		// System.err.println(resultXml);
		inputStream.close();

		httpUrlConnection.disconnect();

		return new String(resultXml);
	}

	public static void download(String address, String localFilePath) {
		OutputStream out = null;
		URLConnection conn = null;
		InputStream in = null;

		try {
			URL url = new URL(address);
			conn = url.openConnection();

			try {
				in = conn.getInputStream();
			} catch (FileNotFoundException e1) {
				return;
			}
			out = new BufferedOutputStream(new FileOutputStream(localFilePath
					+ address.substring(address.lastIndexOf('=') + 1)));
			byte[] buffer = new byte[1024];
			int numRead;

			while ((numRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, numRead);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ioe) {
			}
		}
	}

}
