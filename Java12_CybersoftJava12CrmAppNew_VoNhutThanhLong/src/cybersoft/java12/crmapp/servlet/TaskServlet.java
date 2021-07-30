package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java12.crmapp.dto.ProjectDto;
import cybersoft.java12.crmapp.dto.ProjectUserDto;
import cybersoft.java12.crmapp.dto.StatusDto;
import cybersoft.java12.crmapp.dto.TaskDto;
import cybersoft.java12.crmapp.dto.UserDto;
import cybersoft.java12.crmapp.model.Task;
import cybersoft.java12.crmapp.service.ProjectService;
import cybersoft.java12.crmapp.service.ProjectUserService;
import cybersoft.java12.crmapp.service.StatusService;
import cybersoft.java12.crmapp.service.TaskService;
import cybersoft.java12.crmapp.service.UserService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name = "taskServlet", urlPatterns = {
		UrlConst.TASK_DASHBOARD,
		UrlConst.TASK_ADD,
		UrlConst.TASK_DELETE,
		UrlConst.TASK_UPDATE,
		UrlConst.TASK_USER_DASHBOARD,
		UrlConst.TASK_USER_UPDATE,
})
public class TaskServlet extends HttpServlet{
	private ProjectService service;
	private UserService Uservice;
	private ProjectUserService puService;
	private TaskService taskService;
	private StatusService statusService;
	public TaskServlet() {
		// TODO Auto-generated constructor stub
		service = new ProjectService();
		Uservice = new UserService();
		puService = new ProjectUserService();
		taskService = new TaskService();
		statusService = new StatusService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.TASK_DASHBOARD:
			getTaskDashboard(req,resp);
			break;
		case UrlConst.TASK_ADD:
			getTaskAdd(req,resp);
			break;
		case UrlConst.TASK_UPDATE:
			getTaskUpdate(req,resp);
			break;
		case UrlConst.TASK_DELETE:
			getTaskDelete(req,resp);
			break;
		case UrlConst.TASK_USER_DASHBOARD:
			getTaskUserDashboard(req,resp);
			break;
		case UrlConst.TASK_USER_UPDATE:
			getTaskUserUpdate(req,resp);
			break;
		}
	}
	private void getTaskUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int tid =  Integer.parseInt(req.getParameter("tid"));
		TaskDto taskDto = taskService.getTaskById(tid);
		req.setAttribute("TaskToUpdate", taskDto);
		req.setAttribute("pId", taskDto.getProjectId());
		req.setAttribute("tid", tid);
