package model;

import java.sql.Date;

public class Likes {
	int likeid;
	int userid;
	int postid;
	Date datecreated;
	Date dateupdated;
	
	public Likes(int likeid, int userid, int postid, Date datecreated, Date dateupdated) {
		this.likeid = likeid;
		this.userid = userid;
		this.postid = postid;
		this.datecreated = datecreated;
		this.dateupdated = dateupdated;
	}

	public int getLikeid() {
		return likeid;
	}

	public int getUserid() {
		return userid;
	}

	public int getPostid() {
		return postid;
	}

	public Date getDatecreated() {
		return datecreated;
	}

	public Date getDateupdated() {
		return dateupdated;
	}
	
	
}
