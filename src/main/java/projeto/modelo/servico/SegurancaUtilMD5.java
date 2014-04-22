package projeto.modelo.servico;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SegurancaUtilMD5 {
	public static String md5(String senha) {
		String sen = "";
		if(senha != null)
		{			
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			sen = hash.toString(16);			
		}
		return sen;
	}

}
