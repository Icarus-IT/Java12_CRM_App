package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cybersoft.java12.crmapp.dao.TaskDao;
import cybersoft.java12.crmapp.dto.TaskDto;


public class TaskService {
	private TaskDao dao;
	public TaskService() {
		// TODO Auto-generated constructor stub
		dao = new TaskDao();
	}
	
	public List<TaskDto> findAllProject(int Tid) {
		List<TaskDto> proejects = null;
		try {
			proejects = dao.findAllTaskByProjectId(Tid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proejects;
	}
	public List<TaskDto> findAllTaskByUserId(int uid) {
		List<TaskDto> proejects = new ArrayList<TaskDto>();
		try {
			proejects = dao.findAllTaskByUserId(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proejects;
	}


	public void deleteProjectById(int id) {
		try {
			dao.deleteTaskById(id);;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addTask(TaskDto dto) {
		try {
			dao.addTask(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public TaskDto getTaskById(int id){
		
		try {
			return dao.getTaskById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean UpdateProject(TaskDto dto){
		
		try {
			if ( dao.UpdateTask(dto)) {
			
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
