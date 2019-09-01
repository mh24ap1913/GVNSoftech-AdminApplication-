package in.co.brings.DAO;

import in.co.brings.Beans.SellsExecutive;
import in.co.brings.utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SellsExecutiveDao
{
    Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int res=0;
	String query=null;
	JDBCConnection connref=new JDBCConnection();
	public int registerUser(SellsExecutive register) throws ClassNotFoundException, SQLException{
		try{
		con=connref.getOracleConnection();
		query="insert into  sellsman values(?,?,?,?,?)";
		pstmt=con.prepareStatement(query);
		pstmt.setInt(1, register.getSellsmanid());
		pstmt.setString(2, register.getSellsmanname());
		pstmt.setString(3, register.getSellsmanaddress());
		pstmt.setString(4, register.getSellsmanemail());
		pstmt.setString(5, register.getSellsmannewupdate());
		res=pstmt.executeUpdate();
		if(res==0){
			System.out.println("value not inserted");
		}
		else{
			System.out.println("values inserted succefully");;
		}
		
	}
		catch(Exception e){
			System.out.println("Internal problem"+e);
		}
		return res;
	}
}