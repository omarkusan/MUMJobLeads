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
import model.Posts;

@WebServlet("/posts")
public class PostsController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		System.out.println("hello posts");
		try {
			ConnectDB db = new ConnectDB();
			//int posttype = Integer.parseInt(req.getAttribute("posttype").toString());
			//int userid = Integer.parseInt(req.getAttribute("userid").toString());
			String jsonPosts = new Gson().toJson(db.retrievePosts(1,1));
			
			out.write(jsonPosts);
		} catch (Exception e) {
			out.write("Error on /posts " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("userid", 1);
		req.setAttribute("posttype", 1);
		doPost(req, resp);
	}
}

@WebServlet("/posts/update")
class UpdatePost extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2614944191676101982L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		try {
			Posts post = new Posts();
			post.setPost(req.getAttribute("post").toString());;
			post.setDateupdated((java.sql.Date) (new Date()));
			post.setPosttype(Integer.parseInt(req.getAttribute("posttype").toString()));
			post.setUserid(Integer.parseInt(req.getAttribute("userid").toString()));
			post.setPostid(Integer.parseInt(req.getAttribute("userid").toString()));
			
			//ConnectDB db = new ConnectDB();
			//db.up(post);
			out.write("successful");
		} catch (Exception e) {
			out.write("Error on /posts/update " + e.getMessage());
			e.printStackTrace();
		}
	}
}

@WebServlet("/posts/delete")
class DeletePost extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -131173447424919004L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		try {
			int postId = Integer.parseInt(req.getAttribute("postid").toString());
			int userId = Integer.parseInt(req.getAttribute("userid").toString());
			
			try {
				ConnectDB db = new ConnectDB();
				db.deletePost(postId, userId);
				out.write("successful");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.write("Error: when insert comment. Error message is " + e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			out.write("Error: /posts/delete " + e.getMessage());
			e.printStackTrace();
		}
	}
}

@WebServlet("/posts/insert")
class InsertPost extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -131173447424919004L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		try {
			Posts post = new Posts();
			post.setPost(req.getAttribute("post").toString());;
			post.setDatecreated((java.sql.Date) (new Date()));
			post.setDateupdated((java.sql.Date) (new Date()));
			post.setPosttype(Integer.parseInt(req.getAttribute("posttype").toString()));
			post.setUserid(Integer.parseInt(req.getAttribute("userid").toString()));
			
			
			try {
				ConnectDB db = new ConnectDB();
				db.insertPost(post);
				out.write("successful");
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
