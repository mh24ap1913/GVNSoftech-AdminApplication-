package in.co.brings.service;

import in.co.brings.Beans.MarketingExecutiveBean;
import in.co.brings.DAO.MarketingExecutiveDao;

public class MarketingExecutiveService {
	MarketingExecutiveDao dao=new MarketingExecutiveDao();
	public int registerUser(MarketingExecutiveBean bean) throws Exception{
		return dao.registerUser(bean);
		
		
	}

}
