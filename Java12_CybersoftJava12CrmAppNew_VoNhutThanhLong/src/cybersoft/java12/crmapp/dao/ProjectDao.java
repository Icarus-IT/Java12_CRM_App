package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.ProjectDto;
import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.dto.UserDto;

public class ProjectDao {
	public List<ProjectDto> findAllProject() throws SQLException {
		List<ProjectDto> listProject = null;
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT id, name, description, start_date, end_date, owner "
				+ "FROM project ";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				ProjectDto dto= new ProjectDto();
				
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("name"));
				dto.setDescription(resultSet.getString("description"));
				dto.setStart_date(resultSet.getDate("start_date"));
				dto.setEnd_date(resultSet.getDate("end_date"));
				dto.setOwner(resultSet.getInt("owner"));
				if (listProject==null) {
					 listProject= new ArrayList<ProjectDto>(); 
				}
				listProject.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return listProject;
	}
	public void deleteProjectById(int id) throws SQLException {
		String query = "DELETE FROM project WHERE id = ?";
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}
	public boolean add(ProjectDto dto) throws SQLException {
		String query = "INSERT INTO project(name, description, start_date, end_date, owner)"
				+ "VALUES(?,?,?,?,?)";
		
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getDescription());
			statement.setDate(3, dto.getStart_date());
			statement.setDate(4, dto.getEnd_date());
			statement.setInt(5, dto.getOwner());
			
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
	public ProjectDto getProjectById(int id) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT id, name, description, start_date, end_date, owner "
				+ "FROM project "
				+ "WHERE id = ?";
		ProjectDto dto =null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				dto = new ProjectDto();
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("name"));
				dto.setDescription(resultSet.getString("description"));
				dto.setStart_date(resultSet.getDate("start_date"));
				dto.setEnd_date(resultSet.getDate("end_date"));
				dto.setOwner((resultSet.getInt("owner")));
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return dto;
	}
	public boolean UpdateProject(ProjectDto dto) throws SQLException {
		String query = "UPDATE project SET name=?, description =?, start_date=?, end_date=?, owner=?  WHERE id = ?";
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getDescription());
			statement.setDate(3, dto.getStart_date());
			statement.setDate(4, dto.getEnd_date());
			statement.setInt(5, dto.getOwner());
			statement.setInt(6, dto.getId());
		
			int row = statement.executeUpdate();
			if (row>0) return true;
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return false;
	}
	
}
