package in.co.brings.DAO;

import in.co.brings.Beans.LoginBean;
import in.co.brings.utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class LoginBeanDao {
	JDBCConnection connref=new JDBCConnection();
	PreparedStatement pstmt=null;
	Connection con=null;
	String query=null;
	int res=0;
	ResultSet rs=null;
	
	public int LoginPage(LoginBean loginBean)throws Exception{
		
		try{
			con=connref.getOracleConnection();
			query="insert into userLogin values(?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, loginBean.getEmail());
			pstmt.setString(2, loginBean.getPassword());
			
			res=pstmt.executeUpdate();
			if(res==0){
				System.out.println("Value Not inserted ");
			}
			else{
				System.out.println("Value  inserted Successfully");
			}
			
			
		}catch(Exception e){
			System.out.println("internal error"+e);
		}
		
		return res;
		
	}
	
	public int SignUp(HashMap<String,String> inputdetails) throws SQLException{
		try{
			query="select * from userLogin where email=? and password=?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, inputdetails.get("email"));
			pstmt.setString(2, inputdetails.get("password"));
			while(rs.next()){
				return 1;
			}
		
		}finally{
			con.close();
		}
		return -1;
	
		
	}
	public int LoginUser(HashMap<String,String> input){
		
		String query=null;
		try{
			con=connref.getOracleConnection();
			query="select * from brng_support_user where email=? and password=?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,input.get("email"));
			pstmt.setString(2, input.get("password"));
			rs=pstmt.executeQuery();
			while(rs.next()){
				return 1;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return -1;
		
	}
}
