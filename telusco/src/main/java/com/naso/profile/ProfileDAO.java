package com.naso.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	public ArrayList<ProfileDTO> searchUser(String userId) {
		ArrayList<ProfileDTO> targetProfile = new ArrayList<>();
		try {
			con = ds.getConnection();

			String query = """
					SELECT M.*, P.* FROM MEMBER M
					LEFT OUTER JOIN POST P
					ON M.M_USER_ID = P.P_USER_ID
					WHERE M.M_USER_ID = ?
					ORDER BY M.M_USER_ID
					""";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("M_NAME");
				String id = rs.getString("M_USER_ID");
				String title = rs.getString("P_TITLE");
				String content = rs.getString("P_CONTENT");
				String category = rs.getString("P_CATEGORY");
				int postNum = rs.getInt("P_NUM");
//				String expose = rs.getString("P_EXPOSE");

				ProfileDTO dto = new ProfileDTO();

				dto.setName(name);
				dto.setUserId(id);
				dto.setTitle(title);
				dto.setContent(content);
				dto.setCategory(category);
				dto.setPostNum(postNum);
//				dto.setExpose(expose);
				
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
}