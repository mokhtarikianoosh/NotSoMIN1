package persistenceLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.CompanyReport;

public class ByState {

	
private Connection conn = null;
	
	
	public ByState() throws SQLException{
		conn = DbUtils.connect();
	}
	
	
	public List<String> getStates(){
		String check = "select distinct state from issuesByState order by state asc";
		Statement stmt2 = null;
		List<String> states = new ArrayList<String>();
		try {
			stmt2 = conn.createStatement();
			 // retrieve the persistent objects
	       //	
			System.out.println(check);
			ResultSet rs = stmt2.executeQuery(check);
			 while(rs.next()){
				 states.add(rs.getString(1));
			 }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return states;
	}
	
	
	public List<String> getCompaniesInState(String state){
		String check = "select distinct company from issuesByState where state = '" + state + "' order by company asc";
		Statement stmt2 = null;
		List<String> states = new ArrayList<String>();
		try {
			stmt2 = conn.createStatement();
			 // retrieve the persistent objects
	       //	
			System.out.println(check);
			ResultSet rs = stmt2.executeQuery(check);
			 while(rs.next()){
				 states.add(rs.getString(1));
			 }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return states;
	}
	
	
	public List<CompanyReport> getCompaniesReportByState(String company, String state){
		String check = "select company,issue, total from issuesByState where state = '" + state + "' and company = '"+ company + "'";
		Statement stmt2 = null;
		List<CompanyReport> companies = new ArrayList<CompanyReport>();
		try {
			stmt2 = conn.createStatement();
			 // retrieve the persistent objects
	       //	
			System.out.println(check);
			ResultSet rs = stmt2.executeQuery(check);
			 while(rs.next()){
				 companies.add(new CompanyReport(rs.getString(1), rs.getString(2), rs.getInt(3)));
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
