package cn.chenxhcloud.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 
*   
* 项目名称：chenxh-utils  
* 类名称：cn.chenxhcloud.utils.EncodeUtils  
* @author : chenxh  
* 创建时间：2017年12月12日 下午5:21:49
* 描述：实现MD5加密和Sha加密算法
*
 */
public class EncodeUtils {
	private static final String KEY_MD5 = "MD5";
	private static final String KEY_SHA = "SHA";
	
    private final static String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String md5Encode(String source,boolean uppercase) {
        String result = null;
        try {
            result = source;
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_MD5);
            messageDigest.update(source.getBytes());
            result = byteArrayToHexString(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uppercase ? result.toUpperCase() : result;
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte tem : bytes) {
            stringBuilder.append(byteToHexString(tem));
        }
        return stringBuilder.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }
	
	
	public static  String  shaEncode(String inputStr)
    {
		if(isBlank(inputStr)) {
			throw new RuntimeException("加密字符串不能为空");
		}
        BigInteger sha =null;
        byte[] inputData = inputStr.getBytes();   
        try {
             MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);  
             messageDigest.update(inputData);
             sha = new BigInteger(messageDigest.digest());   
        } catch (Exception e) {e.printStackTrace();}
        return sha.toString(32);
    }
	
	
	public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

	public static String getMD5(String data) {
		 	if(isBlank(data)) {
		 		return null;
		 	}
		 	byte[] source = data.getBytes();
	        String s = null;
	        // 用来将字节转换成 16 进制表示的字符
	        char hexDigits[] = { 
	                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	        try {
	            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	            md.update(source);
	            byte tmp[] = md.digest();          
	            // MD5 的计算结果是一个 128 位的长整数，
	            // 用字节表示就是 16 个字节
	            char str[] = new char[16 * 2];   
	            // 每个字节用 16 进制表示的话，使用两个字符，
	            // 所以表示成 16 进制需要 32 个字符
	            int k = 0;                                
	            // 表示转换结果中对应的字符位置
	            for (int i = 0; i < hexDigits.length; i++) {    
	            	// 从第一个字节开始，对 MD5 的每一个字节
	                // 转换成 16 进制字符的转换
	            	// 取第 i 个字节
	                byte byte0 = tmp[i];  
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
	                // 取字节中高 4 位的数字转换,
	                // >>> 为逻辑右移，将符号位一起右移
	                str[k++] = hexDigits[byte0 & 0xf];   
	                // 取字节中低 4 位的数字转换
	            }
	            s = new String(str);  
	            // 换后的结果转换为字符串

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return s;
	    }
}
