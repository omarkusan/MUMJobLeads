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
import model.Likes;

public class LikesController extends HttpServlet {
	@WebServlet("/likes")
	public class GetLikes extends HttpServlet{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			PrintWriter out = resp.getWriter();
			
			try {
				ConnectDB db = new ConnectDB();
				int postid = Integer.parseInt(req.getAttribute("postid").toString());
				String jsonComments = new Gson().toJson(db.retrieveLikes(postid));
				out.write(jsonComments);
			} catch (Exception e) {
				out.write("Error on /likes " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@WebServlet("/likes/add")
	class addLike extends HttpServlet{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2614944191676101982L;

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			PrintWriter out = resp.getWriter();
			
			try {
				ConnectDB db = new ConnectDB();
				Likes like = new Likes();
				like.setPostid(Integer.parseInt(req.getAttribute("postid").toString()));
				like.setUserid(Integer.parseInt(req.getAttribute("userid").toString()));
				like.setDatecreated((java.sql.Date) (new Date()));
				like.setDateupdated((java.sql.Date) (new Date()));
				
				
				try {
					if(db.setLike(like))
						out.write("successful");
					else
						out.write("Error: when set like");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					out.write("Error: when set like. Error message: " + e.getMessage());
					e.printStackTrace();
				}
			} catch (Exception e) {
				out.write("Error on likes/add " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
