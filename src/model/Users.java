package model;

import java.sql.Date;

public class Users {
	int userid;
	String fullname;
	int gender;
	String state;
	String city;
	String street;
	int zipcode;
	int birthyear;
	String email;
	String password;
	Date datecreated;
	Date dateupdated;

	public Users(int userid, String fullname, int gender, String state, String city, String street, int zipcode,
			int birthyear, String email, Date datecreated, Date dateupdated) {
		this.userid = userid;
		this.fullname = fullname;
		this.gender = gender;
		this.state = state;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
		this.birthyear = birthyear;
		this.email = email;
		this.datecreated = datecreated;
		this.dateupdated = dateupdated;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public int getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
