package in.co.brings.service;

import java.sql.SQLException;
import java.util.HashMap;

import in.co.brings.Beans.LoginBean;
import in.co.brings.DAO.LoginBeanDao;

public class LoginBeanService {
	
	LoginBeanDao beanDao=new LoginBeanDao();
	public int LoginPage(LoginBean loginBean) throws Exception{
		return beanDao.LoginPage(loginBean);
		
	}
	public int SignUp(HashMap<String,String> inputdetails) throws SQLException{
		return beanDao.SignUp(inputdetails);
		
	}
	public int LoginUser(HashMap<String, String> input)throws Exception{
		
		return beanDao.LoginUser(input);
		
	}

}
