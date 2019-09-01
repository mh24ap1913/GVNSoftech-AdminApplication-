package in.co.brings.controller;

import in.co.brings.Beans.LoginBean;
import in.co.brings.service.LoginBeanService;

import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/loginBean")
public class LoginBeanController {

	@Path("/logBean")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String,Integer> LoginPage(LoginBean loginBean) throws Exception{
		LoginBeanService loginBeanSer=new LoginBeanService();
		int res=loginBeanSer.LoginPage(loginBean);
		
		
		HashMap<String,Integer> hs=new HashMap<String,Integer>();
		hs.put("response", res);
		return hs;
		
	}
	@Path("/signBean")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String,Integer> SignUp(HashMap<String,String> inputdetails) throws SQLException{
		LoginBeanService service=new LoginBeanService();
		int res=service.SignUp(inputdetails);
		
		HashMap<String,Integer> hs=new HashMap<String,Integer>();
		hs.put("response", res);
		return hs;
		
		
		
	}
	@Path("/Luser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String,Integer> LoginUser(HashMap<String,String> input) throws Exception{
		
		LoginBeanService loginBeanSer=new LoginBeanService();
		int res=loginBeanSer.LoginUser(input);
		
		HashMap<String,Integer> hs=new HashMap<String,Integer>();
		hs.put("response", res);
		return hs;
		
	}
}
