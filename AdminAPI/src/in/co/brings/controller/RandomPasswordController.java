package in.co.brings.controller;

import in.co.brings.password.RandomPassword;
import in.co.brings.password.sendMailDemo;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/pass")
public class RandomPasswordController {
	
	
	@Path("/passMail")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String,String> sendMail(HashMap<String,String> maildemo){
		
		RandomPassword d=new RandomPassword();
		String newPassword=d.generatePassword();
		//String res=sendMail(maildemo.get("email"),newPassword);
		sendMailDemo demo=new sendMailDemo();
		String res=demo.sendMail(maildemo.get("email"),newPassword);
		
		HashMap<String,String> hs=new HashMap<String,String>();
	    hs.put("response",res);
		return hs;
		
	}

	
		
	

}
