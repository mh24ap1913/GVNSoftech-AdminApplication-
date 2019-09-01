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

import in.co.brings.DAO.UserManagementDAOImpl;  


 
public class UserManagmentService {
	
	UserManagementDAOImpl lgd=new UserManagementDAOImpl();
	public String getGeneralInfo(String action)
	{
		String ret=lgd.getGeneralInfo(action);
		return ret;
	}
	
	public ArrayList<HashMap<String,Object>> getPersonInfo(String action,String email)
	{
		ArrayList<HashMap<String,Object>> ret=lgd.getPersonInfo(action,email);
		return ret;
	}
	
	public HashMap<String,Object> getPersonInfoBasedOnLogin(int loginId)
	{
		HashMap<String,Object> ret=lgd.getPersonInfoBasedOnLogin(loginId);
		return ret;
	}
	
	public int acceptServiceman(String op,String email)
	{
		int ret=lgd.acceptServiceman(op,email);
		return ret;
	}
	
	public ArrayList<HashMap<String,Object>> getPendingServiceMan()
	{
		ArrayList<HashMap<String,Object>> ret=lgd.getPendingServiceMan();
		return ret;
	}
	
	
	
	 
}   
	
	
	


