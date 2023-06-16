package com.naso.search;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/schCtrl")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String keyword = request.getParameter("keyword"); // 사용자가 검색창에 입력한 키워드
//		String kw = keyword.trim().toUpperCase();

		SearchDAO dao = new SearchDAO();
		ArrayList<SearchDTO> listAll = dao.searchUser(); // DB에서 가져온 회원 전체 리스트
		ArrayList<SearchDTO> listResult = new ArrayList<SearchDTO>(); // 검색결과와 일치하는 계정 목록이 저장될 리스트
		
		if (keyword.trim().length()!=0) {			// 검색한 키워드가 공백이 아니면 listResult를 키워드와 관련 있는 계정들로 채움.
			for (SearchDTO account : listAll) {
				if (account.getName().contains(keyword.trim()) || account.getUserId().toUpperCase().contains(keyword.toUpperCase())) {
					listResult.add(account);
				}
			}
		}

		// listResult를 search.jsp로 넘김. listResult가 비어있으면(계정과 비슷한 키워드를 입력하지 않은 경우 or 공백 입력한 경우) search.jsp에서 <c:when test="${empty searchResult }">가 실행됨.
		request.setAttribute("searchResult", listResult);
		RequestDispatcher rd = request.getRequestDispatcher("/html/search.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}