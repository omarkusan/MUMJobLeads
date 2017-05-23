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
	
	public Posts(){}
	
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

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public void setPosttype(int posttype) {
		this.posttype = posttype;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}
	  
}
