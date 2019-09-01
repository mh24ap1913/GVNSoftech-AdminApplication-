package in.co.brings.controller;

import in.co.brings.service.LOKUPFunctionService;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/select")
public class LOKUPFunctionController {
	LOKUPFunctionService service=new LOKUPFunctionService();

	@Path("/selectFile")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	 public ArrayList<HashMap<String,String>> lookUpFilePath(HashMap<String,String> action){
		
		ArrayList<HashMap<String,String>> ret=service.getlookUpFileFath(action.get("action"),action.get("type"));
		HashMap<String,String> hs=new HashMap<>();
		return ret;
		
	}
	
	@Path("/updateFile")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<HashMap<String, String>> updatelookUpFilePath(HashMap<String,String> action){
		
		ArrayList<HashMap<String,String>> ret=service.updatelookUpFilePath(action.get("action"),action.get("type"));
		return ret;
		
	}
	
	

}
