package persistenceLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyRatings {

private Connection conn = null;
	
	
	public CompanyRatings() throws SQLException{
		conn = DbUtils.connect();
	}
	
	
	public List<String> getWorst(){
		String check = "select company, sum(numOfIssue) as totalIssues from companyIssue  group by company order by totalIssues desc limit 10";
		Statement stmt2 = null;
		List<String> worst = new ArrayList<String>();
		try {
			stmt2 = conn.createStatement();
			 // retrieve the persistent objects
	       //	
			ResultSet rs = stmt2.executeQuery(check);
			 while(rs.next()){
				 worst.add(rs.getString(1));
			 }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return worst;
		
	}
	
	public List<String> getBest(){
		String check = "select company, sum(numOfIssue) as totalIssues from companyIssue  group by company order by totalIssues asc limit 10";
		Statement stmt2 = null;
		List<String> best = new ArrayList<String>();
		try {
			stmt2 = conn.createStatement();
			 // retrieve the persistent objects
	       //	
			ResultSet rs = stmt2.executeQuery(check);
			 while(rs.next()){
				 best.add(rs.getString(1));
			 }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return best;
		
	}
	
	public List<String> topIssues(){
		String check = "select issue, sum(numOfIssue) as total from companyIssue group by issue order by total desc limit 25";
		Statement stmt2 = null;
		List<String> issues = new ArrayList<String>();
		try {
			stmt2 = conn.createStatement();
			 // retrieve the persistent objects
	       //	
			ResultSet rs = stmt2.executeQuery(check);
			 while(rs.next()){
				 issues.add(rs.getString(1));
			 }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return issues;
		
	}
	
	
	public void disconnect(){
		try{
			conn.close();
		}catch(SQLException e){
			System.out.println("error");
		}
	}
	
	
}
