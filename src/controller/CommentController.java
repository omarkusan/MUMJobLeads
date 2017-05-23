package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import com.google.gson.Gson;

import dao.ConnectDB;
import model.Comments;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	
@WebServlet("/comments")
public class CommentController extends HttpServlet{
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
			int userid = Integer.parseInt(req.getAttribute("userid").toString());
			String jsonComments = new Gson().toJson(db.retrieveComments(postid, userid));
			
			out.write(jsonComments);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.write("Error on /comments: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
}

@WebServlet("/comments/delete")
class DeleteComment extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -131173447424919004L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		try {
			int commentid = Integer.parseInt(req.getAttribute("commentid").toString());
			int userid = Integer.parseInt(req.getAttribute("userid").toString());
			
			
			try {
				ConnectDB db = new ConnectDB();
				db.deleteComment(commentid, userid);
				out.write("successful");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.write("Error: when insert comment. Error message is " + e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.write("Error on comments/delete " + e.getMessage());
			e.printStackTrace();
		}
	}
}

@WebServlet("/comments/insert")
class InsertComment extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -131173447424919004L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		try {
			Comments comment = new Comments();
			comment.setComment(req.getAttribute("comment").toString());
			comment.setDatecreated((java.sql.Date) (new Date()));
			comment.setDateupdated((java.sql.Date) (new Date()));
			comment.setPostid(Integer.parseInt(req.getAttribute("postid").toString()));
			comment.setUserid(Integer.parseInt(req.getAttribute("userid").toString()));
			
			try {
				ConnectDB db = new ConnectDB();
				db.insertComment(comment);
				out.write("successful");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				out.write("Error: when insert comment. Error message is " + e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.write("Error on comments/insert" + e.getMessage());
			e.printStackTrace();
		}
	}
}

