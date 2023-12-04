package Simetrica;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

	private String userName;
	private String hashedPassword;
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.hashedPassword = hashedPass(password);
	}
	
	public String getUsername() {
		return userName;
	}
	
	public String getPassword() {
		return hashedPassword;
	}
	

	/**
	 * Método para hashear la contraseña del usuario a hexadecimal
	 * @param password
	 * @return Hash de la contraseña de usuario
	 */
	String hashedPass(String password) {
		
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-256");
				byte [] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
				return bytesToHex(encodedHash);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
	
	 String bytesToHex(byte[] hash) {
		
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (byte b : hash) {
			String hex = Integer.toHexString(b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	
	
	
}
