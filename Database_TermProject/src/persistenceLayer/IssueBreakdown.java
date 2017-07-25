package persistenceLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.BreakdownIssue;

public class IssueBreakdown {

	private Connection conn = null;
	
	
	public IssueBreakdown() throws SQLException{
		conn = DbUtils.connect();
	}
	
	
	public List<BreakdownIssue> getBreakdown(String issue){
		String check = "select * from (select product, issue, count(issue) as total from productIssue group by product, issue) as t where issue='" + issue.replace("'", "''") + "' order by total desc";
		Statement stmt2 = null;
		List<BreakdownIssue> breakdown = new ArrayList<BreakdownIssue>();
		try {
			stmt2 = conn.createStatement();
			 // retrieve the persistent objects
	       //	
			ResultSet rs = stmt2.executeQuery(check);
			 while(rs.next()){
				 breakdown.add(new BreakdownIssue(rs.getString(1),rs.getString(2),rs.getInt(3)));
			 }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return breakdown;
	}

	
	public void disconnect(){
		try{
			conn.close();
		}catch(SQLException e){
			System.out.println("error");
		}
	}
	
	
	
}
