import java.util.Arrays;

public class Main {

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
	
	public static void main(String[] args) {
		byte[] iv = new byte[16];
		
		System.out.println("HELLO " + bytesToHex("HELLO".getBytes()));
		
		MyAES aes = new MyAES();
		byte[] crypted = aes.encrypt("HELLOHELLOHELLOHELLOHELLOHELLOHELLOHELLOHELLO", "HELLO", iv);
		System.out.println("Crypted length is " + crypted.length);
		System.out.println(bytesToHex(crypted));
		
		byte[] text = aes.decrypt(crypted, "HELLO", iv);
		System.out.println("Decrypted text is " + bytesToHex(text));
		System.out.println("Plain text is " + new String(text));
		
		AES aes_2 = new AES(Arrays.copyOf("HELLO".getBytes(), 16), iv);
		crypted = aes_2.encrypt("HELLOHELLOHELLOHELLOHELLOHELLOHELLOHELLOHELLO");
		System.out.println("Crypted length 2 is " + crypted.length);
		System.out.println(bytesToHex(crypted));
		
		text = aes_2.decrypt(crypted);
		System.out.println("Decrypted text is " + bytesToHex(text));
		System.out.println("Plain text is " + new String(text));
		
		System.out.println("");
		DigitalEnvelope dE = new DigitalEnvelope();
		DigitalEnvelopeFormat dEF = dE.encrypt("HELLOHELLO", "HELLO".getBytes(), iv);
		System.out.println(dEF.toString());
		System.out.println("Bytes of dEF " + bytesToHex(dEF.getBytes()));
		System.out.println(new DigitalEnvelopeFormat(dEF.getBytes()).toString());
		System.out.println("Reverted message: " + dE.decrypt(dEF, iv));
		
		DigitalStamp dS = new DigitalStamp();
		DigitalSignatureFormat dSF = dS.getDigitalStamp("HELLO", "HELLO", iv);
		System.out.println(dSF.toString());
	}
}
