package in.co.brings.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.co.brings.Beans.Student;
import in.co.brings.utility.JDBCConnection;

public class StudentDao {
	
	JDBCConnection Conref=new JDBCConnection();
	Connection con=null;
	PreparedStatement pstmt=null;
	public int studentInsert(Student st) throws SQLException {
		con=Conref.getOracleConnection();
		
		pstmt=con.prepareStatement("insert into student values(?,?,?)");
		pstmt.setInt(1,st.getId());
		pstmt.setString(2,st.getName());
		pstmt.setString(3,st.getAddress());
		
		int res=pstmt.executeUpdate();
		if(res==0) {
			System.out.println("values saved successfully");
		}
		else {
			System.out.println("values not saved");
		}
		return res;
	}

}
