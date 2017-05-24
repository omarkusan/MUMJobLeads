package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConnectDB;
import model.Users;


@WebServlet("/login")
public class CheckLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		ConnectDB db = new ConnectDB();
		Users user = db.retrieveUserInfo(email);
		if(user != null){
			System.out.println("logged");
			HttpSession session=req.getSession();  
	        session.setAttribute("userid",user.getUserid());
	        session.setAttribute("email",user.getEmail());
	        LoginSession.addSession(session);
	        req.setAttribute("message", "Welcome MUMJobLeadsProject");
	        req.setAttribute("status", true);
	        req.setAttribute("session", session);
	        req.setAttribute("email", user.getEmail());
	        resp.sendRedirect("main.html");
	        //req.getRequestDispatcher("main.html");
		}
		else{
			System.out.println("notlogged");
			req.setAttribute("message", "Please check email and password");
			req.setAttribute("status", false);
			resp.sendRedirect("login.html");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}

