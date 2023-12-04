package Simetrica;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.NoSuchPaddingException;



public class Main_Req_1 {
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException {
		
		Scanner sc = new Scanner(System.in);
		
	// Creamos los usuarios para pedir las credenciales y acceder el menú
		User [] users = {
				new User("Pepe745", "ABC_123"),
				new User("Ana128", "DEF_456"),
				new User("Juan256", "GHI_789")		
		};
		
		boolean authenticated = false;
		
		// El usuario tiene 3 intentos para introducir bien los credenciales
		for (int attempt = 0; attempt < 3; attempt++) {
			System.out.println("*** Introduzca sus datos de acceso ***");
			System.out.println("Nombre de usuario: ");
			String userName = sc.nextLine();
			System.out.println("Contraseña: ");
			String password = sc.nextLine();
			
			User user = null;
			for(User u : users) {
				if (u.getUsername().equals(userName)) {
					user = u;
					break;
				}
			}
			
			// verificamos que el usuario y contraseña coinciden. Si ha introducido mal las credenciales
			// se le pide hasta un máximo de tres veces, si introduce mal las treves vecs
			// la aplicacion finaliza. Si introduce bien los datos, aparece el menú principal
			
			if (user != null && user.getPassword().equals(user.hashedPass(password))) {
				authenticated = true;
				System.out.println("Bienvenid@, " + user.getUsername());
				break;
			} else {
				System.out.println("Nombre de usuario o contraseña incorrectos, inténtelo de nuevo.");
			}
		}
		 if (!authenticated) {
			 System.out.println("Ha introducido 3 intentos fallidos");
			 System.out.println("Saliendo de la aplicación...");
			 System.exit(0);
		 }
		 
		 
		 // Llamamos a la clase de encriptación simétrica para utilizar sus métodos en el menú
		 
		 
		 encryptionMenu();
		
		
}
	
	
	
	public static void encryptionMenu() {
		SymmetricEncryption se = new SymmetricEncryption();
		Scanner sc = new Scanner(System.in);
		
		 menu();
		 
		 int option = sc.nextInt();
		 
		 switch (option) {
			
			case 1: 
				System.out.println(" *** Encriptar frase. *** ");
				System.out.println("Introduce la frase a encriptar: ");
				
				String phraseToEncrypt = sc.nextLine();
				System.out.println("Tu frase a encriptar es: " + phraseToEncrypt);
				se.encryptPhrase(phraseToEncrypt);
				encryptionMenu();
				
			case 2: 
				System.out.println("*** Desencriptar frase. ***");
				se.decryptPhrase();
				System.out.println("Frase desencriptada: ");
				
				encryptionMenu();
				
			case 3: 
				System.out.println("*** Salir del programa ***");
				System.out.println("Hasta pronto!");
				
				sc.close();
				System.exit(0);
				break;
			
			default:
				System.out.println("opción no válida, inténtalo de nuevo!");
				encryptionMenu();

			}
	 }
	
	
	private static void menu() {
		System.out.println("***** Menú encriptación *****");
		System.out.println("1.- Encriptar frase.");
		System.out.println("2.- Desencriptar frase.");
		System.out.println("3.- Salir del programa.");
		
	 }
	
}
	
		
		
		
	
	
	


