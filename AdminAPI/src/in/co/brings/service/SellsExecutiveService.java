package in.co.brings.service;

import java.sql.SQLException;

import in.co.brings.Beans.SellsExecutive;
import in.co.brings.DAO.SellsExecutiveDao;

public class SellsExecutiveService {
	
	public int registeruser(SellsExecutive register) throws ClassNotFoundException, SQLException
	{
		SellsExecutiveDao dao=new SellsExecutiveDao();
		return  dao.registerUser(register);
		
	}

}
