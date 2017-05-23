package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter("/*")
public class LoginFilter implements Filter {
	
	private ServletContext context;

	//public LoginFilter(){}
	
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
    	System.out.println("hello its me");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login.html";

        boolean loggedIn = session != null;// && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        
        System.out.println("hello its me22222 + " + loggedIn + " zzzzzzzz " + loginRequest);

        if (loggedIn || loginRequest) {
        	System.out.println("doFilter called");
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(loginURI);
        }
    }

	@Override
	public void destroy() {
		this.context = null;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.context = arg0.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}

    // ...
}
