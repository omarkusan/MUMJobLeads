package model;

import java.sql.Date;

public class Likes {
	int likeid;
	int userid;
	int postid;
	Date datecreated;
	Date dateupdated;
	
	public Likes(){}
	
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

	public void setLikeid(int likeid) {
		this.likeid = likeid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}
	
	
}
