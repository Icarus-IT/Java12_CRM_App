package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.dto.StatusDto;

public class StatusDao {
	public StatusDto getStatusById(int id) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT id, name, description "
				+ "FROM status "
				+ "WHERE id = ?";
		StatusDto dto =null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				dto = new StatusDto();
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("name"));
				dto.setDescription(resultSet.getString("description"));
			}
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return dto;
	}
}
