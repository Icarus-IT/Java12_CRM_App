package cybersoft.java12.crmapp.dto;

import java.sql.Date;

public class TaskDto {
	private int id;
	private String name;
	private String description;
	private Date start_date;
	private Date end_date;
	private int projectId;
	private int userId;
	private int statusId;
	public TaskDto() {
		// TODO Auto-generated constructor stub
	}
	public TaskDto(int id, String name, String description, Date start_date, Date end_date, int projectId, int userId,
			int statusId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.projectId = projectId;
		this.userId = userId;
		this.statusId = statusId;
	}
	public TaskDto( String name, String description, Date start_date, Date end_date, int projectId, int userId,
			int statusId) {
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.projectId = projectId;
		this.userId = userId;
		this.statusId = statusId;
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
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	
}
