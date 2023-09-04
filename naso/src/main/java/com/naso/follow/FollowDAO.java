package com.naso.follow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FollowDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private static DataSource ds;

	public FollowDAO() {
		try {
			Context ctx = new InitialContext(); // 톰캣에 저장되어 있는 context 정보 조회를 위한 설정
			Context env = (Context) ctx.lookup("java:/comp/env"); // context에 저장되어 있는 환경(설정) 정보 조회용
			ds = (DataSource) env.lookup("jdbc/oracle"); // connection pool 정보 조회
			con = ds.getConnection(); // con 변수 초기화
		} catch (Exception ex) {
			ex.printStackTrace(); // console 창에 로그(메시지) 출력
		}
	}

	public boolean checkFollowing(String myMNum, String othMNum) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection(); // con 변수 초기화

			String query = "SELECT COUNT(*) FROM FOLLOW WHERE F_TO = ? AND F_FROM = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, myMNum);
			pstmt.setString(2, othMNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);

				return count > 0;
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

		return false;
	}
	public boolean checkFollow(String myMNum, String othMNum) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection(); // con 변수 초기화

			String query = "SELECT COUNT(*) FROM FOLLOW WHERE F_FROM = ? AND F_TO = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, myMNum);
			pstmt.setString(2, othMNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);

				return count > 0;
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

		return false;
	}

	public boolean follow(String myMNum, String otherMNum) throws SQLException {
		PreparedStatement pstmt = null;
		boolean success = false;

		try {
			con = ds.getConnection(); // con 변수 초기화

			String query = "INSERT INTO FOLLOW (F_FROM, F_TO) VALUES (?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, myMNum);
			pstmt.setString(2, otherMNum);

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

	public boolean nufollow(String myMNum, String otherMNum) throws SQLException {
		PreparedStatement pstmt = null;
		boolean success = false;

		try {
			con = ds.getConnection(); // con 변수 초기화

			String query = "DELETE FROM FOLLOW WHERE F_FROM = ? AND F_TO = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, myMNum);
			pstmt.setString(2, otherMNum);

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
	public int followerCount(String mNum) {
		int followerCount = 0;
		try {
			con = ds.getConnection();

			String query = " SELECT COUNT(*) AS FOLLOWER FROM FOLLOW F WHERE F.F_TO = ? ";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mNum);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				followerCount = rs.getInt("FOLLOWER");
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return followerCount;
	}
}
