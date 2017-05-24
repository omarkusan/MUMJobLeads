package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			String email = req.getAttribute("email").toString();
			String password = req.getAttribute("password").toString();
			ConnectDB db = new ConnectDB();
			String users = new Gson().toJson(db.retrieveUserInfo(email, password));
			out.write("users");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.write("Error on userinfo/insert" + e.getMessage());
			e.printStackTrace();
		}
	}
}
