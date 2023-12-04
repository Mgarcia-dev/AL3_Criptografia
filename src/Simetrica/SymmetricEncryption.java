package Simetrica;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class SymmetricEncryption {

	private SecretKey sKey;
	private Cipher cipher;
	private String encryptedPhraseBase64;
	
	public SymmetricEncryption() {
		
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			sKey = keyGen.generateKey();
			
			cipher = Cipher.getInstance("AES");
			
		} catch (NoSuchAlgorithmException  | NoSuchPaddingException pe) {
			// TODO Auto-generated catch block
			pe.printStackTrace();
		}
		
	}
	
	public void encryptPhrase (String phrase) {
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, sKey);
			byte [] encryptedPhrase = cipher.doFinal(phrase.getBytes());
			encryptedPhraseBase64 = Base64.getEncoder().encodeToString(encryptedPhrase);
			System.out.println("Frase encriptada con éxito! ");
			
		} catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		
	}
	
	public void decryptPhrase(){
		try {
			if (encryptedPhraseBase64 != null) {
			
				cipher.init(Cipher.DECRYPT_MODE, sKey);
				byte [] encryptedPhrase = Base64.getDecoder().decode(encryptedPhraseBase64);
				byte [] decryptedPhrase = cipher.doFinal(encryptedPhrase);
				System.out.println("frase encriptada: " + new String (encryptedPhraseBase64));
				System.out.println("frase desencriptada : " + new String(decryptedPhrase));
			} else {
				System.out.println("AVISO: No existe ninguna frase guardada para desencriptar! ");
			}
			
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {	
			e.printStackTrace();
		}
			
	}
}

