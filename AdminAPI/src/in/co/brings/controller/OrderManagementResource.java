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

import in.co.brings.service.OrderManagementService;
import in.co.brings.service.UserManagmentService;


@Path("/OrderManagementResource") 
public class OrderManagementResource {
	
	OrderManagementService ls=new OrderManagementService();
	
	 
	  // This method is called if HTML and9 XML is not requested  
	  @Path("/getOrderDetails")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public ArrayList<HashMap<String,Object>> getOrderDetails(HashMap<String,String> action) {  
		  
		  ArrayList<HashMap<String,Object>> ret=ls.getOrderDetails(action.get("orderId"));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		//  ArrayList<HashMap<String,Object>> hs=new HashMap<>();
		 
	    return ret;
	  }
	  
	  @Path("/getOrderDetailsByEmail")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public ArrayList<HashMap<String,Object>> getOrderDetailsByEmail(HashMap<String,String> action) {  
		  
		  ArrayList<HashMap<String,Object>> ret=ls.getOrderDetailsByEmail(action.get("email"));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		//  ArrayList<HashMap<String,Object>> hs=new HashMap<>();
		 
	    return ret;
	  }
	  
	  @Path("/getOrdersByDate")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public ArrayList<HashMap<String,Object>> getOrdersByDate(HashMap<String,String> action) {  
		  
		  ArrayList<HashMap<String,Object>> ret=ls.getOrdersByDate(action);
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		//  ArrayList<HashMap<String,Object>> hs=new HashMap<>();
		 
	    return ret;
	  }
	  
	  @Path("/getOrdersByFilter")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public ArrayList<HashMap<String,Object>> getOrdersByFilter(HashMap<String,String> action ) {  
		  
		  ArrayList<HashMap<String,Object>> ret=ls.getOrdersByFilter(action);
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		//  ArrayList<HashMap<String,Object>> hs=new HashMap<>();
		 
	    return ret;
	  }
	  
	  @Path("/getDistributedOrderDetails")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public ArrayList<HashMap<String,Object>> getDistributedOrderDetails(HashMap<String,String> action ) {  
		  
		  ArrayList<HashMap<String,Object>> ret=ls.getDistributedOrderDetails(action.get("type"),action.get("filterType"));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		//  ArrayList<HashMap<String,Object>> hs=new HashMap<>();
		 
	    return ret;
	  }
	 
	  @Path("/cancelOrderByAdmin")
	  @POST 
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)  
	  public HashMap<String,Integer> cancelOrderByAdmin(HashMap<String,String> action ) {  
		  
		 int ret=ls.cancelOrderByAdmin(action.get("orderId"));
	    //System.out.println(u.getContactNumber()+" "+u.getEmail()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPassword());
		//  ArrayList<HashMap<String,Object>> hs=new HashMap<>();
		 
		 HashMap<String, Integer> hs=new HashMap<>();
			
		  hs.put("response", ret);
		  
		  return hs;
	  }	  
	  
	 
}
