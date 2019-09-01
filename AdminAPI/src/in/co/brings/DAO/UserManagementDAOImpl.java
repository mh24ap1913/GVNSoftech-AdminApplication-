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
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;



import in.co.brings.utility.GetColumnNames;
import in.co.brings.utility.GetConfigValues;
import in.co.brings.utility.JDBCConnection;


public class UserManagementDAOImpl {
	
	Connection con;
	Statement stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	JDBCConnection connref=new JDBCConnection();
	Properties prop = new Properties();
	InputStream input = null;
	GetConfigValues gfc=new GetConfigValues();
	GetColumnNames gc=new GetColumnNames();
	
	public String getGeneralInfo(String action)  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		String tempKey="";
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			
			if(action.equalsIgnoreCase("ALL"))
			{
				key="noOfusers";
				 query=gfc.getQueryFromConfig(key);
				 pstmt=con.prepareStatement(query);
				 rs=pstmt.executeQuery();
					while(rs.next())
					{
						count=rs.getString(1);
					}
			}
			else if(action.equalsIgnoreCase("LOGGED"))
			{
				
				key="noOfLoggedinUsers";
				 query=gfc.getQueryFromConfig(key);
				 pstmt=con.prepareStatement(query);
				 rs=pstmt.executeQuery();
					while(rs.next())
					{
						count=rs.getString(1);
					}
			}
			else if(action.equalsIgnoreCase("BUYERS"))
			{
				key="customerKey";
			 query=gfc.getQueryFromConfig(key);
			 pstmt=con.prepareStatement(query);
			 rs=pstmt.executeQuery();
				while(rs.next())
				{
					tempKey=rs.getString(1);
				}
				key="noOfBuyers";
				 query=gfc.getQueryFromConfig(key);
				 
				 pstmt=con.prepareStatement(query);
				 pstmt.setString(1,tempKey);
				 rs=pstmt.executeQuery();
					while(rs.next())
					{
						count=rs.getString(1);
					}
			}
			else if(action.equalsIgnoreCase("SERVICEMAN"))
			{
				key="serviceManKey";
				 query=gfc.getQueryFromConfig(key);
				 pstmt=con.prepareStatement(query);
				 rs=pstmt.executeQuery();
					while(rs.next())
					{
						tempKey=rs.getString(1);
					}
				key="noOfServiceMan";
				query=gfc.getQueryFromConfig(key);
				 pstmt=con.prepareStatement(query);
				 pstmt.setString(1,tempKey);
				 rs=pstmt.executeQuery();
					while(rs.next())
					{
						count=rs.getString(1);
					}
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
			return "error";
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
			return count;
	}

	public ArrayList<HashMap<String,Object>> getPersonInfo(String action,String email)  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		
		ArrayList<String> columnNames=new ArrayList<>();
		ArrayList<HashMap<String,Object>> al=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			if(action.equalsIgnoreCase("USER_DETAIL"))
			{
				key="userDetails";
			}
			
			
			 System.out.println("email "+email);
			 query="select a.*,b.* from BRNG_USR_REG a,BRNG_USR_REG_ATTR b where a.id=b.usr_reg_id and a.email_id=?";
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				 pstmt.setString(1,email);

			rs=pstmt.executeQuery();
			if(rs!=null)
			{
				columnNames=gc.getColumnNames(rs);
			}
			while(rs.next())
			{
				
				HashMap<String,Object> data=new HashMap<>();
				for(int i=0;i<columnNames.size();i++)
				{
					if(columnNames.get(i).equalsIgnoreCase("registered_date") || columnNames.get(i).equalsIgnoreCase("effective_date"))
					{
					//data.put(columnNames.get(i), rs.getDate(columnNames.get(i)));
					}
					
					else
					{
						data.put(columnNames.get(i), rs.getObject(columnNames.get(i)));
					}
				}
				data.put("totalDistance", getTotalDistance(getLoginFromEmail(email)));
				al.add(data);
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
			return al;
	}

	public HashMap<String,Object> getPersonInfoBasedOnLogin(int loginId)  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		HashMap<String,Object> data=new HashMap<>();
		ArrayList<String> columnNames=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			query="select email_id from BRNG_USR_REG where id in (select BRNG_USR_REG from BRNG_USR_LOGIN where id=?)";
			
			
			 System.out.println("key "+key);
			// query=gfc.getQueryFromConfig(key);
			// System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				 pstmt.setInt(1,loginId);

			rs=pstmt.executeQuery();
			if(rs!=null)
			{
				columnNames=gc.getColumnNames(rs);
			}
			while(rs.next())
			{
				for(int i=0;i<columnNames.size();i++)
				{
					data.put(columnNames.get(i), rs.getObject(columnNames.get(i)));
				}
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
			return data;
	}

	
	public int getLoginFromEmail(String email)  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		int loginId=0;
		HashMap<String,Object> data=new HashMap<>();
		ArrayList<String> columnNames=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			query="select id from brng_usr_login where usr_reg_id in (select id from brng_usr_reg where email_id=?)";
			
			
			 System.out.println("key "+key);
			// query=gfc.getQueryFromConfig(key);
			// System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				 pstmt.setString(1,email);

			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				loginId=rs.getInt(1);
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
			return loginId;
	}

	
	public ArrayList<HashMap<String,Object>> getPendingServiceMan()  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		HashMap<String,Object> data=new HashMap<>();
		ArrayList<String> columnNames=new ArrayList<>();
		ArrayList<HashMap<String,Object>> al1=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			query="select a.email_id,a.id,b.aadhar,b.vehicle_rc,b.pvin,b.driving_lic,b.image from BRNG_USR_REG a,brng_usr_files b where a.id=b.usr_reg_id and  a.validated_id=2";
			
			
			 System.out.println("key "+key);
			// query=gfc.getQueryFromConfig(key);
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				 

			rs=pstmt.executeQuery();
			if(rs!=null)
			{
				columnNames=gc.getColumnNames(rs);
			}
			while(rs.next())
			{
				HashMap< String, Object> hmap=new HashMap<>();
				for(int i=0;i<columnNames.size();i++)
				{
					hmap.put(columnNames.get(i), rs.getObject(columnNames.get(i)));
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
	public int acceptServiceman(String action,String email)  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		int ret=-999;
		ArrayList<String> columnNames=new ArrayList<>();
		ArrayList<HashMap<String,Object>> al=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			
			 System.out.println("email "+email);
			 query="update BRNG_USR_REG set validated_id=1 where email_id=?";
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				 pstmt.setString(1,email);

			 ret=pstmt.executeUpdate();
			
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
			return ret;
	}
	
	public Object getTotalDistance(int loginId )
	{
		//System.out.println("Email"+email);
		float totalDistance=0;
		PreparedStatement pstmt=null;
		try {
			con = connref.getOracleConnectionFromMain();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			String query="select sum(total_distance) from brng_order where id in(select order_id from brng_order_delivery where usr_login_id=?)";
			 pstmt=con.prepareStatement(query);
			 pstmt.setInt(1,loginId);

		 ResultSet rs1=pstmt.executeQuery();
		 while(rs1.next())
			{
			return rs1.getObject(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//return -1;
		}
		return totalDistance;
	}
}
