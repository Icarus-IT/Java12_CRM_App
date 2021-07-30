package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cybersoft.java12.crmapp.dao.ProjectUserDao;
import cybersoft.java12.crmapp.dto.ProjectUserDto;


public class ProjectUserService {
	private ProjectUserDao dao;
	public ProjectUserService() {
		// TODO Auto-generated constructor stub
		dao = new ProjectUserDao();
	}
	
	public List<ProjectUserDto> findAllProject() {
		List<ProjectUserDto> projectUsers = null;
		try {
			projectUsers = dao.findAllProject();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectUsers;
	}
	public List<ProjectUserDto> findAllProjectUserById(int pid) {
		List<ProjectUserDto> projectUsers = null;
		try {
			projectUsers = dao.findAllProjectUserByPID(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectUsers;
	}
	public List<ProjectUserDto> findAllProjectUserByUId(int uid) {
		List<ProjectUserDto> projectUsers = new ArrayList<ProjectUserDto>();
		try {
			projectUsers = dao.findAllProjectUserByUID(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectUsers;
	}
	
	public void deleteProjectById(int pid,int uid) {
		try {
			dao.deleteProjectUserById(pid, uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addProject(ProjectUserDto dto) {
		try {
			dao.addProjectUser(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	public ProjectDto getProjectById(int id){
//		
//		try {
//			return dao.getProjectById(id);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	public boolean UpdateProject(ProjectDto dto){
//		
//		try {
//			if ( dao.UpdateProject(dto)) {
//			
//			return true;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("false");
//		return false;
//	}
}
