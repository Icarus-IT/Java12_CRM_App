package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.dto.ProjectDto;
import cybersoft.java12.crmapp.dto.ProjectUserDto;
import cybersoft.java12.crmapp.dto.TaskDto;
import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.dto.UserDto;
import cybersoft.java12.crmapp.model.User;
import cybersoft.java12.crmapp.service.ProjectUserService;
import cybersoft.java12.crmapp.service.TaskService;
import cybersoft.java12.crmapp.service.UserService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name = "userServlet", urlPatterns = { UrlConst.USER_DASHBOARD, UrlConst.USER_ADD,
		UrlConst.USER_UPDATE, UrlConst.USER_DELETE })
public class UserServlet extends HttpServlet {
	private UserService service;
	private ProjectUserService pUservice;
	private TaskService taskService;

	@Override
	public void init() throws ServletException {
		super.init();
		service = new UserService();
		pUservice = new ProjectUserService();
		taskService = new TaskService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.USER_DASHBOARD:
			getUserDashboard(req, resp);
			break;
		case UrlConst.USER_PROFILE:
			getUserProfile(req, resp);
			break;
		case UrlConst.USER_ADD:
			getUserAdd(req, resp);
			break;
		case UrlConst.USER_UPDATE:
			getUserUpdate(req, resp);
			break;
		case UrlConst.USER_DELETE:
			getUserDelete(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.USER_DASHBOARD:

			break;
		case UrlConst.USER_PROFILE:

			break;
		case UrlConst.USER_ADD:
			postUserAdd(req, resp);
			break;
		case UrlConst.USER_UPDATE:
			postUserUpdate(req, resp);
			break;
		case UrlConst.USER_DELETE:

			break;
		}
	}

	private void postUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// TODO Auto-generated method stub
		UserDto dto = setUserToUpdate(req);
		service.UpdateUser(dto);
		
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
	}

	private void getUserDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		List<ProjectUserDto>listP = pUservice.findAllProjectUserByUId(id);
		List<TaskDto> listT = taskService.findAllTaskByUserId(id);
		
		if ((listP.size()==0)&&(listT.size()==0)) {
			service.deleteById(id);
			resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
		}else {
			req.setAttribute("msg", "Delete user fail, must be delete task or delete user in their project first");
			List<User> users = service.findAll();

			if (users != null && !users.isEmpty())
				req.setAttribute("users", users);

			req.getRequestDispatcher(JspConst.USER_DASHBOARD).forward(req, resp);
		}
		
		
		
	}

	private void getUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UserDto dto = getUserById(req);
		req.setAttribute("UserToUpdate", dto);
		req.getRequestDispatcher(JspConst.USER_UPDATE).forward(req, resp);
	}

	private void getUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher(JspConst.USER_ADD).forward(req, resp);
	}

	private void getUserProfile(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

	}

	private void getUserDashboard(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<User> users = service.findAll();

		if (users != null && !users.isEmpty())
			req.setAttribute("users", users);

		req.getRequestDispatcher(JspConst.USER_DASHBOARD).forward(req, resp);
	}

	private void postUserAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserCreateDto dto = extractDtoFromReq(req);

		service.add(dto);

		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
	}

	private UserCreateDto extractDtoFromReq(HttpServletRequest req) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int roleId = Integer.parseInt(req.getParameter("role"));
		return new UserCreateDto(email, password, name, address, phone, roleId);
	}
	private UserDto getUserById(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		UserDto dto = service.getUserById(id);
		
		return service.getUserById(id);
	}
	private UserDto setUserToUpdate(HttpServletRequest req) {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int roleId = Integer.parseInt(req.getParameter("role"));
		return new UserDto(id,email, password, name, address, phone, roleId);
	}
}