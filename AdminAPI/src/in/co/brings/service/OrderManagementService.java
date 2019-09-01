package in.co.brings.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;  
import javax.ws.rs.POST;
import javax.ws.rs.Path;  
import javax.ws.rs.Produces;  
import javax.ws.rs.core.MediaType;

import in.co.brings.DAO.OrderManagementDAOImpl;
import in.co.brings.DAO.UserManagementDAOImpl;  


 
public class OrderManagementService {
	
	OrderManagementDAOImpl lgd=new OrderManagementDAOImpl();
	public ArrayList<HashMap<String,Object>> getOrderDetails(String action)
	{
		ArrayList<HashMap<String,Object>> ret=lgd.getOrderDetails(action);
		return ret;
	}
	
	public ArrayList<HashMap<String,Object>> getOrderDetailsByEmail(String email)
	{
		ArrayList<HashMap<String,Object>> ret=lgd.getOrderDetailsByEmail(email);
		return ret;
	}
	
	public ArrayList<HashMap<String,Object>> getOrdersByDate(HashMap<String,String> input)
	{
		ArrayList<HashMap<String,Object>> ret=lgd.getOrdersByDate(input);
		return ret;
	}
	
	public ArrayList<HashMap<String,Object>> getOrdersByFilter(HashMap<String,String> input)
	{
		ArrayList<HashMap<String,Object>> ret=lgd.getOrdersByFilter(input);
		return ret;
	}
	
	public ArrayList<HashMap<String,Object>> getDistributedOrderDetails(String type,String filterType)
	{
		ArrayList<HashMap<String,Object>> ret=lgd.getDistributedOrderDetails(type,filterType);
		return ret;
	}
	
	public int cancelOrderByAdmin(String orderId)
	{
		int ret=lgd.cancelOrderByAdmin(orderId);
		return ret;
	}
	
	
	
	
	 
}   
	
	
	


