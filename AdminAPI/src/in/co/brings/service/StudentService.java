package in.co.brings.service;

import java.sql.SQLException;

import in.co.brings.Beans.Student;
import in.co.brings.DAO.StudentDao;

public class StudentService {
	
	
	StudentDao dao=new StudentDao();
	
	public int studentInsert(Student st) throws SQLException {
		return dao.studentInsert(st);
		
	}

}
