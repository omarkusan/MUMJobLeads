package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import com.mysql.jdbc.PreparedStatement;

import model.Comments;
import model.Likes;
import model.Posts;
import model.Users;

public class ConnectDB {

    private static ConnectDB instance = new ConnectDB();
    public static final String URL = "jdbc:mysql://10.10.11.66:3306/carpoolingdb?useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "root123";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    
    public ConnectDB() {
    	try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    Connection createConnection() {
        Connection connection = null;
        try {
            //Step 3: Establish Java MySQL connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }   
     
    public static Connection getConnection() {
        return instance.createConnection();
    }
    
    /*******select**********/
    
    public Users retrieveUserInfo(String email, String password) {
        String readQuery = "SELECT * from users where email = '" + email + "' and password = '"+password+"';";
        //String fullname = "No information found for the requested user: " + email;
        Users user = null;
        try (Statement stmt = getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(readQuery);
            while (rs.next()) {
            	user = new Users(
            			rs.getInt("userid"), 
            			rs.getString("fullname"), 
            			rs.getInt("gender"), 
            			rs.getString("state"), 
            			rs.getString("city"), 
            			rs.getString("street"), 
            			rs.getInt("zipcode"), 
            			rs.getInt("birthyear"), 
            			rs.getString("email"),
            			rs.getDate("datecreated"), 
            			rs.getDate("dateupdated"));
            }
            stmt.close();

        } catch (SQLException s) {
            System.out.println("Exception thrown in retrieveUser ....");
            s.printStackTrace();
        }
        return user;

    }
  
    public List<Posts> retrievePosts(int userid, int postType) {
    	List<Posts> posts = new ArrayList<Posts>();
        String readQuery =
		        "select posts.*, " +
		        "(select ifnull(count(likes.likeid),0) from likes where likes.postid = posts.postid) as likes, " +
		        "(select ifnull(count(comments.commentid),0) from comments where comments.postid = posts.postid) as comments, " + 
		        "ifnull((select count(*) from likes where likes.postid = posts.postid and likes.userid = 1),0) as isLiked  " +
		        "from posts " +
		        "where posttype = 1 " + 
		        "order by posts.dateupdated desc; ";
        //String fullname = "No information found for the requested user: " + email;
        System.out.println(readQuery);
        try (Connection con = getConnection();
                Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery(readQuery);
            while (rs.next()) {
            	Posts post = new Posts(
            			rs.getInt("postid"), 
            			rs.getInt("userid"), 
            			rs.getString("title"),
            			rs.getString("location"),
            			rs.getString("post"), 
            			rs.getInt("posttype"),
            			rs.getDate("datecreated"), 
            			rs.getDate("dateupdated"),
            			rs.getBoolean("isLiked"),
            			userid == rs.getInt("postid")
            			);
            	posts.add(post);
            }
            stmt.close();

        } catch (SQLException s) {
            System.out.println("Exception thrown in retrieveUser ....");
            s.printStackTrace();
        }
        return posts;

    }

    public List<Comments> retrieveComments(int postid, int userid) {
    	List<Comments> comments = new ArrayList<Comments>();
        String readQuery = 
        		"select * from comments where postid = " + postid;
        //String fullname = "No information found for the requested user: " + email;
        try (Connection con = getConnection();
                Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery(readQuery);
            while (rs.next()) {
            	Comments post = new Comments(
            			rs.getInt("commentid"), 
            			rs.getInt("userid"), 
            			rs.getInt("postid"), 
            			rs.getString("comment"), 
            			rs.getDate("datecreated"), 
            			rs.getDate("dateupdated"),
            			userid == rs.getInt("userid")
            			);
            	comments.add(post);
            }
            stmt.close();

        } catch (SQLException s) {
            System.out.println("Exception thrown in retrieveUser ....");
            s.printStackTrace();
        }
        return comments;
    }
    
    public List<Likes> retrieveLikes(int postid) {
    	List<Likes> likes = new ArrayList<Likes>();
        String readQuery = 
        		"select * from likes where postid = " + postid;
        //String fullname = "No information found for the requested user: " + email;
        try (Connection con = getConnection();
                Statement stmt = con.createStatement();) {
            ResultSet rs = stmt.executeQuery(readQuery);
            while (rs.next()) {
            	Likes like = new Likes(
            			rs.getInt("likeid"), 
            			rs.getInt("userid"), 
            			rs.getInt("postid"),  
            			rs.getDate("datecreated"), 
            			rs.getDate("dateupdated")
            			);
            	likes.add(like);
            }
            stmt.close();

        } catch (SQLException s) {
            System.out.println("Exception thrown in retrieveUser ....");
            s.printStackTrace();
        }
        return likes;

    }
    
    /******* insert
     * @throws SQLException **********/
    public boolean insertUserInfo(Users user) throws SQLException{
    	
    	String sql = "INSERT INTO users(fullname,gender,state,city,street,zipcode,birthyear,email,password,datecreated,dateupdated,) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = getConnection();
		PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
				
		statement.setString(1, user.getFullname());
		statement.setInt(2, user.getGender());		
		statement.setString(3, user.getState());
		statement.setString(4, user.getCity());
		statement.setString(5, user.getStreet());
		statement.setInt(6, user.getZipcode());
		statement.setInt(7, user.getBirthyear());
		statement.setString(8, user.getEmail());
		statement.setString(9, user.getPassword());
		statement.setDate(10, user.getDatecreated());
		statement.setDate(11, user.getDateupdated());
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
		    return true;
		}
    	return false;
    }
    
    public boolean insertComment(Comments comment) throws SQLException{
    	
    	String sql = "INSERT INTO comments(userid, postid, comment, datecreated, dateupdated) values(?,?,?,?,?)";
    	Connection conn = getConnection();
    	PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
    	
    	statement.setInt(1, comment.getUserid());
    	statement.setInt(2, comment.getPostid());
    	statement.setString(3, comment.getComment());
    	statement.setDate(4, comment.getDatecreated());
    	statement.setDate(5, comment.getDateupdated());
    	
    	if(statement.executeUpdate() > 0)
    		return true;
    	
    	return false;
    }
    
    public boolean setLike(Likes like) throws SQLException{
    	
    	String sql = "INSERT INTO Likes(userid, postid, datecreated, dateupdated) values(?,?,?,?)";
    	Connection conn = getConnection();
    	PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
    	
    	statement.setInt(1, like.getUserid());
    	statement.setInt(2, like.getPostid());
    	statement.setDate(3, like.getDatecreated());
    	statement.setDate(4, like.getDateupdated());
    	
    	if(statement.executeUpdate() > 0)
    		return true;
    	
    	return false;
    }

    public boolean insertPost(Posts post) throws SQLException{
    	
    	String sql = "INSERT INTO Posts(userid,post,title,location,posttype,datecreated,dateupdated) values(?,?,?,?,?,?,?)";
    	PreparedStatement statement = (PreparedStatement)getConnection().prepareStatement(sql);
    	
    	statement.setInt(1, post.getUserid());
    	statement.setString(2, post.getPost());
    	statement.setString(3, post.getTitle());
    	statement.setString(4, post.getLocation());
    	statement.setInt(5, post.getPosttype());
    	statement.setDate(6, post.getDatecreated());
    	statement.setDate(7, post.getDateupdated());
    	
    	if(statement.executeUpdate() > 0)
    		return true;
    	
    	return false;
    }

    /************ delete
     * @throws SQLException ***************/
    
    public boolean deletePost(int postId, int userId) throws SQLException{
    	
    	String sql = "Delete from posts where postid = " + postId + " and userid = "+userId;
    	PreparedStatement statement = (PreparedStatement)getConnection().prepareStatement(sql);
    	if(statement.executeUpdate() > 0)
    		return true;
    	return false;
    }
    
    public boolean deleteComment(int commentId, int userId) throws SQLException{
    	String sql = "Delete from comments where postid = " + commentId + " and userid = "+userId;
    	PreparedStatement statement = (PreparedStatement)getConnection().prepareStatement(sql);
    	if(statement.executeUpdate() > 0)
    		return true;
    	return false;
    }
    
    boolean takeOutLike(int likeId, int userId) throws SQLException{
    	String sql = "Delete from likes where postid = " + likeId + " and userid = "+userId;
    	PreparedStatement statement = (PreparedStatement)getConnection().prepareStatement(sql);
    	if(statement.executeUpdate() > 0)
    		return true;
    	return false;
    }
    
    /************ update 
     * @throws SQLException *************/
    
    public boolean updateUserInfo(Users user) throws SQLException{
    	String sql = "UPDATE users SET fullname=?,gender=?,state=?,city=?,street=?,zipcode=?,birthyear=?,email=?,password=?,datecreated=?,dateupdated=? WHERE userid = "+ user.getUserid();
		Connection conn = getConnection();
		PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
				
		statement.setString(1, user.getFullname());
		statement.setInt(2, user.getGender());		
		statement.setString(3, user.getState());
		statement.setString(4, user.getCity());
		statement.setString(5, user.getStreet());
		statement.setInt(6, user.getZipcode());
		statement.setInt(7, user.getBirthyear());
		statement.setString(8, user.getEmail());
		statement.setString(9, user.getPassword());
		statement.setDate(10, user.getDatecreated());
		statement.setDate(11, user.getDateupdated());
		int rowsUpdated = statement.executeUpdate();
		if (rowsUpdated > 0) {
		    return true;
		}
    	return false;
    }
    
    public boolean updatePost(Posts post) throws SQLException{
    	String sql = "UPDATE Posts SET userid=?,post=?,dateupdated=? WHERE postid =" + post.getPostid();
    	PreparedStatement statement = (PreparedStatement)getConnection().prepareStatement(sql);
    	
    	statement.setInt(1, post.getUserid());
    	statement.setString(2, post.getPost());
    	statement.setDate(3, post.getDateupdated());
    	
    	if(statement.executeUpdate() > 0)
    		return true;
    	
    	return false;
    }
    
    
}