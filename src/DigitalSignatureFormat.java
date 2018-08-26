
public class DigitalSignatureFormat {
	
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

	private byte[] message;
	private byte[] encryptedHash;
	
	public DigitalSignatureFormat (byte[] message, byte[] encryptedHash) {
		this.message = message;
		this.encryptedHash = encryptedHash;
	}

	public byte[] getMessage() {
		return message;
	}

	public byte[] getEncryptedHash() {
		return encryptedHash;
	}
	
	public String toString() {
		return "Message: " + bytesToHex(message) + "\nHash: " + bytesToHex(encryptedHash) + "\n";
	}
	
}
