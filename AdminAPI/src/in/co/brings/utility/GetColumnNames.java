package in.co.brings.utility;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class GetColumnNames {

	public ArrayList<String> getColumnNames(ResultSet rs) throws SQLException
	{
		ArrayList<String> columnNames=new ArrayList<>();
	
		ResultSetMetaData rsMetaData = rs.getMetaData();
	    int numberOfColumns = rsMetaData.getColumnCount();

	    // get the column names; column indexes start from 1
	    for (int i = 1; i < numberOfColumns + 1; i++) {
	    	if(!rsMetaData.getColumnName(i).equalsIgnoreCase("RNUM"))
	    	{
	    		columnNames.add(rsMetaData.getColumnName(i));
	    	}
	    	
	      // Get the name of the column's table name
	     
	}
	    return columnNames;
	}
}
