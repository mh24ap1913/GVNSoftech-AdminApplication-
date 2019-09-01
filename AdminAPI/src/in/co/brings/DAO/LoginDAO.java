package in.co.brings.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Properties;

import in.co.brings.Beans.BrngSupportUserBean;
import in.co.brings.utility.GetColumnNames;
import in.co.brings.utility.GetConfigValues;
import in.co.brings.utility.JDBCConnection;

public class LoginDAO {

	
	Connection con;
	Statement stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	JDBCConnection connref=new JDBCConnection();
	Properties prop = new Properties();
	InputStream input = null;
	GetConfigValues gfc=new GetConfigValues();
	GetColumnNames gc=new GetColumnNames();
	
	public int signUpUser(BrngSupportUserBean brngsupportuserbean) throws SQLException, ClassNotFoundException  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		String tempKey="";
		int ret=0;
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnection();
		
		if(con!=null)
		{
			Timestamp ts=new Timestamp(System.currentTimeMillis());
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			//int status=getComplaintStatus(con,complaintDetails.get("statusType"));
			
			 query="insert into brng_support_user(emp_id,email,password,auth_level,effective_date) values(?,?,?,?,?)";
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				 pstmt.setInt(1,brngsupportuserbean.getEmpId());
				 pstmt.setString(2,brngsupportuserbean.getEmail());
				 pstmt.setString(3,brngsupportuserbean.getPassword());
				 if(brngsupportuserbean.getEmail().equalsIgnoreCase("admin@brings.co.in"))
				 {
					 pstmt.setString(4,"A");
				 }
				 else
				 {
					 pstmt.setString(4,"P");	 
				 }
				 
				 pstmt.setTimestamp(5,ts);
				// pstmt.setString(6,"BRNG_LOG"+count1);

			ret=pstmt.executeUpdate();
			
	}
		}
		finally
		{
			
		}
		return ret;
		}
	
	public int loginUser(HashMap<String,String> inputDetails) throws SQLException, ClassNotFoundException  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		String tempKey="";
		ResultSet rs=null;
		int ret=0;
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnection();
		
		if(con!=null)
		{
			Timestamp ts=new Timestamp(System.currentTimeMillis());
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			//int status=getComplaintStatus(con,complaintDetails.get("statusType"));
			
				query="select * from brng_support_user where email=? and password=?";
				 pstmt=con.prepareStatement(query);
				 pstmt.setString(1,inputDetails.get("mail"));
				 pstmt.setString(2,inputDetails.get("password"));
				 rs=pstmt.executeQuery();
				 while(rs.next())
				 {
					 return 1; 
				 }
		
			 
				 
				// pstmt.setTimestamp(5,ts);
				// pstmt.setString(6,"BRNG_LOG"+count1);

		//	ret=pstmt.executeUpdate();
			
	}
		}
		finally
		{
			con.close();
		}
		return -1;
		}
}
