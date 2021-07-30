package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java12.crmapp.dto.ProjectDto;
import cybersoft.java12.crmapp.dto.ProjectUserDto;
import cybersoft.java12.crmapp.dto.TaskDto;
import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.dto.UserDto;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.User;
import cybersoft.java12.crmapp.service.ProjectService;
import cybersoft.java12.crmapp.service.ProjectUserService;
import cybersoft.java12.crmapp.service.TaskService;
import cybersoft.java12.crmapp.service.UserService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name = "projectServlet", urlPatterns = {
		UrlConst.PROJECT_DASHBOARD,
		UrlConst.PROJECT_MANAGE,
		UrlConst.PROJECT_ADD,
		UrlConst.PROJECT_UPDATE,
		UrlConst.PROJECT_DELETE,
		UrlConst.PROJECT_STAFF,
		UrlConst.PROJECT_STAFF_ADD,
		UrlConst.PROJECT_STAFF_REMOVE
})
public class ProjectServlet extends HttpServlet {
	private ProjectService service;
	private UserService Uservice;
	private ProjectUserService PUservice;
	private TaskService taskService;
	public ProjectServlet() {
		// TODO Auto-generated constructor stub
		service = new ProjectService();
		Uservice = new UserService();
		PUservice = new ProjectUserService();
		taskService = new TaskService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.PROJECT_DASHBOARD:
			
			getDashboard(req,resp);
			break;
		case UrlConst.PROJECT_MANAGE:
			getProjectManage(req,resp);
			break;
		case UrlConst.PROJECT_ADD:
			getProjectAdd(req,resp);
			break;
		case UrlConst.PROJECT_UPDATE:
			getProjectUpdate(req,resp);
			break;
		case UrlConst.PROJECT_DELETE:
			getProjectDelete(req,resp);
			break;
		case UrlConst.PROJECT_STAFF:
			getProjectStaff(req,resp);
			break;
		case UrlConst.PROJECT_STAFF_ADD:
			getProjectStaffAdd(req,resp);
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:
			getProjectStaffRemove(req,resp);
			break;
		default:
			
			break;
		}
	}
	
	private void getProjectStaffRemove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int uid = Integer.parseInt(req.getParameter("uid"));
		int pid = Integer.parseInt(req.getParameter("pid"));
		PUservice.deleteProjectById(pid, uid);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_STAFF+"?id="+pid);
	}
	private void getProjectStaffAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int uid = Integer.parseInt(req.getParameter("uid"));
		int pid = Integer.parseInt(req.getParameter("pid"));
		long millis=System.currentTimeMillis();  
		ProjectUserDto PUdto = new ProjectUserDto(pid, uid,new java.sql.Date(millis), "STAFF");
		PUservice.addProject(PUdto);

		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_STAFF+"?id="+pid);
	}
	private void getProjectStaff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<UserDto> users = Uservice.findAllStaff();
		int id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("pid", id);
		if (users != null && !users.isEmpty())
			req.setAttribute("users", users);
		
		List<ProjectUserDto> PUList = PUservice.findAllProjectUserById(id);
		
		req.setAttribute("PUList", PUList);
		req.getRequestDispatcher(JspConst.PROJECT_STAFF)
		.forward(req, resp);
	}
	private void getProjectDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		List<ProjectUserDto> list = PUservice.findAllProjectUserById(id);
		List<TaskDto> taskList = taskService.findAllProject(id);
		if (list.size()==0&&taskList.size()==0) {
			service.deleteProjectById(id);

			resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
		} else {
			req.setAttribute("msg", "Delete project fail, must be delete staff or task in this project first");
			UserDto dto = (UserDto) req.getSession().getAttribute("UserInfo");
			List<ProjectDto> projectAll = service.findAllProject();
			List<ProjectDto> projects = new ArrayList<ProjectDto>();
			
				List<ProjectDto> ownerList = new ArrayList<ProjectDto>();
				for (ProjectDto project: projectAll ) {
					if (dto.getId()==project.getOwner()) {
						ownerList.add(project);
					}
				}
			if (dto.getRoleId()==2) {
				projects = ownerList;
			} else {
				projects =service.findAllProject();
			}
			
			if (projects != null && !projects.isEmpty()) {
				List<Project> listProject = new ArrayList<Project>();
				for (ProjectDto pdto: projects) {
					Project p = new Project();
					p.setId(pdto.getId());
					p.setName(pdto.getName());
					p.setDescription(pdto.getDescription());
					p.setStart_date(pdto.getStart_date());
					p.setEnd_date(pdto.getEnd_date());
					p.setOwner(Uservice.getUserById(pdto.getOwner()));
					listProject.add(p);
				}
				req.setAttribute("projects", listProject);
				
			}
			req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD)
			.forward(req, resp);
		}
		
		
	}
	private void getProjectUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProjectDto dto = getDtoById(req);
		req.setAttribute("ProjectToUpdate", dto);
		req.getRequestDispatcher(JspConst.PROJECT_UPDATE)
		.forward(req, resp);
		
	}
	private ProjectDto getDtoById(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		return service.getProjectById(id);
	}
	private void getProjectAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(JspConst.PROJECT_ADD)
		.forward(req, resp);
	}
	private void getProjectManage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD)
		.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.PROJECT_DASHBOARD:
