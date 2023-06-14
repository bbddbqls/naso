package com.naso.post;

import java.io.Console;
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
import javax.servlet.http.Part;



@WebServlet("/post/*")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nextPage = "";
		String action = request.getPathInfo();
		HttpSession session = request.getSession();
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        
        System.out.println(action);
		try {
	        if (loggedIn != null) {
	            // 로그인된 상태이므로 메인 페이지로 이동
	        	if(action.equals("/*"))
	        	action = "/my.do";
	        } else {
	            // 로그인되지 않은 상태이므로 로그인 페이지로 리다이렉트
	        	response.sendRedirect(request.getContextPath() + "/member/");
	        	return;
	        }
	        
			switch (action) {
			case "/my.do": {
				System.out.println("asd");
				PostDAO pd = new PostDAO();
				String userId = (String)session.getAttribute("id");
				
				ArrayList<PostDTO> photoList = new ArrayList<>();
				ArrayList<PostDTO> videoList = new ArrayList<>();
				//포토리스트 저장하는 곳
				try {
		            photoList = pd.photoList(userId);
		            videoList = pd.videoList(userId);
		        } catch (SQLException e) {
		            e.printStackTrace();
		            // 처리 중 예외 발생 시 예외 처리
		        }
			
		        request.setAttribute("photoList", photoList);
		        request.setAttribute("videoList", videoList);
		        
		        nextPage = "/jsp/main.jsp";
				break;
				}
			case "/imageUpload.do": {
				System.out.println("imageUpload.do 실행");
				PostDAO pd = new PostDAO();
				System.out.println((String)session.getAttribute("id"));
				System.out.println(request.getParameter("exposure"));
				System.out.println(request.getParameter("originalFileName"));
				System.out.println(request.getParameter("newFileName"));
				System.out.println(request.getParameter("content"));
				
				String userID = (String)session.getAttribute("id");
				String exposure = request.getParameter("exposure");
				String category = "photo";
				String originalFileName = request.getParameter("originalFileName");
				String newFileName = request.getParameter("newFileName");
				String content = request.getParameter("content");

				
				if(pd.insertPhoto(userID, exposure, category, originalFileName, newFileName, content)==true) {
					nextPage = "/post/my.do";
					System.out.println("성공");
				}
				else {
					System.out.println("실패");
					nextPage = "/test.jsp";
				}
				break;
			}
			/*
			 * case "/showPhotoList.do": { PostDAO pd = new PostDAO();
			 * 
			 * 
			 * String userID = (String)session.getAttribute("id");
			 * 
			 * if(pd.insertPhoto(userID)==true) { nextPage = "/jsp/main.jsp";
			 * System.out.println("성공"); } else { System.out.println("실패"); nextPage =
			 * "/test.jsp"; } break; }
			 */
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
