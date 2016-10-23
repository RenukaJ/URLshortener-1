package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet
{
    protected static final Map<String, String> userDatabase = new Hashtable<>();

    static {
        userDatabase.put("Nicholas", "password");
        userDatabase.put("Sarah", "drowssap");
        userDatabase.put("Mike", "wordpass");
        userDatabase.put("John", "green");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    	System.out.println("loginServlet");
        HttpSession session = request.getSession();
        //We might need to remove the first 'if' condition (Please don't remove - Ashish)
        if(request.getParameter("logout") != null)
        {
            session.invalidate();
            response.sendRedirect("home");
            return;
        }
        else if(session.getAttribute("username") != null)
        {
            
        	response.sendRedirect("userprofile");
            return;
        }
        
        request.setAttribute("loginFailed", false);
	     request.getRequestDispatcher("/WEB-INF/jsp/view/home.jsp")
	               .forward(request, response);
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    	
    	
    	String action = request.getParameter("action");
        System.out.println(action + "in ok");
        if(action == null)
            action = "page";
        switch(action)
        {
            case "login":
                this.login(request, response);
                break;
            case "signup":
            default:
            	this.signup(request, response);
                break;
        }
        
    }
    
    
    private void login(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
    	HttpSession session = request.getSession();
        if(session.getAttribute("username") != null)
        {
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username == null || password == null ||
                !LoginServlet.userDatabase.containsKey(username) ||
                !password.equals(LoginServlet.userDatabase.get(username)))
        {
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/InvalidLogin.jsp")
                   .forward(request, response);
            
        }
        else
        {
        	
            session.setAttribute("username", username);
            request.changeSessionId();
            response.sendRedirect("userprofile");
        }
	     
	}
    
    
    
    private void signup(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
    	System.out.println("In Signup");
    	HttpSession session = request.getSession();
        if(session.getAttribute("username") != null)
        {
            return;
        }

        String username = request.getParameter("new_username");
        String password = request.getParameter("new_password");
        if(username == null || password == null ||
                LoginServlet.userDatabase.containsKey(username))
        {
        	System.out.println("wrongplace");
        	//Todo -> Need to give proper error message
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/InvalidLogin.jsp")
                   .forward(request, response);
            
        }
        else
        {
        	System.out.println("right place");
        	userDatabase.put(username, password);
            session.setAttribute("username", username);
            request.changeSessionId();
            response.sendRedirect("userprofile");
        }
	}
    
    
    
    
}
