package in.co.brings.service;

import in.co.brings.DAO.LOKUPFunctionDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class LOKUPFunctionService {
	
	LOKUPFunctionDAO lookupDao=new LOKUPFunctionDAO();
	
	public ArrayList<HashMap<String,String>> getlookUpFileFath(String action,String type){
		
		ArrayList<HashMap<String,String>> ret=lookupDao.getlookUpFilePath(action, type);
		return ret;
		
	}
	public ArrayList<HashMap<String,String>> updatelookUpFilePath(String action,String type){
		
		ArrayList<HashMap<String,String>> ret=lookupDao.getlookUpFilePath(action, type);
		return ret;
		
	}

}
