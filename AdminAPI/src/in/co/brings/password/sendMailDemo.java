package in.co.brings.password;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendMailDemo {
	
	// static RandomPassword d=new RandomPassword();
	
	public static String sendMail(String email,String pwd)
	{
		
  String status="";
// String host="smtp.gmail.com";  
  String host="mail.brings.co.in";  
//  final String user="brings.gvn@gmail.com";//change accordingly
// final String user="myteamdiscussion@gmail.com";//change accordingly  
  final String user="support@brings.co.in";
  
//final String password="papun12345";//change accordingly  
  final String password="Papun@12345";
  //final String password="gvn123456";
 System.out.println("email entered  "+email);  
 String to=email;
  //SmtpClient 
  //Get the session object  
   Properties props = new Properties(); 
   props.put("mail.transport.protocol","smtp");
   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.port", "587"); //TLS 465
   props.put("mail.smtp.auth", "true"); 
   props.put("mail.smtp.starttls.enable", "true");
   props.put("mail.debug", "true"); 
   
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
   
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("New Password For Brings");  
     message.setText("New password generated successfully  : " +pwd); //"vhu876");  
       
    //send the message  
     Transport.send(message);  
     status ="  message sent successfully...";
     System.out.println("message sent successfully...");  
    }catch (MessagingException e) {e.printStackTrace();
    status=e.toString();
    }	
	//}
	//return status;
	return to;
	
}
//	return status;		
//public static void main(String args[])
//{
//	
//	RandomPassword d=new RandomPassword();
//	//sendMailDemo.generatePassword("vikas", "14jhdref");
//	//sendMailDemo send=new sendMailDemo();
//	//send.generatePassword("vikas","vikas123");
//	String newPassword=d.generatePassword();
//	sendMail("ghodkevikas328@gmail.com",newPassword);
//}
}