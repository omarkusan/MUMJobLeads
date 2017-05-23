package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConnectDB;
import model.Users;

/*@WebFilter("/*")
class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
        	System.out.println("1");
            chain.doFilter(request, response);
        } else {
        	System.out.println("2");
            response.sendRedirect(loginURI);
        }
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//doFilter(arg0.getServletContext().req, res, chain);
		
	}

    // ...
}*/

@WebServlet("/login")
public class CheckLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		ConnectDB db = new ConnectDB();
		Users user = db.retrieveUserInfo(email, password);
		System.out.println(user);
		if(user != null){
			HttpSession session=req.getSession();  
	        session.setAttribute("userid",user.getUserid());
	        LoginSession.addSession(session);
	        req.setAttribute("message", "Welcome MUMJobLeadsProject");
	        req.setAttribute("status", true);
	        resp.sendRedirect("main.html");
	        //req.getRequestDispatcher("main.html");
		}
		else{
			req.setAttribute("message", "Please check email and password");
			req.setAttribute("status", false);
			 resp.sendRedirect("login.html");
		}
	}
}
