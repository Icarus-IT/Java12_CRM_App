package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.ProjectUserDto;

public class ProjectUserDao {
	public List<ProjectUserDto> findAllProject() throws SQLException {
		List<ProjectUserDto> listProjectUser = null;
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT project_id, user_id,join_date, role_description "
				+ "FROM project_user ";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				ProjectUserDto dto= new ProjectUserDto();
				
				dto.setProjectId(resultSet.getInt("proejct_id"));
				dto.setUserId(resultSet.getInt("user_id"));
				dto.setJoinDate(resultSet.getDate("join_date"));
				dto.setRole_Description(resultSet.getString("role_description"));
				
				if (listProjectUser==null) {
					 listProjectUser= new ArrayList<ProjectUserDto>(); 
				}
				listProjectUser.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return listProjectUser;
	}
	public List<ProjectUserDto> findAllProjectUserByPID(int pid) throws SQLException {
		List<ProjectUserDto> listProjectUser =new ArrayList<ProjectUserDto>(); 
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT project_id, user_id,join_date, role_description "
				+ "FROM project_user "
				+ "WHERE project_id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, pid);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				ProjectUserDto dto= new ProjectUserDto();
				
				dto.setProjectId(resultSet.getInt("project_id"));
				dto.setUserId(resultSet.getInt("user_id"));
				dto.setJoinDate(resultSet.getDate("join_date"));
				dto.setRole_Description(resultSet.getString("role_description"));
				
				listProjectUser.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return listProjectUser;
	}
	public List<ProjectUserDto> findAllProjectUserByUID(int uid) throws SQLException {
		List<ProjectUserDto> listProjectUser = new ArrayList<ProjectUserDto>(); 
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT project_id, user_id,join_date, role_description "
				+ "FROM project_user "
				+ "WHERE user_id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, uid);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				ProjectUserDto dto= new ProjectUserDto();
				
				dto.setProjectId(resultSet.getInt("project_id"));
				dto.setUserId(resultSet.getInt("user_id"));
				dto.setJoinDate(resultSet.getDate("join_date"));
				dto.setRole_Description(resultSet.getString("role_description"));
				
				listProjectUser.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return listProjectUser;
	}
	public void deleteProjectUserById(int pid,int uid) throws SQLException {
		String query = "DELETE FROM project_user WHERE project_id = ? and user_id = ?";
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, pid);
			statement.setInt(2, uid);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}
	public boolean addProjectUser(ProjectUserDto dto) throws SQLException {
		String query = "INSERT INTO project_user(project_id, user_id, join_date, role_description)"
				+ "VALUES(?,?,?,?)";
		
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, dto.getProjectId());
			statement.setInt(2, dto.getUserId());
			statement.setDate(3, dto.getJoinDate());
			statement.setString(4, dto.getRole_Description());
			
			int row = statement.executeUpdate();
			if (row >0) return true;
			
		}catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return false;
	}
//	public ProjectDto getProjectById(int id) throws SQLException {
//		Connection connection = MySqlConnection.getConnection();
//		String query = "SELECT id, name, description, start_date, end_date, owner "
//				+ "FROM project "
//				+ "WHERE id = ?";
//		ProjectDto dto =null;
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setInt(1, id);
//			
//			ResultSet resultSet = statement.executeQuery();
//			
//			if(resultSet.next()) {
//				dto = new ProjectDto();
//				dto.setId(resultSet.getInt("id"));
//				dto.setName(resultSet.getString("name"));
//				dto.setDescription(resultSet.getString("description"));
//				dto.setStart_date(resultSet.getDate("start_date"));
//				dto.setEnd_date(resultSet.getDate("end_date"));
//				dto.setOwner((resultSet.getInt("owner")));
//			}
//		} catch (SQLException e) {
//			System.out.println("Unable to connect to database.");
//			e.printStackTrace();
//		} finally {
//			connection.close();
//		}
//		
//		return dto;
//	}
//	public boolean UpdateProject(ProjectDto dto) throws SQLException {
//		String query = "UPDATE project SET name=?, description =?, start_date=?, end_date=?, owner=?  WHERE id = ?";
//		Connection connection = MySqlConnection.getConnection();
//		
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setString(1, dto.getName());
//			statement.setString(2, dto.getDescription());
//			statement.setDate(3, dto.getStart_date());
//			statement.setDate(4, dto.getEnd_date());
//			statement.setInt(5, dto.getOwner());
//			statement.setInt(6, dto.getId());
//		
//			int row = statement.executeUpdate();
//			if (row>0) return true;
//			
//		} catch (SQLException e) {
//			System.out.println("Unable to connect to database.");
//			e.printStackTrace();
//		} finally {
//			connection.close();
//		}
//		return false;
//	}
}
