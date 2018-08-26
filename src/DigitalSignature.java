import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigitalSignature {
	private RSA rsa;
	
	public DigitalSignature() {
		this.rsa = new RSA();
	}
	
	public DigitalSignatureFormat getDigitalSignature(String message) {
		return getDigitalSignature(message.getBytes());
	}
	
	public DigitalSignatureFormat getDigitalSignature(byte[] message) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] encryptedHash = digest.digest(message);
		encryptedHash = rsa.encrypt(encryptedHash);
		return new DigitalSignatureFormat(message, encryptedHash);
	}
	
}
