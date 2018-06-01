package test_encrypt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class Sample {

	 public static void main(String [] args) throws Exception {
		 		 
	        // generate public and private keys
	        KeyPair keyPair = buildKeyPair();
	        PublicKey pubKey = keyPair.getPublic();
	        PrivateKey privateKey = keyPair.getPrivate();
	        
	        
	        
	        String fileName = "/home/softia/test.txt";
			  BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  StringBuilder stringBuilder = new StringBuilder();
			  String line = null;
			  String ls = System.getProperty("line.separator");
			  try {
				while ((line = reader.readLine()) != null) {
				  	stringBuilder.append(line);
				  	stringBuilder.append(ls);
				  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  // delete the last new line separator
			  stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			  try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			  String content = stringBuilder.toString();
	        
	        
	        
	        // encrypt the message
	        byte [] encrypted = encrypt(privateKey, content);     
	        //System.out.println(new String(encrypted));  // <<encrypted message>>
	        
	        // decrypt the message
	        byte[] secret = decrypt(pubKey, encrypted);                                 
	        //System.out.println(new String(secret));     // This is a secret message
	        
	        String newFile = "/home/softia/testE.txt";
	        //String str = "Hello";
	        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
	        writer.write(new String(encrypted));
	         
	        writer.close();
	    }

	    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
	        final int keySize = 2048;
	        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	        keyPairGenerator.initialize(keySize);      
	        return keyPairGenerator.genKeyPair();
	    }

	    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
	        Cipher cipher = Cipher.getInstance("RSA");  
	        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

	        return cipher.doFinal(message.getBytes());  
	    }
	    
	    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
	        Cipher cipher = Cipher.getInstance("RSA");  
	        cipher.init(Cipher.DECRYPT_MODE, publicKey);
	        
	        return cipher.doFinal(encrypted);
	    }
}
