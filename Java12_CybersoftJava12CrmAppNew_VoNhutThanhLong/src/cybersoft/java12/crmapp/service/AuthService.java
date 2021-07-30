package cybersoft.java12.crmapp.service;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import cybersoft.java12.crmapp.dao.AuthDao;
import cybersoft.java12.crmapp.dto.UserLoginDto;

public class AuthService {
	private AuthDao dao;
	
	public AuthService() {
		dao = new AuthDao();
	}

	public boolean login(String email, String password) {
		UserLoginDto dto = null;
		
		try {
			dto = dao.findUserLogin(email);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		if(dto == null)
			return false;
		
		return BCrypt.checkpw(password, dto.getPassword());
	}
	
}