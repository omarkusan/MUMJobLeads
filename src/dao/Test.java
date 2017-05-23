package dao;

public class Test {
	public static void main(String[] args) {
		ConnectDB db = new ConnectDB();
		System.out.println(db.retrieveUserInfo("adondovdorj@mum.edu", "123456").getEmail());
	}
}
