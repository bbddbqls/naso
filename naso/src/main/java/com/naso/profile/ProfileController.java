package com.naso.profile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.naso.follow.FollowDAO;
import com.naso.post.PostDAO;
import com.naso.post.PostDTO;

@WebServlet("/prfCtrl")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Boolean checkFollow = false;
		String myMNum = (String) session.getAttribute("mNum");
		String othMNum = request.getParameter("mNum");

		FollowDAO fd = new FollowDAO();
		ProfileDAO dao = new ProfileDAO();
		ArrayList<ProfileDTO> targetProfile = dao.searchUser(othMNum);
		
		 int postCount = dao.postCount(othMNum); 
		 int followingCount = dao.followingCount(othMNum); 
		 int followerCount = dao.followerCount(othMNum);
		 
		 ArrayList<PostDTO.PostItemDTO> itemList = new ArrayList<>();
		 PostDAO pd = new PostDAO();
		 try {
			 checkFollow = fd.checkFollow(myMNum, othMNum);
			itemList = pd.List(othMNum, checkFollow);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 계정 정보를 profile.jsp로 넘김
		request.setAttribute("targetProfile", targetProfile);
		
		  request.setAttribute("postCount", postCount);
		  request.setAttribute("followingCount", followingCount);
		  request.setAttribute("followerCount", followerCount);
		  request.setAttribute("checkFollow", checkFollow);
		  request.setAttribute("itemList", itemList);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/profile.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

}