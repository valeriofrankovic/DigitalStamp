
public class DigitalEnvelope {
	
	private RSA rsaChiper;
	
	public DigitalEnvelope() {
		rsaChiper = new RSA();
	}
	
	public DigitalEnvelopeFormat encrypt (String message, byte[] key, byte[] initVector) {
		AES aesCipher = new AES(key, initVector);
		byte[] encryptedMessage = aesCipher.encrypt(message);
		byte[] rsaCipher = rsaChiper.encrypt(key);
		return new DigitalEnvelopeFormat(encryptedMessage, rsaCipher);
	}
	
	public String decrypt(DigitalEnvelopeFormat dEF, byte[] initVector) {
		byte[] key = rsaChiper.decrypt(dEF.getKey());
		AES aesCipher = new AES(key, initVector);
		return new String (aesCipher.decrypt(dEF.getMessage()));
	}
}
