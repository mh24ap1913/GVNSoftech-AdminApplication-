package in.co.brings.controller;

import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import in.co.brings.Beans.Student;
import in.co.brings.service.StudentService;

@Path("/stdnt")
public class StudentController {
	StudentService service=new StudentService();
	@Path("/stdnt1")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	HashMap<Integer,String> studentInsert(Student st) throws SQLException{
		
		int ret=service.studentInsert(st);
		
		
	HashMap<Integer,String> hm=new HashMap<>();	
	hm.put(ret,"response");
		return hm;
		
	}

}
