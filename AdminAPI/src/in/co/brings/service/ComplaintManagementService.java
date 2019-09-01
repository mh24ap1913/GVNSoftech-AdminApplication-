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

import in.co.brings.DAO.ComplaintManagementDAOImpl;
import in.co.brings.DAO.UserManagementDAOImpl;  


 
public class ComplaintManagementService {
	
	ComplaintManagementDAOImpl lgd=new ComplaintManagementDAOImpl();
	public int logComplaint(HashMap<String,String> complaintDetails) throws ClassNotFoundException, SQLException
	{
		int ret=lgd.logComplaint(complaintDetails);
		return ret;
	}
	
	public int updateComplaint(HashMap<String,String> complaintDetails) throws ClassNotFoundException, SQLException
	{
		int ret=lgd.updateComplaint(complaintDetails);
		return ret;
	}
	public ArrayList<HashMap<String,Object>> getComplaintDetails(String email,String status) throws ClassNotFoundException, SQLException
	{
		ArrayList<HashMap<String,Object>> ret=lgd.getComplaintDetails(email,status);
		return ret;
	}
	public ArrayList<HashMap<String,Object>> getComplaint(String complaintId) throws ClassNotFoundException, SQLException
	{
		ArrayList<HashMap<String,Object>> ret=lgd.getComplaint(complaintId);
		return ret;
	}
	
	
	
	 
}   
	
	
	


