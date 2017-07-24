package persistenceLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.CompanyReport;

public class Reports {

private Connection conn = null;
	
	
	public Reports() throws SQLException{
		conn = DbUtils.connect();
	}
	
	
	public List<CompanyReport> companyReport(String company){
		List<CompanyReport> report = new ArrayList<CompanyReport>();
		Statement stmt2 = null;
		String check = "select * from companyIssue where company = '" + company + "'";
		try {
			stmt2 = conn.createStatement();
			 // retrieve the persistent objects
	       //	
			ResultSet rs = stmt2.executeQuery(check);
			 while(rs.next()){
				 report.add(new CompanyReport(rs.getString(1),rs.getString(2),rs.getInt(3)));
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return report;
		
		
	}
	
	
	public void disconnect(){
		try{
			conn.close();
		}catch(SQLException e){
			System.out.println("error");
		}
	}
	
}
