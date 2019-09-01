package in.co.brings.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import in.co.brings.service.LookUpFunctionService;
import in.co.brings.service.UserManagmentService;


@Path("/LookUpFunctions") 
public class LookUpFunctions {
	

	
	 
	  // This method is called if HTML and9 XML is not requested  
	  @Path("/lookUpFilePath")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public ArrayList<HashMap<String,String>> lookUpFilePath(HashMap<String,String> action) {  
		  
			LookUpFunctionService lfs=new LookUpFunctionService();
			ArrayList<HashMap<String,String>> ret=lfs.getlookUpFilePath(action.get("action"),action.get("type"));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		  HashMap<String, String> hs=new HashMap<>();
		 
	    return ret;
	  }
	  
	  @Path("/updatelookUpFilePath")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public String updatelookUpFilePath(HashMap<String,String> action) {  
		  
			LookUpFunctionService lfs=new LookUpFunctionService();
			String ret=lfs.updatelookUpFilePath(action.get("action"),action.get("type"));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		 
		 
	    return ret;
	  }
	  
	/*  @Path("/getPersonInfo")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,HashMap<String,Object>> getPersonInfo(HashMap<String,String> action) {  
		  
		  HashMap<String,Object> ret=ls.getPersonInfo(action.get("action"),action.get("email"));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		  HashMap<String, HashMap<String,Object>> hs=new HashMap<>();
		
			  hs.put("response", ret);
		
	    return hs;
	  } */
	  
	  
	  
	  
	 
}
