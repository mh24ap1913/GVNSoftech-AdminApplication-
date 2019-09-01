package in.co.brings.DAO;






import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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


public class OrderManagementDAO {
	
	Connection con;
	Statement stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	JDBCConnection connref=new JDBCConnection();
	Properties prop = new Properties();
	InputStream input = null;
	GetConfigValues gfc=new GetConfigValues();
	GetColumnNames gc=new GetColumnNames();
	
	public ArrayList<HashMap<String,Object>> getOrderDetails(String orderId)  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		String tempKey="";
		ArrayList<HashMap<String,Object>> al1=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			
			 query=gfc.getQueryFromConfig("orderBasedOnId");
			 System.out.println("query "+query +" "+orderId);
				 pstmt=con.prepareStatement(query);
				 pstmt.setString(1,orderId);
				 rs=pstmt.executeQuery();
				 ArrayList<String> al=getColumnNames(rs);
					while(rs.next())
					{
						System.out.println("inside list");
						HashMap< String, Object> hmap=new HashMap<>();
						for(int  i=0;i<al.size();i++)
						{
							hmap.put(al.get(i), rs.getObject(al.get(i)));
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


	public ArrayList<HashMap<String,Object>> getDistributedOrderDetails(String type,String filterType)  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		String tempKey="";
		ArrayList<HashMap<String,Object>> al1=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			if(type.equalsIgnoreCase("current"))
			{
				if(filterType.equalsIgnoreCase("accepted"))
				{
				query="select * from BRNG_ORDER b,BRNG_ORDER_DELIVERY d where b.id=d.order_id and b.is_accepted='1' and d.order_del_status_id='2'";
				}
				else if(filterType.equalsIgnoreCase("paid"))
				{
				query="select * from BRNG_ORDER b,BRNG_ORDER_DELIVERY d where b.id=d.order_id and b.is_paid_id='1' and d.order_del_status_id='2'";
				}
				else if(filterType.equalsIgnoreCase("picked"))
				{
				query="select * from BRNG_ORDER b,BRNG_ORDER_DELIVERY d where b.id=d.order_id and b.is_picked_id='1' and d.order_del_status_id='2'";
				}
				else if(filterType.equalsIgnoreCase("NA"))
				{
					query="select * from BRNG_ORDER b,BRNG_ORDER_DELIVERY d where b.id=d.order_id  and d.order_del_status_id='2'";
				}
			}
			else if(type.equalsIgnoreCase("completed"))
			{
				query="select * from BRNG_ORDER b,BRNG_ORDER_DELIVERY d where b.id=d.order_id and d.order_del_status_id='2'";

			}
			else if(type.equalsIgnoreCase("pending"))
			{
				query="select * from BRNG_ORDER b where b.is_accepted='2'";
			}
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				// pstmt.setString(1,orderId);
				 rs=pstmt.executeQuery();
				 ArrayList<String> al=getColumnNames(rs);
					while(rs.next())
					{
						System.out.println("inside list");
						HashMap< String, Object> hmap=new HashMap<>();
						for(int  i=0;i<al.size();i++)
						{
							if(al.get(i).equalsIgnoreCase("is_picked_id") || al.get(i).equalsIgnoreCase("is_paid_id") || al.get(i).equalsIgnoreCase("is_accepted") || al.get(i).equalsIgnoreCase("is_cancelled_id"))
							{
								hmap.put(al.get(i), codeToText((Integer)rs.getObject(al.get(i))));
								
							}
							else
							{
							hmap.put(al.get(i), rs.getObject(al.get(i)));
							}
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
	
	public int cancelOrderByAdmin(String orderId)  {
		//int countUser=0;
		PreparedStatement pstmt=null;
	//	String key="";
		String query="";
		//String count="";
		int ret=-999;
		ArrayList<String> columnNames=new ArrayList<>();
		ArrayList<HashMap<String,Object>> al=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			
			// System.out.println("email "+email);
			 query="update BRNG_ORDER set is_cancelled_id=1 where id=?";
			 System.out.println("query "+query);
				 pstmt=con.prepareStatement(query);
				 pstmt.setInt(1, Integer.parseInt(orderId));

			 ret=pstmt.executeUpdate();
			 query="insert into brng_admin_cancel (order_id) values(?)";
			 pstmt=con.prepareStatement(query);
			 pstmt.setInt(1, Integer.parseInt(orderId));
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
	
	public ArrayList<HashMap<String,Object>> getOrderDetailsByEmail(String email)  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		String tempKey="";
		ArrayList<HashMap<String,Object>> al1=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
		con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			int loginId=-999;
			query=gfc.getQueryFromConfig("getLoginId");
			// System.out.println("query "+query +" "+orderId);
				 pstmt=con.prepareStatement(query);
				 pstmt.setString(1,email);
				 rs=pstmt.executeQuery();
				 while(rs.next())
				 {
					 loginId=rs.getInt(1);
				 }
			 query="select * from BRNG_ORDER where usr_login_id ="+loginId+" or id in(select order_id from BRNG_ORDER_DELIVERY where usr_login_id="+loginId+" )";
			 		
			 System.out.println("query "+query +" "+loginId);
				 pstmt=con.prepareStatement(query);
				 
				 rs=pstmt.executeQuery();
				 ArrayList<String> al=getColumnNames(rs);
				// String text="";
					while(rs.next())
					{
						System.out.println("inside list");
						HashMap< String, Object> hmap=new HashMap<>();
						for(int  i=0;i<al.size();i++)
						{
							
							if(al.get(i).equalsIgnoreCase("is_picked_id") || al.get(i).equalsIgnoreCase("is_paid_id") || al.get(i).equalsIgnoreCase("is_accepted") || al.get(i).equalsIgnoreCase("is_cancelled_id"))
							{
								hmap.put(al.get(i), codeToText((Integer)rs.getObject(al.get(i))));
								
							}
							else
							{
							hmap.put(al.get(i), rs.getObject(al.get(i)));
							}
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

	public ArrayList<HashMap<String,Object>> getOrdersByDate(HashMap<String,String> input)  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		String tempKey="";
		ArrayList<HashMap<String,Object>> al1=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			int loginId=-999;
			 query="select a.*,b.* from brng_order a,brng_order_delivery b where   a.order_time>=("+input.get("startDate")+") and a.order_time<=("+input.get("endDate")+")+1";
			 System.out.println("query "+query +" "+loginId);
				 pstmt=con.prepareStatement(query);
				 rs=pstmt.executeQuery();
				 ArrayList<String> al=getColumnNames(rs);
					while(rs.next())
					{
						System.out.println("inside list");
						HashMap< String, Object> hmap=new HashMap<>();
						for(int  i=0;i<al.size();i++)
						{
							hmap.put(al.get(i), rs.getObject(al.get(i)));
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

	
	public ArrayList<HashMap<String,Object>> getOrdersByFilter(HashMap<String,String> input)  {
		int countUser=0;
		PreparedStatement pstmt=null;
		String key="";
		String query="";
		String count="";
		String tempKey="";
		ArrayList<HashMap<String,Object>> al1=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			con = connref.getOracleConnectionFromMain();
		
		if(con!=null)
		{
			  
			//input = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			int loginId=-999;
			if(input.get("action").equalsIgnoreCase("Accepted"))
			{
			 query="select a.*,b.* from brng_order a,brng_order_delivery b where   a.is_accepted=1";
			}
			if(input.get("action").equalsIgnoreCase("Paid"))
			{
			 query="select a.*,b.* from brng_order a,brng_order_delivery b where    a.is_paid_id=1";
			}
			if(input.get("action").equalsIgnoreCase("Picked"))
			{
			 query="select a.*,b.* from brng_order a,brng_order_delivery b where   a.is_picked_id=1";
			}
			if(input.get("action").equalsIgnoreCase("Delivered"))
			{
			 query="select a.*,b.* from brng_order a,brng_order_delivery b where   b.order_del_status_id=1";
			}
			 System.out.println("query "+query +" "+loginId);
				 pstmt=con.prepareStatement(query);
				 rs=pstmt.executeQuery();
				 ArrayList<String> al=getColumnNames(rs);
					while(rs.next())
					{
						System.out.println("inside list");
						HashMap< String, Object> hmap=new HashMap<>();
						for(int  i=0;i<al.size();i++)
						{
							hmap.put(al.get(i), rs.getObject(al.get(i)));
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

	
	public ArrayList<String> getColumnNames(ResultSet resultSet)  {
		
		ArrayList<String> columnNames=new ArrayList<>();
		try{
			//Class.forName("com.mysql.jdbc.Driver");
			ResultSetMetaData metaData = resultSet.getMetaData();
			int count = metaData.getColumnCount(); //number of column
			String columnName[] = new String[count];

			for (int i = 1; i <= count; i++)
			{
			   columnName[i-1] = metaData.getColumnLabel(i);
			  // System.out.println(columnName[i-1]);
			   columnNames.add(metaData.getColumnLabel(i));
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
		System.out.println(columnNames);
			return columnNames;
	}
public String codeToText(int code)
{
	if(code ==2)
	{
		return "No";
	}
	else
	{
		return "Yes";
	}
}

}
