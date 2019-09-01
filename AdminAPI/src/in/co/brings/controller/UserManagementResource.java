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

import in.co.brings.service.UserManagmentService;


@Path("/UserManagementResource") 
public class UserManagementResource {
	
	UserManagmentService ls=new UserManagmentService();
	
	 
	  // This method is called if HTML and9 XML is not requested  
	  @Path("/getGeneralInfo")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,String> getGeneralInfo(HashMap<String,String> action) {  
		  
		  String ret=ls.getGeneralInfo(action.get("action"));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		  HashMap<String, String> hs=new HashMap<>();
		  if(ret.equalsIgnoreCase("not-reg"))
		  {
			  hs.put("response", "error");
		  }
		 
		  else
		  {
			  hs.put("response", ret);
		  }
	    return hs;
	  }
	  
	  @Path("/getPersonInfo")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public ArrayList<HashMap<String,Object>> getPersonInfo(HashMap<String,String> action) {  
		  
		  ArrayList<HashMap<String,Object>> ret=ls.getPersonInfo(action.get("action"),action.get("email"));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		 /* HashMap<String, HashMap<String,Object>> hs=new HashMap<>();
		
			  hs.put("response", ret);*/
		
	    return ret;
	  } 
	  
	  @Path("/getPendingServiceMan")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public ArrayList<HashMap<String,Object>> getPendingServiceMan(HashMap<String,String> action) {  
		  
		  ArrayList<HashMap<String,Object>> ret=ls.getPendingServiceMan();
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		 // HashMap<String, HashMap<String,Object>> hs=new HashMap<>();
		
			 // hs.put("response", ret);
		
	    return ret;
	  } 
	  
	  @Path("/getPersonInfoBasedOnLogin")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,HashMap<String,Object>> getPersonInfoBasedOnLogin(HashMap<String,String> action) {  
		  
		  HashMap<String,Object> ret=ls.getPersonInfoBasedOnLogin(Integer.parseInt(action.get("loginID")));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		  HashMap<String, HashMap<String,Object>> hs=new HashMap<>();
		
			  hs.put("response", ret);
		
	    return hs;
	  } 
	  
	  @Path("/acceptServiceman")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> acceptServiceman(HashMap<String,String> action) {  
		  
		  int ret=ls.acceptServiceman(action.get("op"),action.get("email"));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		  HashMap<String, Integer> hs=new HashMap<>();
		
			  hs.put("response", ret);
		
	    return hs;
	  } 
	  
	  
	  
	  
	  
	  
	 
}
