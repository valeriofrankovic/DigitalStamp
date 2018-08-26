
public class DigitalStamp {

	private DigitalEnvelope dE;
	
	public DigitalStamp() {
		dE = new DigitalEnvelope();
	}
	
	public DigitalSignatureFormat getDigitalStamp(String message, String key, byte[] initVector) {
		
		DigitalEnvelopeFormat dEData = dE.encrypt(message, key.getBytes(), initVector);
		
		DigitalSignature dS = new DigitalSignature();
		DigitalSignatureFormat dSF = dS.getDigitalSignature(dEData.getBytes());
		
		return dSF;
	}
	
}
