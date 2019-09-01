package in.co.brings.controller;

import in.co.brings.service.ComplaintManagementService;

import java.awt.PageAttributes.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/ComplaintManagementResource") 
public class ComplaintManagementResource {
	
	
	
	 
	  // This method is called if HTML and9 XML is not requested  
	  @Path("/logComplaint")
	  @POST 
	//  @Consumes(MediaType.APPLICATION_JSON)
	//  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> logComplaint(HashMap<String,String> complaintDetails) throws ClassNotFoundException, SQLException {  
		  ComplaintManagementService cms=new ComplaintManagementService();
		  int ret=cms.logComplaint(complaintDetails);
	  
		  HashMap<String, Integer> hs=new HashMap<>();
		  hs.put("response", ret);
	    return hs;
	  }
	  
	  @Path("/updateComplaint")
	  @POST 
	//  @Consumes(MediaType.APPLICATION_JSON)
	//  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> updateComplaint(HashMap<String,String> complaintDetails) throws ClassNotFoundException, SQLException {  
		  ComplaintManagementService cms=new ComplaintManagementService();
		  int ret=cms.updateComplaint(complaintDetails);
	  
		  HashMap<String, Integer> hs=new HashMap<>();
		  hs.put("response", ret);
	    return hs;
	  }
	  
	  @Path("/getComplaints")
	  @POST 
	//  @Consumes(MediaType.APPLICATION_JSON)
	//  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,ArrayList<HashMap<String,Object>>> getComplaints(HashMap<String,String> inputDetails) throws ClassNotFoundException, SQLException {  
		  ComplaintManagementService cms=new ComplaintManagementService();
		  ArrayList<HashMap<String,Object>> ret=cms.getComplaintDetails(inputDetails.get("email"),inputDetails.get("status"));
	  
		  HashMap<String, ArrayList<HashMap<String,Object>>> hs=new HashMap<>();
		  hs.put("response", ret);
	    return hs;
	  }
	  
	  @Path("/getComplaint")
	  @POST 
	//  @Consumes(MediaType.APPLICATION_JSON)
	//  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,ArrayList<HashMap<String,Object>>> getComplaint(HashMap<String,String> inputDetails) throws ClassNotFoundException, SQLException {  
		  ComplaintManagementService cms=new ComplaintManagementService();
		  ArrayList<HashMap<String,Object>> ret=cms.getComplaint(inputDetails.get("complainId"));
	  
		  HashMap<String, ArrayList<HashMap<String,Object>>> hs=new HashMap<>();
		  hs.put("response", ret);
	    return hs;
	  }
	  /*
	  @Path("/getPersonInfo")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public ArrayList<HashMap<String,Object>> getPersonInfo(HashMap<String,String> action) {  
		  
		  ArrayList<HashMap<String,Object>> ret=ls.getPersonInfo(action.get("action"),action.get("email"));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		  HashMap<String, HashMap<String,Object>> hs=new HashMap<>();
		
			  hs.put("response", ret);
		
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
	  
	  */
	  
	  
	  
	  
	 
}
