import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	Cipher cipherEncrypt;
	Cipher cipherDecrypt;
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public AES(byte[] key, byte[] initVector) {
		try {
			byte[] keyNew = Arrays.copyOf(key, 16);
			
			IvParameterSpec iv = new IvParameterSpec(initVector);
			SecretKeySpec sKeySpec = new SecretKeySpec(keyNew, "AES");
			
			
			cipherEncrypt = Cipher.getInstance("AES/CBC/NoPadding");
			cipherEncrypt.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
			
			cipherDecrypt = Cipher.getInstance("AES/CBC/NoPadding");
			cipherDecrypt.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public byte[] encrypt(String value) {
		byte[] encrypted = null;
		byte[] msg = value.getBytes();
		int ceil = (int) Math.ceil(msg.length/16.);
		msg = Arrays.copyOf(msg, 16 * ceil);
		try {
			encrypted = cipherEncrypt.doFinal(msg);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encrypted;
	}
	
	public byte[] decrypt(byte[] encrypted) {
		byte[] original = null;
		try {
			original = cipherDecrypt.doFinal(encrypted);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return original;
	}
}
