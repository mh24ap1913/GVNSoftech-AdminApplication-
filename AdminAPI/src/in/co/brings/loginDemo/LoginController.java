package in.co.brings.loginDemo;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/signUpuser1")
public class LoginController {
	LoginService service=new LoginService();
	@Path("/signUpuser2")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Integer> signUp(LoginBean bean) throws Exception{
		
		int res=service.signUp(bean);
		
		HashMap<String,Integer> hs=new HashMap<>();
		hs.put("response", res);
		return hs;
		
	}
	
	@Path("/loginUser1")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, Integer> loginUser(HashMap<String,String> input)throws Exception{
	
		int res=service.loginUser(input);
		
		HashMap<String,Integer> hs=new HashMap<>();
		hs.put("response", res);
		
		return hs;
		
	}

}
