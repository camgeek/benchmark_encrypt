package test_encrypt;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
public class Security {
	public static String encrypt(String input, String key){
		  byte[] crypted = null;
		  try{
		    SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
		      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		      cipher.init(Cipher.ENCRYPT_MODE, skey);
		      crypted = cipher.doFinal(input.getBytes());
		    }catch(Exception e){
		    	System.out.println(e.toString());
		    }
		    return new String(Base64.encodeBase64(crypted));
		}

		public static String decrypt(String input, String key){
		    byte[] output = null;
		    try{
		      SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
		      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		      cipher.init(Cipher.DECRYPT_MODE, skey);
		      output = cipher.doFinal(Base64.decodeBase64(input));
		    }catch(Exception e){
		      System.out.println(e.toString());
		    }
		    return new String(output);
		}
		
		public static void main(String[] args) {
		  String key = "1234567891234567";
		  String data = "exampleexampleexampleexampleexampleexampleexample";
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
		  String encryptFile = Security.encrypt(content, key);
		  System.out.println(encryptFile);
		  //System.out.println(Security.decrypt(Security.encrypt(content, key), key));
		  //System.out.println(Security.encrypt(content, key));	    
		}	
}
	
