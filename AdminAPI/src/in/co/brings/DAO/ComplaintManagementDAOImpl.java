package in.co.brings.DAO;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import in.co.brings.utility.GetColumnNames;
import in.co.brings.utility.GetConfigValues;
import in.co.brings.utility.JDBCConnection;


public class ComplaintManagementDAOImpl {
	
	Connection con;
	Statement stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	JDBCConnection connref=new JDBCConnection();
	Properties prop = new Properties();
	InputStream input = null;
	GetConfigValues gfc=new GetConfigValues();
	GetColumnNames gc=new GetColumnNames();
	

	
	public int logComplaint(HashMap<String,String> complaintDetails) throws SQLException, ClassNotFoundException  {
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
			int type=getComplaintType(con,complaintDetails.get("type"));
			int status=getComplaintStatus(con,"L");
			int userId=getUserId(complaintDetails.get("email"));
			int count1=getComplaintCount(con);
			 query="insert into brng_complain(user_id,complain_type,complain,status,effective_date,complaint_id) values(?,?,?,?,?,?)";
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				 pstmt.setInt(1,userId);
				 pstmt.setInt(2,type);
				 pstmt.setString(3,complaintDetails.get("complaint"));
				 pstmt.setInt(4,status);
				 pstmt.setTimestamp(5,ts);
				 pstmt.setString(6,"BRNG_LOG"+count1);

			ret=pstmt.executeUpdate();
			 query="insert into brng_complain_log(complain_id,complain,effective_date) values(?,?,?)";
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				// pstmt.setInt(1,userId);
				 pstmt.setString(1,"BRNG_LOG"+count1);
				 pstmt.setString(2,complaintDetails.get("complaint"));
				 //pstmt.setInt(4,status);
				 pstmt.setTimestamp(3,ts);

			ret=pstmt.executeUpdate();
			
	}
		}
		finally
		{
			
		}
		return ret;
		}
	
	public int updateComplaint(HashMap<String,String> complaintDetails) throws SQLException, ClassNotFoundException  {
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
			int type=getComplaintType(con,complaintDetails.get("type"));
			int status=getComplaintStatus(con,complaintDetails.get("status"));
			//int count1=getComplaintCount(con);
			//int userId=getUserId(complaintDetails.get("email"));
			 query="update brng_complain set complain_type=?,status=? where complaint_id=?";
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				// pstmt.setInt(1,userId);
				 pstmt.setInt(1,type);
				// pstmt.setString(2,complaintDetails.get("complaint"));
				 pstmt.setInt(2,status);
			//	 pstmt.setString(3, complaintDetails.get("complaint"));
				 pstmt.setString(3,complaintDetails.get("complaintId"));
				// pstmt.setTimestamp(5,ts);

			ret=pstmt.executeUpdate();
			
			 query="insert into brng_complain_log(complain_id,complain,effective_date) values(?,?,?)";
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				 pstmt.setString(1,complaintDetails.get("complaintId"));
				 pstmt.setString(2,complaintDetails.get("complaint"));
				 //pstmt.setString(3,complaintDetails.get("complaint"));
				// pstmt.setInt(4,status);
				 pstmt.setTimestamp(3,ts);

			ret=pstmt.executeUpdate();
			//con.commit();
			
	}
		}
		finally
		{
			
		}
		return ret;
		}

