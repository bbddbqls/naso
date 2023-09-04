package com.naso.follow;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.naso.member.MemberDAO;

@WebServlet("/follow/*")
public class FollowController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nextPage = "";
		String action = request.getPathInfo();
		try {
			switch (action) {
			case "/follow.do": {
				String myMNum = request.getParameter("myMNum");
				String otherMNum = request.getParameter("otherMNum");
				
				Boolean result = false;
				FollowDAO fd = new FollowDAO();
				result = fd.follow(myMNum, otherMNum);
				
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				out.write(String.valueOf(result));
				break;
			}
			case "/unfollow.do": {
				String myMNum = request.getParameter("myMNum");
				String otherMNum = request.getParameter("otherMNum");
				
				System.out.println(myMNum);
				System.out.println(otherMNum);
				
				Boolean result = false;
				FollowDAO fd = new FollowDAO();
				result = fd.nufollow(myMNum, otherMNum);

				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				out.write(String.valueOf(result));
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
