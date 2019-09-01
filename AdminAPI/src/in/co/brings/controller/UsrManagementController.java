package in.co.brings.controller;

import in.co.brings.service.UsrManagementSrvce;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/usr")
public class UsrManagementController {
	UsrManagementSrvce service=new UsrManagementSrvce();
	@Path("/usrMgmt")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<HashMap<String,Object>> getOrderDetails(String action){
		
		ArrayList<HashMap<String,Object>> ret=service.getOrderDetails(action);
		return ret;
		
	}
	@Path("/bymail")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<HashMap<String,Object>> getOrderDetailsByMail(HashMap<String,String> action){
		
		ArrayList<HashMap<String,Object>> ret=service.getOrderDetailsByMail(action.get("email"));
		return ret;
	

}
	@Path("/date")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<HashMap<String,Object>> getOrdersbyDate(HashMap<String,String> action)
	{
		ArrayList<HashMap<String,Object>> ret=service.getOrderByDate(action);
		return ret;
		
	}
	@Path("/filter")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<HashMap<String,Object>> getOrdersByFilter(HashMap<String,String> action){
		
		ArrayList<HashMap<String, Object>> ret=service.getOredrsByFilter(action);
		return ret;
		
	}
	@Path("/cancel")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Integer> cancelOrdersByAdmin(HashMap<String,String> action){
		
		
		int res=service.cancelOrderByAdmin(action.get("orderId"));
		
		HashMap<String,Integer> hs=new HashMap<>();
		hs.put("", res);
		return hs;
		
	}
	
	
}
