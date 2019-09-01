package in.co.brings.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCConnection {
	static Connection con;
	public static Connection getOracleConnection(){
		
	
	try{
	InitialContext ctx=new InitialContext();
	Context enContext= (Context) ctx.lookup("java:/comp/env");
	DataSource dataSource=(DataSource) enContext.lookup("jdbc/MyDB");
	//System.out.println(dataSource);
	con=dataSource.getConnection();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return con;

	}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
		
		Connection con=getOracleConnection();
		System.out.println(con);
	}
	
	public static Connection getOracleConnectionFromMain()
			throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");  
		  
		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/brings_admin?noAccessToProcedureBodies=true","root","root");  
   

		return con;

	}
	
	
}
