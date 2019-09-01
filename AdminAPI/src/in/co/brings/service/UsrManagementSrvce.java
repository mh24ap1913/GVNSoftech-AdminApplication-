package in.co.brings.service;

import in.co.brings.DAO.OrderManagementDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class UsrManagementSrvce {
	OrderManagementDAO omd=new OrderManagementDAO();
	public ArrayList<HashMap<String ,Object>> getOrderDetails(String action){
		
		ArrayList<HashMap<String,Object>> ret=omd.getOrderDetails(action);
		return ret;
		
	}
	public ArrayList<HashMap<String,Object>> getOrderDetailsByMail(String email){
		
		ArrayList<HashMap<String, Object>> ret=omd.getOrderDetailsByEmail(email);
		return ret;
	}
	public ArrayList<HashMap<String,Object>> getOrderByDate(HashMap<String,String> input){
		
		ArrayList<HashMap<String, Object>> ret=omd.getOrdersByDate(input);
		return ret;
		
	}
	public ArrayList<HashMap<String,Object>> getOredrsByFilter(HashMap<String,String> input){
		
		ArrayList<HashMap<String, Object>> ret=omd.getOrdersByFilter(input);
		return ret;
		
	}
	public ArrayList<HashMap<String,Object>> getDistributedOrderDetails(String type,String filterType){
		ArrayList<HashMap<String,Object>> ret=omd.getDistributedOrderDetails(type, filterType);
		return ret;
		
	}
	public int cancelOrderByAdmin(String orderId)
	{
		int ret=omd.cancelOrderByAdmin(orderId);
		return ret;
	}
	


}
