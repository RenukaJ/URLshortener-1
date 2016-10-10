package com.cpsc476.urlshortner;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
        name = "UserProfileServlet",
        urlPatterns = "/userprofile"
)
public class UserProfilePage extends HttpServlet{
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
		HttpSession session = request.getSession();

        
        request.setAttribute("username", session.getAttribute("username"));

        request.getRequestDispatcher("/WEB-INF/jsp/view/userprofile.jsp")
               .forward(request, response);
    }
}
