package persistenceLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckLogin {

private Connection conn = null;
	
	
	public CheckLogin() throws SQLException{
		conn = DbUtils.connect();
	}
	
	
	public boolean checkCredentials(String username, String password){
		String check = "select Username from User where Username = '" + username + "' and password = '" + password + "'"  ;
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
	
	
	public void disconnect(){
		try{
			conn.close();
		}catch(SQLException e){
			System.out.println("error");
		}
	}
}