/*	public  ArrayList<HashMap<String,String>> getTickets() throws SQLException, ClassNotFoundException  {
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
			int type=getComplaintType(con,complaintDetails.get("type"));
			int status=getComplaintStatus(con,"L");
			//int userId=getUserId(complaintDetails.get("email"));
			 query="update brng_complain set complain_type=?,status=? where id=?";
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				// pstmt.setInt(1,userId);
				 pstmt.setInt(1,type);
				// pstmt.setString(2,complaintDetails.get("complaint"));
				 pstmt.setInt(2,status);
				// pstmt.setTimestamp(5,ts);

			ret=pstmt.executeUpdate();
			
			 query="insert into brng_complain_log(complain_id,comment,effective_date) values(?,?,?)";
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				 pstmt.setInt(1,Integer.parseInt(complaintDetails.get("id")));
				 pstmt.setString(2,complaintDetails.get("comment"));
				 //pstmt.setString(3,complaintDetails.get("complaint"));
				// pstmt.setInt(4,status);
				 pstmt.setTimestamp(3,ts);

			ret=pstmt.executeUpdate();
			
			
	}
		}
		finally
		{
			
		}
		return ret;
		}
	*/
	public int getComplaintStatus(Connection con,String status)
	
	{
		
		int statusId=0;
		try
		{
	
		String query="select id from brng_complain_status_lkp where status=?";
		PreparedStatement pstmt=null;
		pstmt=con.prepareStatement(query);
		 pstmt.setString(1,status);

		 rs=pstmt.executeQuery();
		 while(rs.next())
		 {
			 statusId=rs.getInt(1); 
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statusId;
	}
	
public String getComplaintStatusToString(Connection con,String id)
	
	{
		
		String status="";
		try
		{
	
		String query="select description from brng_complain_status_lkp where id=?";
		PreparedStatement pstmt=null;
		pstmt=con.prepareStatement(query);
		 pstmt.setInt(1,Integer.parseInt(id));

		ResultSet rs=pstmt.executeQuery();
		 while(rs.next())
		 {
			 status=rs.getString(1); 
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
		
public int getComplaintCount(Connection con)
	
	{
		
		int count=0;
		try
		{
	
		String query="select count(*) from brng_complain";
		PreparedStatement pstmt=null;
		pstmt=con.prepareStatement(query);
		// pstmt.setString(1,status);

		 rs=pstmt.executeQuery();
		 while(rs.next())
		 {
			 count=rs.getInt(1); 
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}
public int getComplaintType(Connection con,String status)
	
	{
		
		int typeId=0;
		try
		{
	
		String query="select id from brng_complain_type_lkp where type=?";
		PreparedStatement pstmt=null;
		pstmt=con.prepareStatement(query);
		 pstmt.setString(1,status);

		 rs=pstmt.executeQuery();
		 while(rs.next())
		 {
			 typeId=rs.getInt(1); 
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return typeId;
	}
	
public String getComplaintTypeToString(Connection con,String status)

{
	
	String type="";
	try
	{

	String query="select description from brng_complain_type_lkp where id=?";
	PreparedStatement pstmt=null;
	pstmt=con.prepareStatement(query);
	 pstmt.setInt(1,Integer.parseInt(status));

	ResultSet rs=pstmt.executeQuery();
	 while(rs.next())
	 {
		 type=rs.getString(1); 
	 }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return type;
}

public int getUserId(String email)

{
	
	int userId=0;
	try
	{
Connection con1=connref.getOracleConnectionFromMain();
	String query="select id from brng_usr_reg where email_id=?";
	PreparedStatement pstmt=null;
	pstmt=con1.prepareStatement(query);
	 pstmt.setString(1,email);

	 rs=pstmt.executeQuery();
	 while(rs.next())
	 {
		 userId=rs.getInt(1); 
	 }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return userId;
}


public String getMail(int userId)

{
	String mail="";
	//int userId=0;
	try
	{
Connection con1=connref.getOracleConnectionFromMain();
	String query="select email_id from brng_usr_reg where id=?";
	PreparedStatement pstmt=null;
	pstmt=con1.prepareStatement(query);
	 pstmt.setInt(1,userId);

	ResultSet rs=pstmt.executeQuery();
	 while(rs.next())
	 {
		 mail=rs.getString(1); 
	 }
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return mail;
}

public ArrayList<HashMap<String,Object>> getComplaintDetails(String email,String status)  {
	int countUser=0;
	PreparedStatement pstmt=null;
	String key="";
	String query="";
	String count="";
	String tempKey="";
	int userId=0;
	ArrayList<HashMap<String,Object>> al1=new ArrayList<>();
	try{
		//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnection();
		if(!email.equalsIgnoreCase("NA"))
		{
		 userId=getUserId(email);
		}
		int statusId=getComplaintStatus(con, status);
	if(con!=null)
	{
		  
		//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
		
		
		if(!email.equalsIgnoreCase("NA"))
		{
		 query="select * from brng_complain where user_id=? and status=?";
		 pstmt=con.prepareStatement(query);
		 pstmt.setInt(1,userId);
		 pstmt.setInt(2,statusId);
		}
		else
		{
			query="select * from brng_complain where status=?";
			 pstmt=con.prepareStatement(query);
			 pstmt.setInt(1,statusId);
			
		}
		// System.out.println("query "+query +" "+orderId);
			
			 rs=pstmt.executeQuery();
			// ArrayList<String> al=getColumnNames(rs);
				while(rs.next())
				{
					System.out.println("inside list");
					HashMap< String, Object> hmap=new HashMap<>();
					
						hmap.put("complaint_id", rs.getObject("complaint_id"));
						hmap.put("user_id", email);
					
					al1.add(hmap);
				}
		}
		
				}
	
	catch(Exception e)
	{
		
		e.printStackTrace();
		try {
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//return al1;
	}
	finally
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return al1;
}




public ArrayList<HashMap<String,Object>> getComplaint(String complaintId)  {
	int countUser=0;
	PreparedStatement pstmt=null;
	String key="";
	String query="";
	String count="";
	String tempKey="";
	int userId=0;
	ArrayList<HashMap<String,Object>> al1=new ArrayList<>();
	try{
		//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnection();
		
		
	if(con!=null)
	{
		  
		//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
		
		
		
		 query="select * from brng_complain where complaint_id=?";
		 pstmt=con.prepareStatement(query);
		 pstmt.setString(1,complaintId);
		// pstmt.setInt(2,statusId);
		
		// System.out.println("query "+query +" "+orderId);
			
			 rs=pstmt.executeQuery();
			// ArrayList<String> al=getColumnNames(rs);
				while(rs.next())
				{
					System.out.println("inside list");
					HashMap< String, Object> hmap=new HashMap<>();
					
						hmap.put("complaint_id", rs.getObject("complaint_id"));
						hmap.put("logged_date", rs.getObject("effective_date"));
						
						hmap.put("user_id", getMail(Integer.parseInt((rs.getObject("user_id").toString()))));
						hmap.put("complain_type", getComplaintTypeToString(con, rs.getObject("complain_type").toString()));
						//hmap.put("complain_type",  rs.getObject("complain_type"));
						hmap.put("status", getComplaintStatusToString(con,(rs.getObject("status").toString())));
						hmap.put("complain",rs.getObject("complain") );
						 query="select * from brng_complain_log where complain_id=? order by effective_date desc limit 1";
						 pstmt=con.prepareStatement(query);
						 pstmt.setString(1,rs.getObject("complaint_id").toString());
						 ResultSet rs1=pstmt.executeQuery();
						 while(rs1.next())
							{
							 hmap.put("last_update",rs1.getObject("complain") );
							 hmap.put("last_update_date",rs1.getObject("effective_date") );
							}
					al1.add(hmap);
				}
		}
		
				}
	
	catch(Exception e)
	{
		
		e.printStackTrace();
		try {
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//return al1;
	}
	finally
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		return al1;
}
}
