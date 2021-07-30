package cybersoft.java12.crmapp.dto;

import java.sql.Date;

public class ProjectDto {
	private int id;
	private String name;
	private String description;
	private Date start_date;
	private Date end_date;
	private int owner;
	public ProjectDto() {
		// TODO Auto-generated constructor stub
	}
	public ProjectDto(int id, String name, String description, Date start_date, Date end_date, int owner) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.owner = owner;
	}
	public ProjectDto( String name, String description, Date start_date, Date end_date, int owner) {
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.owner = owner;
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
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
}
