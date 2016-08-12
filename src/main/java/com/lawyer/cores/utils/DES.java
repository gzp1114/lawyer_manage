package com.lawyer.cores.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DES {

	SecretKeySpec _key = null;
	private static byte[] _iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public DES() {

	}

	public DES(String str) {
		setKey(str);
	}

	public Key key() {
		return _key;
	}

	public void key(SecretKeySpec _key) {
		this._key = _key;
	}

	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 根据参数生成 KEY
	 */
	public void setKey(String strKey) {
		try {
			this.key( new SecretKeySpec(strKey.getBytes("utf-8"), "DES") );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密 String 明文输入 ,String 密文输出
	 */
	public String encryptStr(String plainString) {

		try {
			//System.out.println("origin string:" + plainString);
			//System.out.println("string to utf8 byte:" + parseByte2HexStr(plainString.getBytes("UTF8")));
			byte[] encryptedData = encryptByte(plainString.getBytes("UTF8"));
			//System.out.println("desed byte:" + parseByte2HexStr(encryptedData));
			//System.out.println("base64 string:" + Base64.encode(encryptedData));
			return Base64.encode(encryptedData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;

	}

	/**
	 * 解密 以 String 密文输入 ,String 明文输出
	 * 
	 * @param strMi
	 * @return
	 */
	public String decryptStr(String encryptString) {
		try {
			byte[] decryptData = decryptByte(Base64.decode(encryptString));
			return new String(decryptData, "UTF8");
		} catch (Exception e) {
			System.out.println("not a des string:" + encryptString);
		} finally {
			
		}
		return null;
	}

	/**
	 * 加密以 byte[] 明文输入 ,byte[] 密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private byte[] encryptByte(byte[] byteS) {
		IvParameterSpec zeroIv = new IvParameterSpec(_iv);
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, this.key(), zeroIv);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			zeroIv = null;
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * 解密以 byte[] 密文输入 , 以 byte[] 明文输出
	 * 
	 * @param byteD
	 * @return
	 */
	private byte[] decryptByte(byte[] byteD) {
		IvParameterSpec zeroIv = new IvParameterSpec(_iv);
		Cipher cipher = null;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, this.key(), zeroIv);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing SqlMap class. Cause: " + e);
		} finally {
			zeroIv = null;
			cipher = null;
		}
		return byteFina;
	}

	public static void main(String[] args) throws Exception {
		DES des = new DES("12345678");

		
		String payParam = "{\"pid\":\"2088811412845114\",\"appId\":\"2015081900223344\",\"privateKey\":\"MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALXizn2IxqNDWPmkM3mhrnuEX7U4ho36W29qmgJSbsn1QL3O7LcK4NhCdij3HwtqQFByd/LygeT7+x0k2bIr654LnEPxkORyuw3hc0namZBKUkDRgPpuykyTmtpcUqPrC1k4Nhd347+oxcoawnB44uFW+kOeR0K8O0dJMjS3TJsBAgMBAAECgYEAorBoeqjo5AfNgh55XIZeB2V0JiiVUlFpj9MORp1tM4AHlL+NxHwaedWY0zOXtSIovMxmtgxsW2F4T60ye8gFSyo2Yp9L97ozYvhwXrP/2uCWKn1mnUbFlCrWSBx4zOQLS7jlR3ZQ4sex7496I+AMzx6HbNbIjWQcRZhVSsUmarECQQDhpMLyqOFhHjGNgaZabu1YGbCKWLYObbayNVikI9Mm+wars8bVwk6GKx0B6XlBY0FBMSfNYXjcG+aNFkO7+9ILAkEAzlsEmHo+m/d23zkAQac+adKjpwB2WPo+JvH1I46xna9Nwv0d1jzK8BmNfZAo+Y1t1M8wwEL25/5a5c+pGxpaowJBAJmM+7USQ71JRMNdNQ1Za16GrOKYl9udwbPoJZgdYPWn4dQ6lNamUbVv5V+FuCRNHPz2LHXRHOP8gJoGSrXEGh0CQQC3ZGSdLmdeLXvTvtDfiEcLvUv8K4kCYsPB0N9QDo0D2rx9Qn6126lv4QXCRdBkmLC3pLtq4xiT7ZXTD872a2GJAkBKzOzzZ3RYO0TrhL+FlNe+HYBg03mi4r2Zhu38VK0zXuCS3k5myB7TH9rZAUw9gEetYC/xolqsjNIj5bd9AxdC\",\"publicKey\":\"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC14s59iMajQ1j5pDN5oa57hF+1OIaN+ltvapoCUm7J9UC9zuy3CuDYQnYo9x8LakBQcnfy8oHk+/sdJNmyK+ueC5xD8ZDkcrsN4XNJ2pmQSlJA0YD6bspMk5raXFKj6wtZODYXd+O/qMXKGsJweOLhVvpDnkdCvDtHSTI0t0ybAQIDAQAB\",\"alipayPublicKey\":\"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB\"}";//库中取
//		String payParam = URLEncoder.encode("{\"pid\":\"2088811412845114\",\"appId\":\"2015081900223344\",\"privateKey\":\"MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALXizn2IxqNDWPmkM3mhrnuEX7U4ho36W29qmgJSbsn1QL3O7LcK4NhCdij3HwtqQFByd/LygeT7+x0k2bIr654LnEPxkORyuw3hc0namZBKUkDRgPpuykyTmtpcUqPrC1k4Nhd347+oxcoawnB44uFW+kOeR0K8O0dJMjS3TJsBAgMBAAECgYEAorBoeqjo5AfNgh55XIZeB2V0JiiVUlFpj9MORp1tM4AHlL+NxHwaedWY0zOXtSIovMxmtgxsW2F4T60ye8gFSyo2Yp9L97ozYvhwXrP/2uCWKn1mnUbFlCrWSBx4zOQLS7jlR3ZQ4sex7496I+AMzx6HbNbIjWQcRZhVSsUmarECQQDhpMLyqOFhHjGNgaZabu1YGbCKWLYObbayNVikI9Mm+wars8bVwk6GKx0B6XlBY0FBMSfNYXjcG+aNFkO7+9ILAkEAzlsEmHo+m/d23zkAQac+adKjpwB2WPo+JvH1I46xna9Nwv0d1jzK8BmNfZAo+Y1t1M8wwEL25/5a5c+pGxpaowJBAJmM+7USQ71JRMNdNQ1Za16GrOKYl9udwbPoJZgdYPWn4dQ6lNamUbVv5V+FuCRNHPz2LHXRHOP8gJoGSrXEGh0CQQC3ZGSdLmdeLXvTvtDfiEcLvUv8K4kCYsPB0N9QDo0D2rx9Qn6126lv4QXCRdBkmLC3pLtq4xiT7ZXTD872a2GJAkBKzOzzZ3RYO0TrhL+FlNe+HYBg03mi4r2Zhu38VK0zXuCS3k5myB7TH9rZAUw9gEetYC/xolqsjNIj5bd9AxdC\",\"publicKey\":\"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC14s59iMajQ1j5pDN5oa57hF+1OIaN+ltvapoCUm7J9UC9zuy3CuDYQnYo9x8LakBQcnfy8oHk+/sdJNmyK+ueC5xD8ZDkcrsN4XNJ2pmQSlJA0YD6bspMk5raXFKj6wtZODYXd+O/qMXKGsJweOLhVvpDnkdCvDtHSTI0t0ybAQIDAQAB\",\"alipayPublicKey\":\"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB\"}");//库中取

		
		String str = "中文abc12";
		String enStr = des.encryptStr(payParam);
		String deStr = des.decryptStr(enStr);

		System.out.println("加密前: " + payParam);
		System.out.println("加密后: " + enStr);
		System.out.println("解密后: " + deStr);
	}

}