//			getDashboard(req,resp);
			break;
		case UrlConst.PROJECT_MANAGE:
			postProjectManage(req,resp);
			break;
		case UrlConst.PROJECT_ADD:
			postProjectAdd(req,resp);
			break;
		case UrlConst.PROJECT_UPDATE:
			postProjectUpdate(req,resp);
			break;
		case UrlConst.PROJECT_DELETE:
			postProjectDelete(req,resp);
			break;
		case UrlConst.PROJECT_STAFF:
			postProjectStaff(req,resp);
			break;
		case UrlConst.PROJECT_STAFF_ADD:
			postProjectStaffAdd(req,resp);
			break;
		case UrlConst.PROJECT_STAFF_REMOVE:
			postProjectStaffRemove(req,resp);
			break;
		default:
			
			break;
		}
	}

	private void postProjectStaffRemove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD)
		.forward(req, resp);
	}
	private void postProjectStaffAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD)
		.forward(req, resp);
	}
	private void postProjectStaff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD)
		.forward(req, resp);
		
	}
	private void postProjectDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD)
		.forward(req, resp);
	}
	private void postProjectUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProjectDto dto = getDtoFromReq(req);
		UserDto Udto = Uservice.getUserById(dto.getOwner());
		if (Udto==null||Udto.getRoleId()!=2) {
			req.setAttribute("msg", "Add fail, Database don't have this user or user not a LEADER");
			ProjectDto Pdto = getDtoById(req);
			req.setAttribute("ProjectToUpdate", Pdto);
			req.getRequestDispatcher(JspConst.PROJECT_UPDATE)
			.forward(req, resp);
		} else 
		if (dto.getStart_date().after(dto.getEnd_date())) {
			ProjectDto Pdto = getDtoById(req);
			req.setAttribute("ProjectToUpdate", Pdto);
			req.setAttribute("msg", "Update fail");
			req.getRequestDispatcher(JspConst.PROJECT_UPDATE)
			.forward(req, resp);
		} else {
			service.UpdateProject(dto);
			resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
		}	
	}
	private ProjectDto getDtoFromReq(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		Date start_date = Date.valueOf(req.getParameter("start_date"));
		Date end_date = Date.valueOf(req.getParameter("end_date"));
		int owner = Integer.parseInt(req.getParameter("owner"));
		return new ProjectDto(id,name, description, start_date, end_date, owner);
	}
	private void postProjectAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProjectDto dto = extractDtoFromReq(req);
		UserDto Udto = Uservice.getUserById(dto.getOwner());
		if (Udto==null||Udto.getRoleId()!=2) {
			req.setAttribute("msg", "Add fail, Database don't have this user or user not a LEADER");
			req.getRequestDispatcher(JspConst.PROJECT_ADD)
			.forward(req, resp);
		} else 
		if (dto.getStart_date().after(dto.getEnd_date())) {
			req.setAttribute("msg", "Add fail");
			req.getRequestDispatcher(JspConst.PROJECT_ADD)
			.forward(req, resp);
		} else {
			
			service.addProject(dto);
			resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
		}
		
		
	}
	private ProjectDto extractDtoFromReq(HttpServletRequest req) {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		Date start_date = Date.valueOf(req.getParameter("start_date"));
		Date end_date = Date.valueOf(req.getParameter("end_date"));
		int owner = Integer.parseInt(req.getParameter("owner"));
		return new ProjectDto(name, description, start_date, end_date, owner);
	}
	private void postProjectManage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD)
		.forward(req, resp);	
	}
	private void getDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserDto dto = (UserDto) session.getAttribute("UserInfo");
		List<ProjectDto> projectAll = service.findAllProject();
		List<ProjectDto> projects = new ArrayList<ProjectDto>();
		
			List<ProjectDto> ownerList = new ArrayList<ProjectDto>();
			for (ProjectDto project: projectAll ) {
				if (dto.getId()==project.getOwner()) {
					ownerList.add(project);
				}
			}
		if (dto.getRoleId()==2) {
			projects = ownerList;
		} else {
			projects =service.findAllProject();
		}
		
		if (projects != null && !projects.isEmpty()) {
			List<Project> listProject = new ArrayList<Project>();
			for (ProjectDto pdto: projects) {
				Project p = new Project();
				p.setId(pdto.getId());
				p.setName(pdto.getName());
				p.setDescription(pdto.getDescription());
				p.setStart_date(pdto.getStart_date());
				p.setEnd_date(pdto.getEnd_date());
				p.setOwner(Uservice.getUserById(pdto.getOwner()));
				listProject.add(p);
			}
			req.setAttribute("projects", listProject);
			
		}
		req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD)
		.forward(req, resp);
	}
	
}
