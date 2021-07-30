package cybersoft.java12.crmapp.model;

import java.sql.Date;

import cybersoft.java12.crmapp.dto.ProjectDto;
import cybersoft.java12.crmapp.dto.StatusDto;
import cybersoft.java12.crmapp.dto.UserDto;

public class Task {
	private int id;
	private String name;
	private String description;
	private Date start_date;
	private Date end_date;
	private ProjectDto project;
	private UserDto user;
	private StatusDto status;
	public Task() {
		// TODO Auto-generated constructor stub
	}
	
	public Task(int id, String name, String description, Date start_date, Date end_date, ProjectDto project,
			UserDto user, StatusDto status) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.project = project;
		this.user = user;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public ProjectDto getProject() {
		return project;
	}
	public void setProject(ProjectDto project) {
		this.project = project;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public StatusDto getStatus() {
		return status;
	}
	public void setStatus(StatusDto status) {
		this.status = status;
	}
	
}
