package com.naso.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PostDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private static DataSource ds;

	public PostDAO() {
		try {
			Context ctx = new InitialContext(); // 톰캣에 저장되어 있는 context 정보 조회를 위한 설정
			Context env = (Context) ctx.lookup("java:/comp/env"); // context에 저장되어 있는 환경(설정) 정보 조회용
			ds = (DataSource) env.lookup("jdbc/oracle"); // connection pool 정보 조회
			con = ds.getConnection(); // con 변수 초기화
		} catch (Exception ex) {
			ex.printStackTrace(); // console 창에 로그(메시지) 출력
		}
	}

	// 사진 리스트 받아오기
	public ArrayList<PostDTO.PostItemDTO> photoList(String mNum, boolean check) throws SQLException {
		ArrayList<PostDTO.PostItemDTO> list = new ArrayList<>();
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String query = "";
			if (check)
				query = "SELECT MEMBER.M_USER_ID ,POST.* FROM MEMBER INNER join post on MEMBER.M_NUM = POST.M_NUM WHERE MEMBER.M_NUM = ? AND P_CATEGORY = 'photo' ORDER BY POST.P_DATETIME_CREATED DESC";
			else {
				query = "SELECT MEMBER.M_USER_ID ,POST.* FROM MEMBER INNER join post on MEMBER.M_NUM = POST.M_NUM WHERE MEMBER.M_NUM = ? AND P_CATEGORY = 'photo' AND P_EXPOSE = '1' ORDER BY POST.P_DATETIME_CREATED DESC";
			}
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO postDTO = new PostDTO();
				PostDTO.PostItemDTO post = postDTO.new PostItemDTO();

				post.setUserId(rs.getString("M_USER_ID"));
				post.setPNum(rs.getString("P_NUM"));
				post.setMNum(rs.getString("M_NUM"));
				post.setExpose(rs.getString("P_EXPOSE"));
				post.setCategory(rs.getString("P_CATEGORY"));
				post.setDatetimeCreated(rs.getTimestamp("P_DATETIME_CREATED"));
				post.setMediaOriginal(rs.getString("P_FILENAME_ORIGINAL"));
				post.setMediaS3(rs.getString("P_FILENAME_S3"));
				post.setContent(rs.getString("P_CONTENT"));
				post.setViewCount(rs.getInt("P_VIEW_COUNT"));
				post.setLikeCount(rs.getInt("P_LIKE_COUNT"));

				list.add(post);

			}

		} finally {
			// 사용한 자원 해제
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return list;
	}

	// 동영상 리스트 받아오기
	public ArrayList<PostDTO.PostItemDTO> videoList(String mNum, boolean check) throws SQLException {
		ArrayList<PostDTO.PostItemDTO> list = new ArrayList<>();
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String query = "";
			if (check)
				query = "SELECT MEMBER.M_USER_ID ,POST.* FROM MEMBER INNER join post on MEMBER.M_NUM = POST.M_NUM WHERE MEMBER.M_NUM = ? AND P_CATEGORY = 'video' ORDER BY POST.P_DATETIME_CREATED DESC";
			else {
				query = "SELECT MEMBER.M_USER_ID ,POST.* FROM MEMBER INNER join post on MEMBER.M_NUM = POST.M_NUM WHERE MEMBER.M_NUM = ? AND P_CATEGORY = 'video' AND P_EXPOSE = '1' ORDER BY POST.P_DATETIME_CREATED DESC";
			}
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO postDTO = new PostDTO();
				PostDTO.PostItemDTO post = postDTO.new PostItemDTO();

				post.setUserId(rs.getString("M_USER_ID"));
				post.setPNum(rs.getString("P_NUM"));
				post.setMNum(rs.getString("M_NUM"));
				post.setExpose(rs.getString("P_EXPOSE"));
				post.setCategory(rs.getString("P_CATEGORY"));
				post.setDatetimeCreated(rs.getTimestamp("P_DATETIME_CREATED"));
				post.setMediaOriginal(rs.getString("P_FILENAME_ORIGINAL"));
				post.setMediaS3(rs.getString("P_FILENAME_S3"));
				post.setContent(rs.getString("P_CONTENT"));
				post.setViewCount(rs.getInt("P_VIEW_COUNT"));
				post.setLikeCount(rs.getInt("P_LIKE_COUNT"));

				list.add(post);

			}

		} finally {
			// 사용한 자원 해제
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return list;
	}

	// 사진과 동영상 가져오기
	public ArrayList<PostDTO.PostItemDTO> List(String mNum, boolean check) throws SQLException {
		ArrayList<PostDTO.PostItemDTO> list = new ArrayList<>();
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String query = "";
			if (check)
				query = "SELECT MEMBER.M_USER_ID ,POST.* FROM MEMBER INNER join post on MEMBER.M_NUM = POST.M_NUM WHERE MEMBER.M_NUM = ? ORDER BY POST.P_DATETIME_CREATED DESC";
			else {
				query = "SELECT MEMBER.M_USER_ID ,POST.* FROM MEMBER INNER join post on MEMBER.M_NUM = POST.M_NUM WHERE MEMBER.M_NUM = ? AND P_EXPOSE = '1' ORDER BY POST.P_DATETIME_CREATED DESC";
			}
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO postDTO = new PostDTO();
				PostDTO.PostItemDTO post = postDTO.new PostItemDTO();

				post.setUserId(rs.getString("M_USER_ID"));
				post.setPNum(rs.getString("P_NUM"));
				post.setMNum(rs.getString("M_NUM"));
				post.setExpose(rs.getString("P_EXPOSE"));
				post.setCategory(rs.getString("P_CATEGORY"));
				post.setDatetimeCreated(rs.getTimestamp("P_DATETIME_CREATED"));
				post.setMediaOriginal(rs.getString("P_FILENAME_ORIGINAL"));
				post.setMediaS3(rs.getString("P_FILENAME_S3"));
				post.setContent(rs.getString("P_CONTENT"));
				post.setViewCount(rs.getInt("P_VIEW_COUNT"));
				post.setLikeCount(rs.getInt("P_LIKE_COUNT"));

				list.add(post);

			}

		} finally {
			// 사용한 자원 해제
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return list;
	}

	// 정보 추가
	public boolean insertPhoto(String mNum, String exposure, String category, String originalFileName,
			String newFileName, String content) throws SQLException {
		boolean success = false;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String query = "INSERT INTO POST (P_NUM, M_NUM, P_EXPOSE, P_CATEGORY, P_DATETIME_CREATED, P_FILENAME_ORIGINAL, P_FILENAME_S3, P_CONTENT) "
					+ "VALUES (seq_p_num.NEXTVAL, ?, ?, ?, SYSDATE, ?, ?, ?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, mNum);
			pstmt.setString(2, exposure);
			pstmt.setString(3, category);
			pstmt.setString(4, originalFileName);
			pstmt.setString(5, newFileName);
			pstmt.setString(6, content);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 사용한 자원 해제
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return success;
	}

	public boolean deletePost(String pNum) throws SQLException {
		boolean success = false;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			String query = "DELETE FROM POST WHERE P_NUM = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pNum); // 게시물
			
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 사용한 자원 해제
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	// 댓글 리스트 출력
	public ArrayList<PostDTO.PostReplyDTO> getCommentsByPNum(String pNum) throws SQLException {
		ArrayList<PostDTO.PostReplyDTO> list = new ArrayList<>();
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			String query = "SELECT MEMBER.M_USER_ID ,reply.* FROM MEMBER INNER JOIN reply ON MEMBER.M_NUM = reply.M_NUM  WHERE P_NUM = ? ORDER BY R_DATETIME_CREATED DESC";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO postDTO = new PostDTO();
				PostDTO.PostReplyDTO reply = postDTO.new PostReplyDTO();

				reply.setMNum(rs.getString("M_NUM"));
				reply.setMUserId(rs.getString("M_USER_ID"));
				reply.setRContent(rs.getString("R_CONTENT"));
				reply.setRDatetimeCreated(rs.getTimestamp("R_DATETIME_CREATED"));

				list.add(reply);
			}
		} finally {
			// 사용한 자원 해제
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		return list;
	}

	// 댓글 생성
	public boolean createComment(String pNum, String mNum, String commentText) throws SQLException {
		// 데이터베이스 연결을 위한 코드
		PreparedStatement pstmt = null;
		boolean success = false;

		try {
			con = ds.getConnection();
			// SQL 쿼리 준비
			String query = "INSERT INTO REPLY (P_NUM, M_NUM, R_CONTENT) VALUES (?, ?, ?)";
			pstmt = con.prepareStatement(query);

			// SQL 쿼리에 파라미터 값 설정
			pstmt.setString(1, pNum);
			pstmt.setString(2, mNum);
			pstmt.setString(3, commentText);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 사용한 자원 해제
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return success;
	}

	// 아이디 가져오기
	public String getUID(String mNum) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String uid = null;
			// SQL 쿼리 준비
			String query = "SELECT M_USER_ID FROM MEMBER WHERE M_NUM = ?";
			pstmt = con.prepareStatement(query);

			// SQL 쿼리에 파라미터 값 설정
			pstmt.setString(1, mNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				uid = rs.getString("M_USER_ID");
				return uid;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 사용한 자원 해제
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return "false";
	}

	/* --------------------------------다이어리-------------------------------- */
	// 날짜 반환
	public String getDate() {
		String SQL = "SELECT d_datetime_created from diary where d_num=3";

		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL); // SQL 실행
			ResultSet rs = pstmt.executeQuery(); // 실제 실행했을 때 가지고 오는 결과

			if (rs.next()) {
				return rs.getString(1); // 현재 날짜를 반환
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; // 데이터베이스 오류
	}

	public int getnext() {
		String SQL = "SELECT D_NUM FROM DIARY ORDER BY D_NUM DESC";

		try {
			con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL); // SQL 실행
			ResultSet rs = pstmt.executeQuery(); // 실제 실행했을 때 가지고 오는 결과
			if (rs.next()) {
				pstmt.close();

				con.close();
				return Integer.parseInt(rs.getString(1)) + 1;
			}
			return 1; // 첫 번째 게시물의 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

	public int write(String mnum, String title, String content) {
		String SQL = "INSERT INTO DIARY (M_NUM, D_TITLE, D_CONTENT) VALUES(?, ?, ?)";
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, mnum);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			int result = pstmt.executeUpdate();
			pstmt.close();

			con.close();
			return result;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1; // 데이터베이스 오류
	}

	public int delete(String dnum) throws SQLException {

		String query = "DELETE FROM diary WHERE D_NUM = ?";
		con = ds.getConnection();
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dnum); // 게시물
			int result = pstmt.executeUpdate();
			pstmt.close();

			con.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public ArrayList<PostDTO.DiaryDTO> getdiarylist(String MNUM) throws SQLException {
		ArrayList<PostDTO.DiaryDTO> list = new ArrayList<>();
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String query = "";

			query = "select * from diary where M_NUM = ? order by d_datetime_created desc";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, MNUM);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO postDTO = new PostDTO();
				PostDTO.DiaryDTO diaryDTO = postDTO.new DiaryDTO();

				diaryDTO.setDNum(rs.getString("D_NUM"));
				diaryDTO.setMNum(rs.getString("M_NUM"));
				diaryDTO.setDatetimeCreated(rs.getTimestamp("D_DATETIME_CREATED"));
				diaryDTO.setTitle(rs.getString("D_TITLE"));
				diaryDTO.setContent(rs.getString("D_CONTENT"));

				list.add(diaryDTO);

			}

		} finally {
			rs.close();

			pstmt.close();

			con.close();

		}

		return list;

	}
}
