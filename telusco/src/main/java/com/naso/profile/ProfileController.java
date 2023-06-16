package com.naso.profile;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/prfCtrl")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");		// search.jsp에서 계정의 아이디를 넘겨받음.
		
		ProfileDAO dao = new ProfileDAO();
		ArrayList<ProfileDTO> targetProfile = dao.searchUser(userId); 		
		
		// 계정 정보를 profile.jsp로 넘김
		request.setAttribute("targetProfile", targetProfile);
		RequestDispatcher rd = request.getRequestDispatcher("/html/profile.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}