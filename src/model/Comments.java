package model;

import java.sql.Date;

public class Comments {
	int commentid;
	int userid;
	int postid;
	String comment;
	Date datecreated;
	Date dateupdated;
	boolean isOwn;
	
	public Comments(){}
	
	public Comments(int commentid, int userid, int postid, String comment, Date datecreated, Date dateupdated, boolean isOwn) {
		this.commentid = commentid;
		this.userid = userid;
		this.postid = postid;
		this.comment = comment;
		this.datecreated = datecreated;
		this.dateupdated = dateupdated;
		this.isOwn = isOwn;
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	public Date getDateupdated() {
		return dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}
	
	
}
