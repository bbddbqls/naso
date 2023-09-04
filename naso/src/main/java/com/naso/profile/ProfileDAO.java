package com.naso.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.naso.post.PostDTO;
import com.naso.post.PostDTO.PostItemDTO;
import com.naso.search.SearchDTO;

public class ProfileDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private static DataSource ds;

	public ProfileDAO() {
		try {
			Context ctx = new InitialContext(); // 톰캣에 저장되어 있는 context 정보 조회를 위한 설정
			Context env = (Context) ctx.lookup("java:/comp/env"); // context에 저장되어 있는 환경(설정) 정보 조회용
			ds = (DataSource) env.lookup("jdbc/oracle"); // connection pool 정보 조회
		} catch (Exception ex) {
			ex.printStackTrace(); // console 창에 로그(메시지) 출력
		}
	}

	// 선택한 계정의 정보를 DB에서 가져오는 메서드
	public ArrayList<ProfileDTO> searchUser(String mNum) {
		ArrayList<ProfileDTO> targetProfile = new ArrayList<>();
		try {
			con = ds.getConnection();

			/*
			 * String query = """ SELECT * FROM MEMBER M LEFT OUTER JOIN POST P ON
			 * M.M_USER_ID = P.P_USER_ID WHERE M.M_USER_ID = ? ORDER BY M.M_USER_ID """;
			 */
			String query = "SELECT * FROM MEMBER WHERE M_NUM = ?";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mNum);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String num = rs.getString("M_NUM");
				String name = rs.getString("M_NAME");
				String id = rs.getString("M_USER_ID");
				/* String content = rs.getString("P_CONTENT"); */
				/* String category = rs.getString("P_CATEGORY"); */
				/* int postNum = rs.getInt("P_NUM"); */

				ProfileDTO dto = new ProfileDTO();

				dto.setMNum(num);
				dto.setName(name);
				dto.setUserId(id);

				/*
				 * dto.setContent(content); dto.setCategory(category);
				 */
				/* dto.setPostNum(postNum); */

				targetProfile.add(dto);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return targetProfile;
	}

	// 선택한 계정의 게시물 개수를 DB에서 가져오는 메서드

	public int postCount(String nNum) {
		int postCount = 0;
		try {
			con = ds.getConnection();

			String query = "SELECT COUNT(*) AS COUNT FROM POST P WHERE P.M_NUM = ?";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, nNum);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				postCount = rs.getInt("COUNT");
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return postCount;
	}

	// 선택한 계정의 팔로잉 개수를 DB에서 가져오는 메서드
	public int followingCount(String mNum) {
		int followingCount = 0;
		try {
			con = ds.getConnection();

			String query = " SELECT COUNT(*) AS FOLLOWING FROM FOLLOW F WHERE F.F_FROM= ? ";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mNum);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				followingCount = rs.getInt("FOLLOWING");
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return followingCount;
	}

	// 선택한 계정의 팔로워 개수를 DB에서 가져오는 메서드
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
	

	
	public ArrayList<PostDTO.PostItemDTO> itemList(String mNum) throws SQLException {
		ArrayList<PostDTO.PostItemDTO> list = new ArrayList<>();
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String query = "SELECT MEMBER.M_USER_ID ,POST.* FROM MEMBER INNER join post on MEMBER.M_NUM = POST.M_NUM WHERE MEMBER.M_NUM = ? ORDER BY POST.P_DATETIME_CREATED DESC";
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

}