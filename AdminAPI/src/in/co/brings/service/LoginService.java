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

import in.co.brings.Beans.BrngSupportUserBean;
import in.co.brings.DAO.LoginDAO;
import in.co.brings.DAO.UserManagementDAOImpl;  


 
public class LoginService {
	
	LoginDAO lgd=new LoginDAO();
	public int signUpUser(BrngSupportUserBean brngsupportuserbean) throws ClassNotFoundException, SQLException
	{
		//String ret=lgd.signUpUser(brngsupportuserbean);
		return lgd.signUpUser(brngsupportuserbean);
	}
	
	public int loginUser(HashMap<String,String> inputDetails) throws SQLException, ClassNotFoundException  {
	
		return lgd.loginUser(inputDetails);
		//return ret;
	}
	
	

	
	
	
	 
}   
	
	
	


