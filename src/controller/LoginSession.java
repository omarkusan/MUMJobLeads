package controller;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class LoginSession{
	private Set<HttpSession> userSessions = Collections.newSetFromMap(new ConcurrentHashMap<HttpSession, Boolean>());
	private static LoginSession instance = new LoginSession();
	
	public LoginSession() {
		
	}
	
	public static void addSession(HttpSession user){
		instance.userSessions.add(user);
	}
	
	public static void deleteSession(HttpSession user){
		instance.userSessions.remove(user);
	}
	
	public static Set<HttpSession> getSessions(){
		return instance.userSessions;
	}

}
