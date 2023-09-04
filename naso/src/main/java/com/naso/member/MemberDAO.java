package com.naso.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private static DataSource ds;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext(); // 톰캣에 저장되어 있는 context 정보 조회를 위한 설정
			Context env = (Context) ctx.lookup("java:/comp/env"); // context에 저장되어 있는 환경(설정) 정보 조회용
			ds = (DataSource) env.lookup("jdbc/oracle"); // connection pool 정보 조회
			con = ds.getConnection(); // con 변수 초기화
		} catch (Exception ex) {
			ex.printStackTrace(); // console 창에 로그(메시지) 출력
		}
	}

	// 로그인
	public boolean authenticateUser(String M_USER_ID, String M_PW) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT COUNT(*) FROM member WHERE M_USER_ID = ? AND M_PW = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, M_USER_ID);
			pstmt.setString(2, M_PW);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);

				return count > 0; // 로그인 성공 여부를 반환합니다.
			}
		} catch (Exception e) {
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

	// 회원 번호 출력
	public String getUserNum(String M_USER_ID) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			System.out.println("실행");
			String query = "SELECT M_NUM FROM member WHERE M_USER_ID = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, M_USER_ID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String m_num = rs.getString("M_NUM");
				System.out.println(m_num);
				rs.close();
				pstmt.close();
				con.close();
				return m_num;
			}
		} catch (Exception e) {
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
		return "0";
	}

	// 회원가입
	public boolean join(MemberDTO member) throws SQLException {
		PreparedStatement pstmt = null;
		boolean success = false;

		try {
			con = ds.getConnection();
			// SQL 쿼리 준비
			String query = "INSERT INTO MEMBER (M_USER_ID, M_PW, M_GENDER, M_NAME) VALUES (?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getmUserId());
			pstmt.setString(2, member.getmPw());
			pstmt.setString(3, member.getmGender());
			pstmt.setString(4, member.getmName());
			// SQL 쿼리에 파라미터 값 설정

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

	public MemberDTO getMemberInfo(String mNum) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO mdt = new MemberDTO();
		try {
			con = ds.getConnection();
			String query = "SELECT * FROM Member WHERE M_NUM = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mNum);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				mdt.setmNum(rs.getString("M_NUM"));
				mdt.setmUserId(rs.getString("M_USER_ID"));
				mdt.setmPw(rs.getString("M_PW"));
				mdt.setmGender(rs.getString("M_GENDER"));
				mdt.setmName(rs.getString("M_NAME"));
				mdt.setmDatetimeCreated(rs.getTimestamp("M_DATETIME_CREATED"));
				mdt.setmEmail(rs.getString("M_EMAIL"));
				mdt.setmDateBirth(rs.getDate("M_DATE_BIRTH"));
				mdt.setmPhone(rs.getString("M_PHONE"));
				mdt.setmProfileImage(rs.getString("M_PROFILE_IMAGE"));
				mdt.setmInstagram(rs.getString("M_INSTAGRAM"));
				mdt.setmTiktok(rs.getString("M_TIKTOK"));
				mdt.setmYoutube(rs.getString("M_YOUTUBE"));
				mdt.setmBlog(rs.getString("M_BLOG"));
				mdt.setmKakao(rs.getString("M_AFRICA"));
				mdt.setmAfrica(rs.getString("M_NAVER"));
				
				return mdt;
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

		return mdt;
	}
	public boolean profileUpdate(MemberDTO member) throws SQLException {
		PreparedStatement pstmt = null;
		boolean success = false;

		try {
			
			con = ds.getConnection();
			// SQL 쿼리 준비
			String query = "UPDATE Member SET M_GENDER = ?, M_PW = ?, M_NAME = ?, M_EMAIL = ?, M_PHONE = ?, M_DATE_BIRTH = ?, M_INSTAGRAM = ?, M_KAKAO = ?, M_TIKTOK = ?, M_YOUTUBE = ?, M_AFRICA = ?, M_NAVER = ?, M_BLOG = ? WHERE M_NUM = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getmGender());
			pstmt.setString(2, member.getmPw());
			pstmt.setString(3, member.getmName());
			pstmt.setString(4, member.getmEmail());
			pstmt.setString(5, member.getmPhone());
			pstmt.setDate(6, member.getmDateBirth());
			pstmt.setString(7, member.getmInstagram());
			pstmt.setString(8, member.getmKakao());
			pstmt.setString(9, member.getmTiktok());
			pstmt.setString(10, member.getmYoutube());
			pstmt.setString(11, member.getmAfrica());
			pstmt.setString(12, member.getmNaver());
			pstmt.setString(13, member.getmBlog());
			pstmt.setString(14, member.getmNum());
			
			// SQL 쿼리에 파라미터 값 설정
			int rowsAffected = pstmt.executeUpdate();
			System.out.println(rowsAffected);
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
