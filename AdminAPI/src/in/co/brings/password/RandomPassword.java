package in.co.brings.password;

import java.util.Random;
//main class
public class RandomPassword {
	
	public String generatePassword(){
	//static char[] generatePassword(int length){
	  //  username="ghodkevikas328@gmail.com";
		int length=8;
		String letters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstvuwxyz";
		String numbers="0123456789";
		String pwd=letters+numbers;
		Random r=new Random();
		char[] newPass=new char[ length];
		//char[] newPass1=char(newPass);
		for(int i=0;i<length;i++){
			newPass[i]=pwd.charAt(r.nextInt(pwd.length()));
			
		}
		return String.valueOf(newPass);
	}
}