//		List<ProjectUserDto> pUDto = puService.findAllProjectUserById(taskDto.getProjectId());
//		List<UserDto> listUser = new ArrayList<UserDto>();
//		for (ProjectUserDto dto : pUDto) {
//			UserDto uDto = new UserDto();
//			uDto = Uservice.getUserById(dto.getUserId());
//			listUser.add(uDto);
//		}
		List<StatusDto> listStatus = new ArrayList<StatusDto>();
		listStatus.add(new StatusDto(1, "DONE", "done"));
		listStatus.add(new StatusDto(2, "DOING", "doing"));
		listStatus.add(new StatusDto(3, "TO DO", "not start"));
		req.setAttribute("STATUSLIST", listStatus);
		req.getRequestDispatcher(JspConst.TASK_USER_UPDATE)
		.forward(req, resp);
		
	}
	private void getTaskUserDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		int Pid = Integer.parseInt(req.getParameter("id"));
		HttpSession session = req.getSession();
		UserDto udto = (UserDto) session.getAttribute("UserInfo");
		int done = 0;
		List<TaskDto> tasks = taskService.findAllTaskByUserId(udto.getId());
		List<Task> listtask = new ArrayList<Task>();
		if (tasks != null && !tasks.isEmpty()) {
			for (TaskDto dto: tasks) {
				Task task = new Task();
				task.setId(dto.getId());
				task.setName(dto.getName());
				task.setDescription(dto.getDescription());
				task.setStart_date(dto.getStart_date());
				task.setEnd_date(dto.getEnd_date());
				task.setProject(service.getProjectById(dto.getProjectId()));
				task.setUser(Uservice.getUserById(dto.getUserId()));
				task.setStatus(statusService.getStatusById(dto.getStatusId()));
				if (dto.getStatusId()==1) {
					done++;
				}
				listtask.add(task);
			}
			
			req.setAttribute("max", listtask.size());
			req.setAttribute("done", done);
			double access=  ((double)done*100/listtask.size());
		
			double percent =  (double) Math.round(access * 100.0) / 100.0;
		
			req.setAttribute("percent", percent);
			req.setAttribute("tasks", listtask);
		};
		req.getRequestDispatcher(JspConst.TASK_USER_DASHBOARD)
			.forward(req, resp);
		
	}
	private void getTaskDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int tid = Integer.parseInt(req.getParameter("tid"));
		int pid = Integer.parseInt(req.getParameter("pid"));
		
		taskService.deleteProjectById(tid);
		resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD+"?id="+pid);
	}
	private void getTaskUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int tid =  Integer.parseInt(req.getParameter("tid"));
		TaskDto taskDto = taskService.getTaskById(tid);
		req.setAttribute("TaskToUpdate", taskDto);
		req.setAttribute("pId", taskDto.getProjectId());
		req.setAttribute("tid", tid);
		List<ProjectUserDto> pUDto = puService.findAllProjectUserById(taskDto.getProjectId());
		List<UserDto> listUser = new ArrayList<UserDto>();
		for (ProjectUserDto dto : pUDto) {
			UserDto uDto = new UserDto();
			uDto = Uservice.getUserById(dto.getUserId());
			listUser.add(uDto);
		}
		req.setAttribute("USERLIST", listUser);
		req.getRequestDispatcher(JspConst.TASK_UPDATE)
		.forward(req, resp);
	}
	private void getTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pid =  Integer.parseInt(req.getParameter("pid"));
		req.setAttribute("pId", pid);
		List<ProjectUserDto> pUDto = puService.findAllProjectUserById(pid);
		List<UserDto> listUser = new ArrayList<UserDto>();
		for (ProjectUserDto dto : pUDto) {
			UserDto uDto = new UserDto();
			uDto = Uservice.getUserById(dto.getUserId());
			listUser.add(uDto);
		}
		req.setAttribute("USERLIST", listUser);
		req.getRequestDispatcher(JspConst.TASK_ADD)
		.forward(req, resp);
		
	}
	private void getTaskDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int Pid = Integer.parseInt(req.getParameter("id"));
		int done = 0;
		
		List<TaskDto> tasks = taskService.findAllProject(Pid);
		List<Task> listtask = new ArrayList<Task>();
		if (tasks != null && !tasks.isEmpty()) {
			for (TaskDto dto: tasks) {
				Task task = new Task();
				task.setId(dto.getId());
				task.setName(dto.getName());
				task.setDescription(dto.getDescription());
				task.setStart_date(dto.getStart_date());
				task.setEnd_date(dto.getEnd_date());
				task.setProject(service.getProjectById(dto.getProjectId()));
				task.setUser(Uservice.getUserById(dto.getUserId()));
				task.setStatus(statusService.getStatusById(dto.getStatusId()));
				if (dto.getStatusId()==1) {
					done++;
				}
				listtask.add(task);
			}
			
			req.setAttribute("max", listtask.size());
			req.setAttribute("done", done);
			double access=  ((double)done*100/listtask.size());
			
			double percent =  (double) Math.round(access * 100.0) / 100.0;
			
			req.setAttribute("percent", percent);
			req.setAttribute("tasks", listtask);
		};
		req.setAttribute("pId", Pid);
		req.getRequestDispatcher(JspConst.TASK_DASHBOARD)
			.forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.TASK_DASHBOARD:
			postTaskDashboard(req,resp);
			break;
		case UrlConst.TASK_ADD:
			postTaskAdd(req,resp);
			break;
		case UrlConst.TASK_UPDATE:
			postTaskUpdate(req,resp);
			break;
		case UrlConst.TASK_DELETE:
			postTaskDelete(req,resp);
			break;
		case UrlConst.TASK_USER_DASHBOARD:
			postTaskDashboard(req,resp);
			break;
		case UrlConst.TASK_USER_UPDATE:
			postTaskUserUpdate(req,resp);
			break;
		}
	}
	private void postTaskUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int taskId = Integer.parseInt(req.getParameter("tid"));
		
		int projectId = Integer.parseInt(req.getParameter("pid"));
		int statusId = Integer.parseInt(req.getParameter("cboStatus"));
		
		TaskDto dto = taskService.getTaskById(taskId);
		req.setAttribute("TaskToUpdate", dto);

		dto.setStatusId(statusId);
			taskService.UpdateProject(dto);
			resp.sendRedirect(req.getContextPath() + UrlConst.TASK_USER_DASHBOARD);
		
	
		
	}
	private void postTaskDashboard(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	private void postTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		Date start_date = Date.valueOf(req.getParameter("start_date"));
		Date end_date = Date.valueOf(req.getParameter("end_date"));
		int projectId = Integer.parseInt(req.getParameter("pid"));
		int userId = Integer.parseInt(req.getParameter("cboUserName"));
		int statusId = 3;
		TaskDto dto = new TaskDto(name, description, start_date, end_date, projectId, userId, statusId);
		if (dto.getStart_date().after(dto.getEnd_date())) {
			req.setAttribute("msg", "Add fail");
			List<ProjectUserDto> pUDto = puService.findAllProjectUserById(projectId);
			List<UserDto> listUser = new ArrayList<UserDto>();
			for (ProjectUserDto pudto : pUDto) {
				UserDto uDto = new UserDto();
				uDto = Uservice.getUserById(pudto.getUserId());
				listUser.add(uDto);
			}
			req.setAttribute("USERLIST", listUser);
			req.setAttribute("pId", projectId);
			req.getRequestDispatcher(JspConst.TASK_ADD)
			.forward(req, resp);
		} else {
			
			taskService.addTask(dto);
			resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD+"?id="+projectId);
		}
		
	}
	private void postTaskUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int taskId = Integer.parseInt(req.getParameter("tid"));
		
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		Date start_date = Date.valueOf(req.getParameter("start_date"));
		Date end_date = Date.valueOf(req.getParameter("end_date"));
		int projectId = Integer.parseInt(req.getParameter("pid"));
		int userId = Integer.parseInt(req.getParameter("cboUserName"));
		
		TaskDto dto = taskService.getTaskById(taskId);
		req.setAttribute("TaskToUpdate", dto);
		dto.setName(name);
		dto.setDescription(description);
		dto.setStart_date(start_date);
		dto.setEnd_date(end_date);
		dto.setUserId(userId);
		
		if (dto.getStart_date().after(dto.getEnd_date())) {
			req.setAttribute("msg", "Add fail");
			List<ProjectUserDto> pUDto = puService.findAllProjectUserById(projectId);
			List<UserDto> listUser = new ArrayList<UserDto>();
			for (ProjectUserDto pudto : pUDto) {
				UserDto uDto = new UserDto();
				uDto = Uservice.getUserById(pudto.getUserId());
				listUser.add(uDto);
			}
			req.setAttribute("USERLIST", listUser);
			req.setAttribute("pId", projectId);
			req.setAttribute("tid", taskId);
			req.getRequestDispatcher(JspConst.TASK_UPDATE)
			.forward(req, resp);
		} else {
			
			taskService.UpdateProject(dto);
			resp.sendRedirect(req.getContextPath() + UrlConst.TASK_DASHBOARD+"?id="+projectId);
		}
		
	}
	private void postTaskDelete(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
}
