package cn.com.p2p.framework.util;

import java.io.Serializable;
import java.security.Key;

import javax.crypto.Cipher;

/**
 * DES加密工具
 * @author pub
 *
 */
public class DESPlus implements Serializable {

	private static final long serialVersionUID = 3154981423464332012L;
	
	private static String strDefaultKey = "national";
	
	private Cipher encryptCipher = null;
	
	private Cipher decryptCipher = null;

	public DESPlus() throws Exception {
		this(strDefaultKey);
	}

	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public DESPlus(String strKey) throws Exception {
//		Security.addProvider(new com.sun.crypto.provider.SunJCE());
//		Key key = getKey(strKey.getBytes());
//		encryptCipher = Cipher.getInstance("DES");
//		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
//		decryptCipher = Cipher.getInstance("DES");
//		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	private Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8];
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	public  String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}
}
