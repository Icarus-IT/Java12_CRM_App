package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.TaskDto;

public class TaskDao {
	public List<TaskDto> findAllTaskByProjectId(int Pid) throws SQLException {
		List<TaskDto> listTask =new ArrayList<TaskDto>(); 
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT id, name, description, start_date, end_date,project_id, user_id, status_id  "
				+ "FROM task "
				+ "WHERE project_id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, Pid);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				TaskDto dto= new TaskDto();
				
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("name"));
				dto.setDescription(resultSet.getString("description"));
				dto.setStart_date(resultSet.getDate("start_date"));
				dto.setEnd_date(resultSet.getDate("end_date"));
				dto.setProjectId(resultSet.getInt("project_id"));
				dto.setUserId(resultSet.getInt("user_id"));
				dto.setStatusId(resultSet.getInt("status_id"));
				
				listTask.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return listTask;
	}
	public List<TaskDto> findAllTaskByUserId(int uid) throws SQLException {
		List<TaskDto> listTask = new ArrayList<TaskDto>();
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT id, name, description, start_date, end_date,project_id, user_id, status_id  "
				+ "FROM task "
				+ "WHERE user_id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, uid);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				TaskDto dto= new TaskDto();
				
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("name"));
				dto.setDescription(resultSet.getString("description"));
				dto.setStart_date(resultSet.getDate("start_date"));
				dto.setEnd_date(resultSet.getDate("end_date"));
				dto.setProjectId(resultSet.getInt("project_id"));
				dto.setUserId(resultSet.getInt("user_id"));
				dto.setStatusId(resultSet.getInt("status_id"));
				
				listTask.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return listTask;
	}
	public void deleteTaskById(int id) throws SQLException {
		String query = "DELETE FROM task WHERE id = ?";
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
	public boolean addTask(TaskDto dto) throws SQLException {
		String query = "INSERT INTO task(name, description, start_date, end_date, project_id, user_id, status_id )"
				+ "VALUES(?,?,?,?,?,?,?)";
		
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getDescription());
			statement.setDate(3, dto.getStart_date());
			statement.setDate(4, dto.getEnd_date());
			statement.setInt(5, dto.getProjectId());
			statement.setInt(6, dto.getUserId());
			statement.setInt(7, dto.getStatusId());
			
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
	public TaskDto getTaskById(int id) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT id, name, description, start_date, end_date, project_id, user_id, status_id  "
				+ "FROM task "
				+ "WHERE id = ?";
		TaskDto dto =null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				dto = new TaskDto();
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("name"));
				dto.setDescription(resultSet.getString("description"));
				dto.setStart_date(resultSet.getDate("start_date"));
				dto.setEnd_date(resultSet.getDate("end_date"));
				dto.setProjectId(resultSet.getInt("project_id"));
				dto.setUserId(resultSet.getInt("user_id"));
				dto.setStatusId(resultSet.getInt("status_id"));
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return dto;
	}
	public boolean UpdateTask(TaskDto dto) throws SQLException {
		String query = "UPDATE task SET name=?, description =?, start_date=?, end_date=?, project_id=?, user_id=?, status_id=?  WHERE id = ?";
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getDescription());
			statement.setDate(3, dto.getStart_date());
			statement.setDate(4, dto.getEnd_date());
			statement.setInt(5, dto.getProjectId());
			statement.setInt(6, dto.getUserId());
			statement.setInt(7, dto.getStatusId());
			statement.setInt(8, dto.getId());
		
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
