package model;

import java.sql.Date;

public class Posts {
	int postid;
	int userid;
	String post;
	int posttype;
	Date datecreated;
	Date dateupdated;
	boolean isLiked;
	boolean isOwn;
	
	public Posts(int postid, int userid, String post, int posttype, Date datecreated, Date dateupdated, boolean isLiked, boolean isOwn) {
		this.postid = postid;
		this.userid = userid;
		this.post = post;
		this.posttype = posttype;
		this.datecreated = datecreated;
		this.dateupdated = dateupdated;
		this.isLiked = isLiked;
		this.isOwn = isOwn;
	}

	public int getPostid() {
		return postid;
	}

	public int getUserid() {
		return userid;
	}

	public String getPost() {
		return post;
	}

	public int getPosttype() {
		return posttype;
	}

	public Date getDatecreated() {
		return datecreated;
	}

	public Date getDateupdated() {
		return dateupdated;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public boolean isOwn() {
		return isOwn;
	}
	  
	  
}
