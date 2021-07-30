package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import cybersoft.java12.crmapp.dao.UserDao;
import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.dto.UserDto;
import cybersoft.java12.crmapp.model.User;

public class UserService {
	private UserDao dao;
	
	public UserService() {
		dao = new UserDao();
	}

	public List<User> findAll() {
		List<User> users = null;
		try {
			users = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	public List<UserDto> findAllStaff() {
		List<UserDto> users = null;
		try {
			users = dao.findAllStaff();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void deleteById(int id) {
		try {
			dao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(UserCreateDto dto) {
		String hashedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashedPwd);
		
		try {
			dao.add(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public UserDto getUserById(int id){
		
		try {
			return dao.getUserById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
public UserDto getUserWhenLogin(String email){
		
		try {
			return dao.getUserWhenLogin(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean UpdateUser(UserDto dto){
		
		try {
			return dao.UpdateUser(dto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}public boolean UpdateUserAndPass(UserDto dto){
		String hashedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashedPwd);
		
		try {
			return dao.UpdateUserAndPass(dto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
