package com.naso.post;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import com.google.gson.Gson;

@WebServlet("/post/*")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nextPage = "";
		String action = request.getPathInfo();
		HttpSession session = request.getSession();
		Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");

		System.out.println(action);
		try {
			if (loggedIn != null) {
				// 로그인된 상태이므로 메인 페이지로 이동
				if (action.equals("/*"))
					action = "/my.do";
			} else {
				// 로그인되지 않은 상태이므로 로그인 페이지로 리다이렉트
				response.sendRedirect(request.getContextPath() + "/member/");
				return;
			}

			switch (action) {
			case "/my.do": {
				PostDAO pd = new PostDAO();
				String userId = (String) session.getAttribute("id");

				ArrayList<PostDTO> photoList = new ArrayList<>();
				ArrayList<PostDTO> videoList = new ArrayList<>();
				// 포토리스트 저장하는 곳
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
				PostDAO pd = new PostDAO();

				String userID = (String) session.getAttribute("id");
				String exposure = request.getParameter("exposure");
				String category = "photo";
				String originalFileName = request.getParameter("originalFileName");
				String newFileName = request.getParameter("newFileName");
				String content = request.getParameter("content");

				if (pd.insertPhoto(userID, exposure, category, originalFileName, newFileName, content) == true) {
					nextPage = "/post/my.do";
					System.out.println("성공");
				} else {
					System.out.println("실패");
					nextPage = "/test.jsp";
				}
				break;
			}
			case "/replyShow.do": {
				PostDAO pd = new PostDAO();
				String pNum = request.getParameter("pNum");

				ArrayList<ReplyDTO> comments = new ArrayList<>();
				// 포토리스트 저장하는 곳
				try {
			        comments = pd.getCommentsByPNum(pNum);
			        
			        // 댓글 목록을 JSON 배열로 변환
//			        JSONArray jsonArray = new JSONArray();
			        
//			        for (ReplyDTO comment : comments) {
//			        	System.out.println(comments);
//			            JSONObject jsonComment = new JSONObject();
//			            jsonComment.put("userName", comment.getRUserId());
//			            jsonComment.put("commentText", comment.getRContent());
//			            jsonArray.add(jsonComment);
//			        }
			        
			        // 응답 데이터 생성
//			        JSONObject jsonResponse = new JSONObject();
//			        jsonResponse.put("comments", jsonArray);
			        
			        // 응답 데이터 전송
			        Gson gson = new Gson();
			        String json = gson.toJson(comments);
			        response.setContentType("application/json");
			        response.setCharacterEncoding("UTF-8");
			        PrintWriter out = response.getWriter();
			        out.print(json);
			        out.flush();
//			        response.getWriter().print(jsonResponse.toString());
			    } catch (SQLException e) {
			        e.printStackTrace();
			        // 처리 중 예외 발생 시 예외 처리
			    }

				nextPage = "/jsp/main.jsp";
				break;
			}
			case "/replyUpload.do": {
				PostDAO pd = new PostDAO();
				String pNum = request.getParameter("pNum");
				String commentText = request.getParameter("commentText");
				String userId = (String) session.getAttribute("id");

				if (pd.createComment(pNum, userId, commentText)) {
					// JSON 형태의 응답 데이터 생성
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("userName", userId);
					jsonResponse.put("commentText", commentText);

					// 응답 데이터 전송
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(jsonResponse.toString());
				} else {
					System.out.println("실패");
					nextPage = "/test.jsp";
				}
				break;
			}

			/*
			 * case "/diaryInput.do": { PostDAO pd = new PostDAO();
			 * 
			 * String content = request.getParameter("diaryText");
			 * 
			 * String userID = (String)session.getAttribute("id"); String exposure =
			 * request.getParameter("exposure"); String category = "photo"; String
			 * originalFileName = request.getParameter("originalFileName"); String
			 * newFileName = request.getParameter("newFileName"); String content =
			 * request.getParameter("content");
			 * 
			 * 
			 * 
			 * if(pd.DiaryInputDAO(content)) { nextPage = "/post/my.do";
			 * System.out.println("성공"); } else { System.out.println("실패"); nextPage =
			 * "/test.jsp"; } break; }
			 */
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
