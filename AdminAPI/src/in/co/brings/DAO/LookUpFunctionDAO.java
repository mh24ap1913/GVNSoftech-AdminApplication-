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
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import in.co.brings.utility.GetColumnNames;
import in.co.brings.utility.GetConfigValues;
import in.co.brings.utility.JDBCConnection;


public class LookUpFunctionDAO {
	
	Connection con;
	Statement stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	JDBCConnection connref=new JDBCConnection();
	Properties prop = new Properties();
	InputStream input = null;
	GetConfigValues gfc=new GetConfigValues();
	GetColumnNames gc=new GetColumnNames();
	
	public ArrayList<HashMap<String,String>> getlookUpFilePath(String action,String type)  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		String tempKey="";
		ArrayList<HashMap<String,String>> al=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnection();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			
			if(action.equalsIgnoreCase("fileselect"))
			{
				key="fileselect";
				 query=gfc.getQueryFromConfig(key);
				 pstmt=con.prepareStatement(query);
				 System.out.println("query"+query);
				 rs=pstmt.executeQuery();
					while(rs.next())
					{
						HashMap<String,String> hmap=new HashMap<>();
						hmap.put("path", rs.getString("file_path"));
						hmap.put("type", rs.getString("type"));
						al.add(hmap);
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
			return al;
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

	public String updatelookUpFilePath(String action,String type)  {
		
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		String tempKey="";
		ArrayList<HashMap<String,String>> al=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnection();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			if(action.equalsIgnoreCase("fileUpdate"))
			{
				
				key="fileUpdate";
				 query=gfc.getQueryFromConfig(key);
				 pstmt=con.prepareStatement(query);
				 System.out.println(type.split("&")[0]);
				 pstmt.setString(1,type.split("&")[0]);
				 pstmt.setString(2,type.split("&")[1]);
				 
				 pstmt.execute();
						//count=rs.getString(1);
					}
			
			
			else if(action.equalsIgnoreCase("fileAdd"))
			{
				key="fileAdd";
			 query=gfc.getQueryFromConfig(key);
			 System.out.println("query"+query);
			 pstmt=con.prepareStatement(query);
			 pstmt.setString(1,type.split("&")[0]);
			 pstmt.setString(2,type.split("&")[1]);
			 pstmt.executeUpdate();
				
			}
			else if(action.equalsIgnoreCase("filedelete"))
			{
				key="filedelete";
				 query=gfc.getQueryFromConfig(key);
				 pstmt=con.prepareStatement(query);
				 pstmt.setString(1,type);
				 pstmt.execute();
				
					
				
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
			return "success";
	}

}
