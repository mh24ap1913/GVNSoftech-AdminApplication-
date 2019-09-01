package in.co.brings.controller;

import in.co.brings.Beans.MarketingExecutiveBean;
import in.co.brings.service.MarketingExecutiveService;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mpath")
public class MarketingExecutiveController 
{
	
	@Path("/mpath1")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String,Integer> registerUser(MarketingExecutiveBean bean) throws Exception{
		
		MarketingExecutiveService service=new MarketingExecutiveService();
		int res=service.registerUser(bean);
		
		
		HashMap<String,Integer> hm=new HashMap<>();
		hm.put("resp", res);
		
		return hm;
		
	}

}
