package com.naso.member;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDTO memberDTO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
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

				if (md.authenticateUser(id, pw) == true) {
					String mNum = md.getUserNum(id);
					session.setAttribute("mNum", mNum);
					session.setAttribute("id", id);
					session.setAttribute("loggedIn", true);
					response.sendRedirect(request.getContextPath() + "/post/my.do");

				} else {
					nextPage = "/jsp/login.jsp";
				}
				break;
			}
			case "/join.do": {
				
				MemberDAO md = new MemberDAO();
				String memberId = request.getParameter("memberId");
				String memberPwd = request.getParameter("memberPwd");
				String memberName = request.getParameter("memberName");
				String memberGender = request.getParameter("memberGender");

				if (md.getUserNum(memberId).equals("0")) {
					
					MemberDTO mdt = new MemberDTO();
					mdt.setmUserId(memberId);
					mdt.setmPw(memberPwd);
					mdt.setmName(memberName);
					mdt.setmGender(memberGender);
				
					if(md.join(mdt)) {
						
						String mNum = md.getUserNum(memberId);
						session.setAttribute("mNum", mNum);
						session.setAttribute("id", memberId);
						session.setAttribute("loggedIn", true);
						response.sendRedirect(request.getContextPath() + "/post/my.do");
					}else {
						nextPage = "/jsp/login.jsp";
					}
				}

				break;
			}
			case "/editProfile.do": {
				MemberDAO md = new MemberDAO();
				String mNum = (String) session.getAttribute("mNum");

				MemberDTO mdtInfo = new MemberDTO();
				mdtInfo = md.getMemberInfo(mNum);
				
				request.setAttribute("userList", mdtInfo);
				nextPage = "/jsp/edit_profile.jsp";
				
				break;
			}
			case "/profileUpload.do":{
				MemberDTO mdt = new MemberDTO();
				MemberDAO md = new MemberDAO();
				String mNum = (String) session.getAttribute("mNum");
				System.out.println(mNum);
				mdt.setmNum(mNum);
				mdt.setmGender(request.getParameter("gender"));
				mdt.setmPw(request.getParameter("memberPwd"));
				mdt.setmName(request.getParameter("memberName"));
				mdt.setmEmail(request.getParameter("memberEmail"));
				mdt.setmPhone(request.getParameter("memberPhone"));
				System.out.println("member1");
				String dateOfBirthParam = request.getParameter("dateOfBirth");
				if (dateOfBirthParam != null && !dateOfBirthParam.isEmpty()) {
				    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				    try {
				        java.util.Date utilDate = dateFormat.parse(dateOfBirthParam);
				        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				        mdt.setmDateBirth(sqlDate);
				    } catch (ParseException e) {
				        e.printStackTrace();
				        // 예외 처리 방법에 맞게 처리
				    }
				}
				
				
				mdt.setmInstagram(request.getParameter("memberInstagram"));
				mdt.setmKakao(request.getParameter("memberKakao"));
				mdt.setmTiktok(request.getParameter("memberTiktok"));
				mdt.setmYoutube(request.getParameter("memberYoutube"));
				mdt.setmAfrica(request.getParameter("memberAfrica"));
				mdt.setmNaver(request.getParameter("memberNaver"));
				mdt.setmBlog(request.getParameter("memberBlog"));
				
				
				if(md.profileUpdate(mdt)) {
					
					nextPage = "/prfCtrl?mNum="+mNum;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
