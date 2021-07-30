package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import cybersoft.java12.crmapp.dao.ProjectDao;
import cybersoft.java12.crmapp.dto.ProjectDto;

public class ProjectService {
	private ProjectDao dao;
	public ProjectService() {
		// TODO Auto-generated constructor stub
		dao = new ProjectDao();
	}
	
	public List<ProjectDto> findAllProject() {
		List<ProjectDto> proejects = null;
		try {
			proejects = dao.findAllProject();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proejects;
	}

	public void deleteProjectById(int id) {
		try {
			dao.deleteProjectById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addProject(ProjectDto dto) {
		try {
			dao.add(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ProjectDto getProjectById(int id){
		
		try {
			return dao.getProjectById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean UpdateProject(ProjectDto dto){
		
		try {
			if ( dao.UpdateProject(dto)) {
			
			return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("false");
		return false;
	}
}
