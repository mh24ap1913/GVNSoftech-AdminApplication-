package in.co.brings.DAO;

import in.co.brings.Beans.RegisterBean;
import in.co.brings.utility.JDBCConnection;

import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;

public class RegisterDao {
	Connection con=null;
	PreparedStatement pstmt=null;
	String query=null;
	int res=0;
	JDBCConnection connref=new JDBCConnection();
	
	public int RegisterUser(RegisterBean register)
	{
		try{
			con=connref.getOracleConnection();
			query="insert into register (username,firstname,lastname,email_id,password) values(?,?,?,?,?)";
			pstmt=(PreparedStatement) con.prepareStatement(query);
			pstmt.setString(1, register.getUsername());
			pstmt.setString(2,register.getFirstname());
			pstmt.setString(3, register.getEmail_id());
			pstmt.setString(4, register.getPassword());
			pstmt.setString(5, register.getLastname());
			res=pstmt.executeUpdate();
			if(res==0){
				System.out.println("value not saved");
			}
			else{
				System.out.println("value saved succefully");
			}
			
		}catch(Exception e){
			System.out.println("internal error:-"+e);
		}
		return res;
		
	}

}
