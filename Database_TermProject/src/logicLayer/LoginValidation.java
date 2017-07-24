package logicLayer;

import java.sql.SQLException;

/**
 *  LogicLayer class to handle the loginValidation
 * 
 * 
 */
import java.util.List;

import Entity.User;
import SessionTracking.Session;
import SessionTracking.SessionManager;
import persistenceLayer.CheckLogin;
public class LoginValidation {

	private CheckLogin check;
	
	public LoginValidation(){
		try {
			check = new CheckLogin();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public String validate(Session session, String username, String password) throws Exception{
		String ssid = null;
		String returnString = null;
		try {
			
			if(check.checkCredentials(username, password) == true){
				User user = new User();
				user.setUserName(username);
				user.setPassword(password);
				session.setUser(user);
				ssid = SessionManager.storeSession(session);
				returnString = ssid;
			}else{
				returnString= "Error";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		check.disconnect();
		return returnString;
		
	}
	
	
	public void logout(String ssid) throws Exception{
		SessionManager.logout(ssid);
		check.disconnect();
	}
	
	
	
	
	
}
