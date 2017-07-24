package persistenceLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import persistenceLayer.DbUtils;

public class UserRegistration {

	
private Connection conn = null;
	
	
	public UserRegistration() throws SQLException{
		conn = DbUtils.connect();
	}
	
	
	public boolean checkUserName(String username){
		String check = "select Username from User where Username = '" + username + "'";
		Statement stmt2 = null;
		boolean checking = false;
		try {
			stmt2 = conn.createStatement();
			 // retrieve the persistent objects
	       //	
			ResultSet rs = stmt2.executeQuery(check);
			 if(rs.next()){
				checking = true;
			 }else{
				 checking = false;
			 }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checking;
		
	}
	
	public int addUser( String userName, String password, String email){
		StringBuilder insertUser = new StringBuilder();
		insertUser.append("insert into User values ( ");
		insertUser.append("'" + userName +"', ");
		insertUser.append("'" + password +"', ");
		insertUser.append("'" + email +"')");
		Statement stmt2 = null;
	 	int result = 0;
	 	System.out.println(insertUser.toString());
		try {
			stmt2 = conn.createStatement();
			 // retrieve the persistent objects
	       //
	       if( stmt2.executeUpdate( insertUser.toString() ) == 1) { // statement returned a result
	    	   result = 1;
	       }else{
	    	   result =0;
	       }
	  
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public void disconnect(){
		try{
			conn.close();
		}catch(SQLException e){
			System.out.println("error");
		}
	}
}
