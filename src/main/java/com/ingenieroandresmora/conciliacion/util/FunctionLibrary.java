package com.ingenieroandresmora.conciliacion.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FunctionLibrary {

	public String encript(String str) {

		String encripted = "";
		String privateKey = "!#_@.22" + str;

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(privateKey.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			encripted = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			encripted = str;
		}

		return encripted;

	}

	public String month(int month) {

		String[] months = { "","Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre", "Diciembre" };

		return months[month];
	}
}
