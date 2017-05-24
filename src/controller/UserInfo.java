package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.ConnectDB;

@WebServlet("/userinfo")
public class UserInfo extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2614944191676101982L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			HttpSession session = req.getSession(false);
			String email = session.getAttribute("email").toString();
			//String password = req.getAttribute("password").toString();
			ConnectDB db = new ConnectDB();
			String users = new Gson().toJson(db.retrieveUserInfo(email));
			out.write(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.write("Error on userinfo/get" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
