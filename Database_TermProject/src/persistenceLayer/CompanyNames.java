package persistenceLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyNames {
	
private Connection conn = null;
	
	
	public CompanyNames() throws SQLException{
		conn = DbUtils.connect();
	}
	
	
	public List<String> names(String letter){
		String check = "select distinct name from company3 where name like  '" + letter + "%'";
		Statement stmt2 = null;
		List<String> companies = new ArrayList<String>();
		try {
			stmt2 = conn.createStatement();
			 // retrieve the persistent objects
	       //	
			System.out.println(check);
			ResultSet rs = stmt2.executeQuery(check);
			 while(rs.next()){
				 companies.add(rs.getString(1));
			 }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return companies;
	}
	
	public void disconnect(){
		try{
			conn.close();
		}catch(SQLException e){
			System.out.println("error");
		}
	}
}
