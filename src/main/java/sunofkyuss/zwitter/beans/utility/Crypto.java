package sunofkyuss.zwitter.beans.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {

	public static String md5(String s) {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] b = md.digest(s.getBytes());

		return new String(b);
	}

	public static boolean md5Equals(String value, String encrypted) {
		value = md5(value);
		return value.equals(encrypted);
	}
}
