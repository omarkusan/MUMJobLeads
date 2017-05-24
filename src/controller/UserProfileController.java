package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ConnectDB;
import model.Comments;
import model.Users;


@WebServlet("/userinfo/insert")
public class UserProfileController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			Users user = new Users();
			user.setBirthyear(req.getAttribute("year").toString() + "-" + req.getAttribute("month").toString() + "-"+ req.getAttribute("month").toString());
			/*user.setDatecreated((java.sql.Date) (new Date()));
			user.setDateupdated((java.sql.Date) (new Date()));*/
			user.setStreet(req.getAttribute("street").toString());
			user.setCity(req.getAttribute("city").toString());
			user.setState(req.getAttribute("state").toString());
			user.setPassword(req.getAttribute("password").toString());
			user.setEmail(req.getAttribute("email").toString());
			user.setFullname(req.getAttribute("fullname").toString());
			user.setGender((Integer.parseInt(req.getAttribute("gender").toString())));
			user.setZipcode((Integer.parseInt(req.getAttribute("zipcode").toString())));
			
			try {
				ConnectDB db = new ConnectDB();
				db.insertUserInfo(user);
				out.write("successful");
				resp.sendRedirect("../main.html");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.write("Error: when insert comment. Error message is " + e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.write("Error on userinfo/insert" + e.getMessage());
			e.printStackTrace();
		}
	}
}

@WebServlet("/userinfo/update")
class UpdateUserInfo extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2614944191676101982L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			Users user = new Users();
			user.setUserid(Integer.parseInt(req.getAttribute("userid").toString()));
			user.setBirthyear(req.getAttribute("year").toString() + "-" + req.getAttribute("month").toString() + "-"+ req.getAttribute("month").toString());
			//user.setDateupdated((java.sql.Date) (new Date()));
			user.setStreet(req.getAttribute("street").toString());
			user.setCity(req.getAttribute("city").toString());
			user.setState(req.getAttribute("state").toString());
			user.setPassword(req.getAttribute("password").toString());
			user.setEmail(req.getAttribute("email").toString());
			user.setFullname(req.getAttribute("fullname").toString());
			user.setGender((Integer.parseInt(req.getAttribute("gender").toString())));
			user.setZipcode((Integer.parseInt(req.getAttribute("zipcode").toString())));
			
			try {
				ConnectDB db = new ConnectDB();
				db.updateUserInfo(user);
				out.write("successful");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.write("Error: when insert comment. Error message is " + e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.write("Error on userinfo/insert" + e.getMessage());
			e.printStackTrace();
		}
	}
}


