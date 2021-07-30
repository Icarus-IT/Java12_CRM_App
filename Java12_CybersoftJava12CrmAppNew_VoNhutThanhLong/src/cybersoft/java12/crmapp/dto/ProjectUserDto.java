package cybersoft.java12.crmapp.dto;

import java.sql.Date;

public class ProjectUserDto {
	private int projectId;
	private int userId;
	private Date joinDate;
	private String role_Description;
	public ProjectUserDto() {
		// TODO Auto-generated constructor stub
	}
	public ProjectUserDto(int projectId, int userId, Date joinDate, String role_Description) {
		super();
		this.projectId = projectId;
		this.userId = userId;
		this.joinDate = joinDate;
		this.role_Description = role_Description;
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
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getRole_Description() {
		return role_Description;
	}
	public void setRole_Description(String role_Description) {
		this.role_Description = role_Description;
	}
	
}
