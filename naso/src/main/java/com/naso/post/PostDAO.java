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
	public ArrayList<PostDTO> photoList(String userId) throws SQLException {
		ArrayList<PostDTO> list = new ArrayList<>();
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			String query = "SELECT * FROM POST WHERE P_USER_ID = ? AND P_CATEGORY = 'photo' ORDER BY P_DATETIME_CREATED DESC";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO post = new PostDTO();

				post.setPNum(rs.getString("P_NUM"));
				post.setUserId(rs.getString("P_USER_ID"));
				post.setExpose(rs.getString("P_EXPOSE"));
				post.setCategory(rs.getString("P_CATEGORY"));
				post.setDatetimeCreated(rs.getTimestamp("P_DATETIME_CREATED"));
				post.setTitle(rs.getString("P_TITLE"));
				post.setMediaOriginal(rs.getString("P_MEDIA_ORIGINAL"));
				post.setMediaS3(rs.getString("P_MEDIA_S3"));
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
	public ArrayList<PostDTO> videoList(String userId) throws SQLException {
		ArrayList<PostDTO> list = new ArrayList<>();
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			String query = "SELECT * FROM POST WHERE P_USER_ID = ? AND P_CATEGORY = 'video' ORDER BY P_DATETIME_CREATED DESC";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO post = new PostDTO();

				post.setPNum(rs.getString("P_NUM"));
				post.setUserId(rs.getString("P_USER_ID"));
				post.setExpose(rs.getString("P_EXPOSE"));
				post.setCategory(rs.getString("P_CATEGORY"));
				post.setDatetimeCreated(rs.getTimestamp("P_DATETIME_CREATED"));
				post.setTitle(rs.getString("P_TITLE"));
				post.setMediaOriginal(rs.getString("P_MEDIA_ORIGINAL"));
				post.setMediaS3(rs.getString("P_MEDIA_S3"));
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

	// 사진 정보 추가
	public boolean insertPhoto(String userId, String exposure, String category, String originalFileName,
			String newFileName, String content) {
		boolean success = false;
		PreparedStatement pstmt = null;

		try {
			String query = "INSERT INTO POST (P_NUM, P_USER_ID, P_EXPOSE, P_CATEGORY, P_DATETIME_CREATED, P_MEDIA_ORIGINAL, P_MEDIA_S3, P_CONTENT) "
					+ "VALUES (seq_p_num.NEXTVAL, ?, ?, ?, SYSDATE, ?, ?, ?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, userId);
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
	//댓글 리스트 출력
	public ArrayList<ReplyDTO> getCommentsByPNum(String pNum) throws SQLException {
		ArrayList<ReplyDTO> list = new ArrayList<>();
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			String query = "SELECT *FROM reply WHERE P_NUM = ? ORDER BY R_DATETIME_CREATED DESC";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReplyDTO reply = new ReplyDTO();

				reply.setRUserId(rs.getString("R_USER_ID"));
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
	public boolean createComment(String pNum, String userId, String commentText) {
		// 데이터베이스 연결을 위한 코드
		PreparedStatement pstmt = null;
		boolean success = false;

		try {

			// SQL 쿼리 준비
			String query = "INSERT INTO REPLY (P_NUM, R_USER_ID, R_CONTENT) VALUES (?, ?, ?)";
			pstmt = con.prepareStatement(query);

			// SQL 쿼리에 파라미터 값 설정
			pstmt.setString(1, pNum);
			pstmt.setString(2, userId);
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
}
