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

import in.co.brings.DAO.LookUpFunctionDAO;
import in.co.brings.DAO.UserManagementDAOImpl;  


 
public class LookUpFunctionService {
	
	
	public ArrayList<HashMap<String,String>> getlookUpFilePath(String action,String type)
	{
		LookUpFunctionDAO lfd=new LookUpFunctionDAO();
		ArrayList<HashMap<String,String>> ret=lfd.getlookUpFilePath(action,type);
		return ret;
	}
	
	public String updatelookUpFilePath(String action,String type)
	{
		LookUpFunctionDAO lfd=new LookUpFunctionDAO();
		String ret=lfd.updatelookUpFilePath(action,type);
		return ret;
	}
	
	/*public HashMap<String,Object> getPersonInfo(String action,String email)
	{
		HashMap<String,Object> ret=lgd.getPersonInfo(action,email);
		return ret;
	}*/
	
	
	
	 
}   
	
	
	


