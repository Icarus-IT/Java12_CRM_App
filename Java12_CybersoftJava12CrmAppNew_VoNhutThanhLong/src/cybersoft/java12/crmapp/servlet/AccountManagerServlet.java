package cybersoft.java12.crmapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java12.crmapp.dto.UserDto;
import cybersoft.java12.crmapp.service.UserService;
import cybersoft.java12.crmapp.util.JspConst;
import cybersoft.java12.crmapp.util.UrlConst;

@WebServlet(name = "accountServlet", urlPatterns = { UrlConst.USER_PROFILE, UrlConst.USER_UPDATE_PROFILE })
public class AccountManagerServlet extends HttpServlet{
	private UserService uService;
	public AccountManagerServlet() {
		uService = new UserService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.USER_PROFILE:
			getUserProfile(req, resp);
			break;
		case UrlConst.USER_UPDATE_PROFILE:
			getUserUpdateProfile(req, resp);
			break;
		}
	}
	private void getUserProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher(JspConst.USER_PROFILE).forward(req, resp);
		
	}
	private void getUserUpdateProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(JspConst.USER_UPDATE_PROFILE).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.USER_PROFILE:
			postUserProfile(req, resp);
			break;
		case UrlConst.USER_UPDATE_PROFILE:
			postUserUpdateProfile(req, resp);
			break;
		}
	}
	private void postUserProfile(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	private void postUserUpdateProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		UserDto uDto = (UserDto) req.getSession().getAttribute("UserInfo");
		uDto.setPassword(password);
		uDto.setName(name);
		uDto.setPhone(phone);
		uDto.setAddress(address);
		uService.UpdateUserAndPass(uDto);		
		HttpSession session = req.getSession();

		session.setAttribute("UserInfo", uService.getUserWhenLogin(email));
	
		resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
		
	}
}
