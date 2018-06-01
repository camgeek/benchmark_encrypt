package test_encrypt;

import java.io.File;

import org.encryptor4j.util.FileEncryptor;

public class test_final {
	 public static void main(String [] args) throws Exception {
		 
		 long startTime = System.nanoTime();
		 //Encryption
		 File srcFile = new File("/home/cvrignaud/file.sql.tar.gz");
		 File destFile = new File("/home/cvrignaud/file.sql.tar.gz.encrypt");
		 String password = "124984618168486468dqdniopqfniqgnios<vnevs<e4v64s<v86s<1c86c18<86cs1v6<81bbiobcioneo<jc,oiscn";
		 FileEncryptor fe = new FileEncryptor(password);
		 fe.encrypt(srcFile, destFile);
		 long endTime   = System.nanoTime();
		 long totalTime = endTime - startTime;
		 System.out.println("Time for encrypt");
		 System.out.println(totalTime +" Nanosecondes");
		 System.out.println(totalTime / 1000000 +" Millisecondes");
		 System.out.println(totalTime / 1000000000 +" Secondes");
		 
		 /////////////////////////////////////////////////////////////////////////////////////////////////
		 
		 startTime = System.nanoTime();
		 //Decryption
		 File newFile = new File("/home/cvrignaud/file.sql.tar.gz.new");
		 fe.decrypt(destFile, newFile);
		 
		 endTime   = System.nanoTime();
		 totalTime = endTime - startTime;
		 System.out.println("Time for decrypt");
		 System.out.println(totalTime +" Nanosecondes");
		 System.out.println(totalTime / 1000000 +" Millisecondes");
		 System.out.println(totalTime / 1000000000 +" Secondes");
	 }

}
