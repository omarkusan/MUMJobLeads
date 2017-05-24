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

import dao.ConnectDB;
import model.Posts;
import java.time.LocalDate;

@WebServlet("/posts/insert")
public class PostsInsertController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -131173447424919004L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("hi");
		PrintWriter out = resp.getWriter();

		try {
			Posts post = new Posts();
			post.setPost(req.getParameter("post").toString());
			post.setPosttype(Integer.parseInt(req.getParameter("posttype").toString()));
			post.setUserid(Integer.parseInt(req.getParameter("userid").toString()));
			post.setTitle(req.getParameter("title").toString());
			post.setLocation(req.getParameter("location").toString());

			try {
				ConnectDB db = new ConnectDB();
				db.insertPost(post);
				out.write("successful");
				resp.sendRedirect("../main.html");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.write("Error: when insert comment. Error message is " + e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			out.write("Error: /posts/insert " + e.getMessage());
			e.printStackTrace();
		}
	}
}
