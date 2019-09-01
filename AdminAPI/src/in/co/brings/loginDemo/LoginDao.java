package in.co.brings.loginDemo;

import in.co.brings.utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;

public class LoginDao {
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	JDBCConnection connref=new JDBCConnection();
	
	public int signUp(LoginBean bean)throws Exception{
	String query=null;
	int res=0;
		try{
			con=connref.getOracleConnection();
			if(con!=null){
				Timestamp timestamp=new Timestamp(System.currentTimeMillis());
				query="insert into brng_support_user values(?,?,?,?,?)";
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, bean.setEmpId());
				pstmt.setString(2, bean.getEmail());
				pstmt.setString(3, bean.getPassword());
				pstmt.setString(4, bean.getAuthLevel());
				pstmt.setTimestamp(5,bean.getEffectiveDate());
				pstmt.setTimestamp(5, timestamp);
				res=pstmt.executeUpdate();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return res;
		
	}
	public int loginUser(HashMap<String,String> input)throws Exception{
		String query=null;
		int res=0;
		try{
			con=connref.getOracleConnection();
			
			if(con!=null){
				Timestamp timestamp=new Timestamp(System.currentTimeMillis());
			query="select * from brng_support_user where email=? and password=?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, input.get("mail"));
			pstmt.setString(2, input.get("password"));
			pstmt.setTimestamp(5,timestamp);
			rs=pstmt.executeQuery();
			while(rs.next()){
				return 1;
			}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return -1;
		
	}

}
