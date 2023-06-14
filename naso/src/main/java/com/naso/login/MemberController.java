package com.naso.login;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDTO memberDTO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nextPage = "";
		String action = request.getPathInfo();

		try {
			if (action == null || action.equals("/")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/login.jsp"); 
	            dispatcher.forward(request, response);
			}

			switch (action) {
			case "/login.do": {
				MemberDAO md = new MemberDAO();
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				
				if(md.authenticateUser(id, pw)==true) {
					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					session.setAttribute("loggedIn", true);
					response.sendRedirect(request.getContextPath() + "/post/my.do");
					
				}
				else {
					nextPage = "/jsp/login.jsp";
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + action);
			}
			if (!nextPage.equals("")) {
				
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
