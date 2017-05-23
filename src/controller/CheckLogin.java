package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.connectDB;
import model.Users;

@WebServlet("/login")
public class CheckLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		connectDB db = new connectDB();
		Users user = db.retrieveUserInfo(req.getParameter("email"), "password");
		if(user != null){
			HttpSession session=req.getSession();  
	        session.setAttribute("userid",user.getUserid());
	        LoginSession.addSession(session);
	        req.setAttribute("message", "Welcome MUMJobLeadsProject");
	        req.setAttribute("status", true);
	        req.getRequestDispatcher("home.jsp");
		}
		else{
			req.setAttribute("message", "Please check email and password");
			req.setAttribute("status", false);
		}
	}
}
