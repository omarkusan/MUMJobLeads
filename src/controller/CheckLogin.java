package controller;

import java.io.IOException;

<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/omarkusan/MUMJobLeads.git
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/omarkusan/MUMJobLeads.git
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
		Users user = db.retrieveUserInfo(email, password);
		if(user != null){
			System.out.println("logged");
			HttpSession session=req.getSession();  
	        session.setAttribute("userid",user.getUserid());
	        LoginSession.addSession(session);
	        req.setAttribute("message", "Welcome MUMJobLeadsProject");
	        req.setAttribute("status", true);
	        req.setAttribute("session", session);
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

