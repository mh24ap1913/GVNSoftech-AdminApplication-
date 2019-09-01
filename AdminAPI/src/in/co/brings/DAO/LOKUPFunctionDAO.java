package in.co.brings.DAO;

import in.co.brings.utility.GetColumnNames;
import in.co.brings.utility.GetConfigValues;
import in.co.brings.utility.JDBCConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class LOKUPFunctionDAO {
	Connection con=null;
	Statement st=null;
	PreparedStatement pstmt=null;
	JDBCConnection connref=new JDBCConnection();
	ResultSet rs=null;
	Properties prop=new Properties();
	InputStream input = null;
	GetConfigValues gfc=new GetConfigValues();
	GetColumnNames gc=new GetColumnNames();
	
	
	public ArrayList<HashMap<String,String>> getlookUpFilePath(String action,String type){
	
		String query=null;
		String key=null;
		ArrayList<HashMap<String,String>> al=new ArrayList<>();
		try{
			con=connref.getOracleConnection();
			if(con!=null){
				key="fileselect";
				if(action.equalsIgnoreCase("fileselect")){
					query=gfc.getQueryFromConfig(key);
					pstmt=con.prepareStatement(query);
					System.out.println("query:-"+query);
					rs=pstmt.executeQuery();
					while(rs.next()){
						HashMap<String,String> hmap=new HashMap<>();
						hmap.put("path",rs.getString("file_path"));
						hmap.put("type", rs.getString("type"));
						al.add(hmap);
						
					}
					
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			try{
				con.close();
			} 
			catch(SQLException se){
				se.printStackTrace();
			}
			finally{
				try{
					con.close();
				}
				catch(Exception ae){
					ae.printStackTrace();
				}
			}
		}
		return al;
		
	}
	
	public String updatelookUpFilePath(String action,String type){
		
		String key=null;
		String query=null;
		ArrayList<HashMap<String,String>> al=new ArrayList<>();
		try{
			con=connref.getOracleConnection();
			
			if(action.equalsIgnoreCase("fileUpdate")){
				key="fileUpdate";
				query=gfc.getQueryFromConfig(key);
				System.out.println("query:-"+query);
				pstmt=con.prepareStatement(query);
				System.out.println(type.split("&")[0]);
				pstmt.setString(1,type.split("&")[0]);
				pstmt.setString(2, type.split("$")[1]);
			    pstmt.executeQuery();
				
			}
			else if(action.equalsIgnoreCase("fileAdd")){
				key="fileAdd";
				query=gfc.getQueryFromConfig(key);
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, type.split("&")[1]);
				pstmt.setString(2, type.split("&")[0]);
				pstmt.executeUpdate();
			}
			else if(action.equalsIgnoreCase("filedelete")){
				key="filedelete";
				query=gfc.getQueryFromConfig(key);
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, type);
				pstmt.execute();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			try{
				con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			finally{
				try{
					con.close();
				}catch(Exception ae){
					ae.printStackTrace();
				}
			}
		}
		return type;
		
	}

}
