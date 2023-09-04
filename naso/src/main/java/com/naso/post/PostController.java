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
import com.naso.follow.FollowDAO;
import com.naso.member.MemberDAO;
import com.naso.member.MemberDTO;

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
				MemberDAO md = new MemberDAO();
				String mNum = (String) session.getAttribute("mNum");
				String mUserId = (String) session.getAttribute("id");
				ArrayList<PostDTO.PostItemDTO> photoList = new ArrayList<>();
				ArrayList<PostDTO.PostItemDTO> videoList = new ArrayList<>();
				ArrayList<PostDTO.DiaryDTO> diaryList = new ArrayList<>();
				MemberDTO member = new MemberDTO();
				// 포토리스트 저장하는 곳
				try {
					photoList = pd.photoList(mNum, true);
					videoList = pd.videoList(mNum, true);
					diaryList = pd.getdiarylist(mNum);
					member = md.getMemberInfo(mNum);
				} catch (SQLException e) {
					e.printStackTrace();
					// 처리 중 예외 발생 시 예외 처리
				}
				request.setAttribute("mNum", mNum);
				request.setAttribute("mUserId", mUserId);
				request.setAttribute("member", member);
				request.setAttribute("photoList", photoList);
				request.setAttribute("videoList", videoList);
				request.setAttribute("diaryList", diaryList);

				nextPage = "/jsp/main.jsp";
				break;
			}
			case "/other.do": {
				PostDAO pd = new PostDAO();
				MemberDAO md = new MemberDAO();
				FollowDAO fd = new FollowDAO();
				String myMNum = (String) session.getAttribute("mNum");
				String othMNum = request.getParameter("mNum");
				String mUserId = null;
				ArrayList<PostDTO.PostItemDTO> photoList = new ArrayList<>();
				ArrayList<PostDTO.PostItemDTO> videoList = new ArrayList<>();
				ArrayList<PostDTO.DiaryDTO> diaryList = new ArrayList<>();
				MemberDTO member = new MemberDTO();
				// 포토리스트 저장하는 곳
				try {
					Boolean check = fd.checkFollow(myMNum, othMNum);
					System.out.println(check);

					photoList = pd.photoList(othMNum, check);
					videoList = pd.videoList(othMNum, check);
					diaryList = pd.getdiarylist(othMNum);
					mUserId = pd.getUID(othMNum);
					member = md.getMemberInfo(othMNum);
				} catch (SQLException e) {
					e.printStackTrace();
					// 처리 중 예외 발생 시 예외 처리
				}
				request.setAttribute("mNum", othMNum);
				request.setAttribute("mUserId", mUserId);
				request.setAttribute("photoList", photoList);
				request.setAttribute("videoList", videoList);
				request.setAttribute("diaryList", diaryList);
				request.setAttribute("member", member);

				nextPage = "/jsp/main.jsp";
				break;
			}
			case "/imageUpload.do": {
				PostDAO pd = new PostDAO();

				String mNum = (String) session.getAttribute("mNum");

				String exposure = request.getParameter("exposure");
				String category = "photo";
				String originalFileName = request.getParameter("originalFileName");
				String newFileName = request.getParameter("newFileName");
				String content = request.getParameter("content");

				if (pd.insertPhoto(mNum, exposure, category, originalFileName, newFileName, content) == true) {
					nextPage = "/post/my.do";
					System.out.println("성공");
				} else {
					System.out.println("실패");
					nextPage = "/test.jsp";
				}
				break;
			}
			case "/videoUpload.do": {
				PostDAO pd = new PostDAO();

				String mNum = (String) session.getAttribute("mNum");

				String exposure = request.getParameter("exposure");
				String category = "video";
				String originalFileName = request.getParameter("originalFileName");
				String newFileName = request.getParameter("newFileName");
				String content = request.getParameter("content");

				if (pd.insertPhoto(mNum, exposure, category, originalFileName, newFileName, content) == true) {
					nextPage = "/post/my.do";
					System.out.println("성공");
				} else {
					System.out.println("실패");
					nextPage = "/test.jsp";
				}
				break;
			}
			case "/postDelete.do": {
				PostDAO pd = new PostDAO();
				String pNum = request.getParameter("pNum");
				
				if(pd.deletePost(pNum)) {
					nextPage = "/post/my.do";
				}
				break;
			}
			case "/replyShow.do": {
				PostDAO pd = new PostDAO();
				String pNum = request.getParameter("pNum");

				ArrayList<PostDTO.PostReplyDTO> comments = new ArrayList<>();
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
				String mNum = (String) session.getAttribute("mNum");
				String userId = (String) session.getAttribute("id");

				if (pd.createComment(pNum, mNum, commentText)) {
					// JSON 형태의 응답 데이터 생성
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("mNum", mNum);
					jsonResponse.put("userId", userId);
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
			case "/diaryDelete.do": {
				String dnum = request.getParameter("dnum");

				if (dnum != null) {

					try {
						PostDAO pd = new PostDAO();
						int result = pd.delete(dnum);

						if (result > 0) {
							response.sendRedirect(request.getContextPath() + "/post/*");

						} else {
							System.out.print("-1");

						}

					} catch (SQLException e) {
						e.printStackTrace();
						response.getWriter().print("다이어리 삭제 실패");
					}
				}
				break;
			}
			case "/diaryWrite.do": {

				// 기본 동작: 새 일기 작성
				String mNum = (String) session.getAttribute("mNum");
				String title = request.getParameter("title");
				String content = request.getParameter("content");

				PostDAO pd = new PostDAO();
				int result = pd.write(mNum, title, content);

				if (result > 0) {
					response.sendRedirect(request.getContextPath() + "/post/*");

				} else {
					System.out.print("-1");

				}
				break;
			}

			case "/logout.do": {
				if (session != null) { // 세션을 무효화합니다.
					session.invalidate();
					response.sendRedirect(request.getContextPath() + "/member/");
				}
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
