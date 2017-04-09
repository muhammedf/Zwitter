package sunofkyuss.zwitter.beans.utility;

public interface Crypto {

	public String encrypt(String value);
	public boolean areEncryptedValuesEquals(String value, String encrypted);
}
