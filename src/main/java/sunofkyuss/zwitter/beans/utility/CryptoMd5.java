package sunofkyuss.zwitter.beans.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@MD5
public class CryptoMd5 implements Crypto {

	@Override
	public String encrypt(String value) {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("md5");
		
		byte[] b = md.digest(value.getBytes());

		return new String(b);
	}

	@Override
	public boolean areEncryptedValuesEquals(String value, String encrypted) {
		System.out.println("md5");
		value = encrypt(value);
		return value.equals(encrypted);
	}
}
