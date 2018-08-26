import java.nio.ByteBuffer;
import java.util.Arrays;

public class DigitalEnvelopeFormat {

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
	private byte[] key;
	
	public DigitalEnvelopeFormat(byte[] message, byte[] key) {
		this.message = message;
		this.key = key;
	}
	
	public DigitalEnvelopeFormat(byte[] fromBytes) {
		byte[] messageLength = Arrays.copyOfRange(fromBytes, 0, 4);
		ByteBuffer wrapped = ByteBuffer.wrap(messageLength);
		int mLength = wrapped.getInt();
		this.message = Arrays.copyOfRange(fromBytes, 4, mLength);
		byte[] keyLength = Arrays.copyOfRange(fromBytes, 4+mLength, 4+mLength+4);
		wrapped = ByteBuffer.wrap(keyLength);
		int kLength = wrapped.getInt();
		this.key = Arrays.copyOfRange(fromBytes, 4+mLength+4, 4+mLength+4+kLength);
	}

	public byte[] getMessage() {
		return message;
	}

	public byte[] getKey() {
		return key;
	}
	
	public byte[] getBytes() {
		return ByteBuffer.allocate(4 + message.length + 4 + key.length).putInt(message.length).put(message).putInt(key.length).put(key).array();
	}

	public String toString() {
		return "Message: " + bytesToHex(message) + "\nKey: " + bytesToHex(key) + "\n";
	}
}
