package in.co.brings.controller;

import in.co.brings.Beans.SellsExecutive;
import in.co.brings.service.SellsExecutiveService;

import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/reg")
public class SellsExecutiveController {
	@Path("/reg1")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String,Integer> registeruser(SellsExecutive register) throws ClassNotFoundException, SQLException
	{
		SellsExecutiveService service=new SellsExecutiveService();
		int res=service.registeruser(register);
		
		HashMap<String,Integer> hm=new HashMap<>();
		hm.put("response", res);
		return hm;
		
	}
}
