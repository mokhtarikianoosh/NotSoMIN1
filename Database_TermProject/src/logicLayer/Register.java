package logicLayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistenceLayer.UserRegistration;

public class Register {

	private UserRegistration register;
	
	public Register(){
		try {
			register = new UserRegistration();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public int addUser(String userName, String password, String email){
		return register.addUser(userName, password, email);
			
	}
	
	
	public boolean checkUser(String username){
		return register.checkUserName(username);
	}
	
	public void disconnect(){
		register.disconnect();
	}
}
