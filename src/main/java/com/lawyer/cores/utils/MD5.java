package com.lawyer.cores.utils;

import java.io.UnsupportedEncodingException;

import org.apache.shiro.codec.Base64;

import sun.misc.BASE64Encoder;

/**
 * MD5的算法在RFC1321 中定义
 * 在RFC 1321中，给出了Test suite用来检验你的实现是否正确： 
 * MD5 ("") = d41d8cd98f00b204e9800998ecf8427e 
 * MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661 
 * MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72 
 * MD5 ("message digest") = f96b697d7cb7938d525a2f31aaf161d0 
 * MD5 ("abcdefghijklmnopqrstuvwxyz") = c3fcd3d76192e4007dfb496cca67e13b 
 * 
 * @author haogj
 *
 * 传入参数：一个字节数组
 * 传出参数：字节数组的 MD5 结果字符串
 */
public class MD5 {
	
	public static String getMD5(byte[] source) {
		String s = null;
		// 用来将字节转换成 16 进制表示的字符
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'};
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance( "MD5" );
			md.update( source );
			
			// MD5 的计算结果是一个 128 位的长整数
			byte tmp[] = md.digest();
            
			// 用字节表示就是 16 个字节
			// 每个字节用 16 进制表示的话，使用两个字符
			// 所以表示成 16 进制需要 32 个字符
			char str[] = new char[16 * 2];
                      
			// 表示转换结果中对应的字符位置
			int k = 0;
			
			// 从第一个字节开始，对 MD5 的每一个字节  转换成 16 进制字符的转换
			for (int i = 0; i < 16; i++) {
				// 取第 i 个字节
				byte byte0 = tmp[i];
				// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				// 取字节中低 4 位的数字转换
				str[k++] = hexDigits[byte0 & 0xf];
			}
			// 换后的结果转换为字符串
			s = new String(str);			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		return s;	
	}
	
	public static String getMD5 (String raw) {
		if (raw == null || raw.length() == 0) {
			return "";
		}
		try {
			return getMD5(raw.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	} 
	
	public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
	
	public static String getLdapMD5(String raw){
		if (raw == null || raw.length() == 0) {
			return "";
		}
		
		String encoded = getBase64(getMD5(raw));
		return encoded;
	}
	
	public static void main(String[] args) {
		System.out.println(getMD5("123456"));
	}
	
}