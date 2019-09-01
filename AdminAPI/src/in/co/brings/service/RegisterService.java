package in.co.brings.service;

import in.co.brings.Beans.RegisterBean;
import in.co.brings.DAO.RegisterDao;

public class RegisterService {
	RegisterDao registerDao=new RegisterDao();
	public int RegisterUser(RegisterBean register)throws Exception{
		return registerDao.RegisterUser(register);
		
	}

}
