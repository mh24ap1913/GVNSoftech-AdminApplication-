package in.co.brings.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import in.co.brings.Beans.BrngSupportUserBean;
import in.co.brings.service.LoginService;
import in.co.brings.service.UserManagmentService;

@Path("/loginUser") 
public class LoginController {

	
	 @Path("/signUp")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> signUp(BrngSupportUserBean brngsupportuserbean) throws ClassNotFoundException, SQLException {  
		  
		 
			LoginService ls=new LoginService();
		  int ret=ls.signUpUser(brngsupportuserbean);
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		  HashMap<String, Integer> hs=new HashMap<>();
		 hs.put("response", ret);
	    return hs;
	  }
	 
	 
	 @Path("/login")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> login(HashMap<String,String> inputDetails) throws ClassNotFoundException, SQLException {  
		  
		 
			LoginService ls=new LoginService();
		  int ret=ls.loginUser(inputDetails);
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		  HashMap<String, Integer> hs=new HashMap<>();
		 hs.put("response", ret);
	    return hs;
	  }
	  
}
