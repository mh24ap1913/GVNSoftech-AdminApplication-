package in.co.brings.DAO;

import in.co.brings.Beans.MarketingExecutiveBean;
import in.co.brings.utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class MarketingExecutiveDao {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int res=0;
	String query=null;
	JDBCConnection connref=new JDBCConnection();
	DataSource dataSource;
	public int registerUser(MarketingExecutiveBean bean)throws Exception{
		try{
			con=((JDBCConnection) dataSource).getOracleConnection();
			query="insert into  brings_marketing_Executive values(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, bean.getFullname());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getPhone());
			pstmt.setString(4, bean.getPassword());
			pstmt.setString(5, bean.getConfirm_password());
			pstmt.setString(6, bean.getGender());
			
			res=pstmt.executeUpdate();
			if(res==0){
				System.out.println("Value not inserted");
			}
			else{
				System.out.println("value inserted successfully");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
		
	}

}
