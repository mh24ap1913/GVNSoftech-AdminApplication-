package in.co.brings.controller;

import in.co.brings.Beans.RegisterBean;
import in.co.brings.service.RegisterService;

import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/form") 
public class RegisterController {

	
	 @Path("/formPath")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> RegisterUser(RegisterBean register) throws Exception {  
		  
		 
			RegisterService rs=new RegisterService();
		  int ret=rs.RegisterUser(register);
	    
		  HashMap<String, Integer> hs=new HashMap<>();
		 hs.put("response", ret);
	    return hs;
	 
	 }
}
