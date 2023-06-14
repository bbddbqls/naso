/*
 * package com.naso.main;
 * 
 * import java.sql.Connection; import java.sql.PreparedStatement; import
 * java.sql.ResultSet; import java.sql.SQLException; import java.util.ArrayList;
 * 
 * import javax.naming.Context; import javax.naming.InitialContext; import
 * javax.sql.DataSource;
 * 
 * import com.naso.post.PostDTO;
 * 
 * public class MainDAO { private Connection con; private PreparedStatement
 * pstmt; private static DataSource ds;
 * 
 * public MainDAO() { try { Context ctx = new InitialContext(); // 톰캣에 저장되어 있는
 * context 정보 조회를 위한 설정 Context env = (Context) ctx.lookup("java:/comp/env"); //
 * context에 저장되어 있는 환경(설정) 정보 조회용 ds = (DataSource) env.lookup("jdbc/oracle");
 * // connection pool 정보 조회 con = ds.getConnection(); // con 변수 초기화 } catch
 * (Exception ex) { ex.printStackTrace(); // console 창에 로그(메시지) 출력 } }
 * 
 * 
 * public ArrayList<PostDTO> photoList(String userId) throws SQLException {
 * ArrayList<PostDTO> list = new ArrayList<>(); ResultSet rs = null;
 * 
 * try {
 * 
 * con = ds.getConnection();
 * 
 * String query = "SELECT * FROM POST WHERE P_USER_ID = ? AND P_CATEGORY = ?";
 * pstmt = con.prepareStatement(query); pstmt.setString(1, userId);
 * pstmt.setString(2, "photo");
 * 
 * rs = pstmt.executeQuery();
 * 
 * while (rs.next()) { PostDTO post = new PostDTO();
 * 
 * post.setPNum(rs.getString("P_NUM"));
 * post.setUserId(rs.getString("P_USER_ID"));
 * post.setExpose(rs.getString("P_EXPOSE"));
 * post.setCategory(rs.getString("P_CATEGORY"));
 * post.setDatetimeCreated(rs.getTimestamp("P_DATETIME_CREATED"));
 * post.setTitle(rs.getString("P_TITLE"));
 * post.setMediaOriginal(rs.getString("P_MEDIA_ORIGINAL"));
 * post.setMediaS3(rs.getString("P_MEDIA_S3"));
 * post.setContent(rs.getString("P_CONTENT"));
 * post.setViewCount(rs.getInt("P_VIEW_COUNT"));
 * post.setLikeCount(rs.getInt("P_LIKE_COUNT"));
 * 
 * list.add(post);
 * 
 * }
 * 
 * } finally { // 사용한 자원 해제 if (rs != null) { rs.close(); } if (pstmt != null) {
 * pstmt.close(); } if (con != null) { con.close(); } }
 * 
 * return list; } public ArrayList<PostDTO> vidioList(String userId) throws
 * SQLException { ArrayList<PostDTO> list = new ArrayList<>(); ResultSet rs =
 * null;
 * 
 * try { con = ds.getConnection();
 * 
 * String query = "SELECT * FROM POST WHERE P_USER_ID = ? AND P_CATEGORY = ?";
 * pstmt = con.prepareStatement(query); pstmt.setString(1, userId);
 * pstmt.setString(2, "vidio");
 * 
 * rs = pstmt.executeQuery();
 * 
 * while (rs.next()) { PostDTO post = new PostDTO();
 * 
 * post.setPNum(rs.getString("P_NUM"));
 * post.setUserId(rs.getString("P_USER_ID"));
 * post.setExpose(rs.getString("P_EXPOSE"));
 * post.setCategory(rs.getString("P_CATEGORY"));
 * post.setDatetimeCreated(rs.getTimestamp("P_DATETIME_CREATED"));
 * post.setTitle(rs.getString("P_TITLE"));
 * post.setMediaOriginal(rs.getString("P_MEDIA_ORIGINAL"));
 * post.setMediaS3(rs.getString("P_MEDIA_S3"));
 * post.setContent(rs.getString("P_CONTENT"));
 * post.setViewCount(rs.getInt("P_VIEW_COUNT"));
 * post.setLikeCount(rs.getInt("P_LIKE_COUNT"));
 * 
 * list.add(post); } } finally { // 사용한 자원 해제 if (rs != null) { rs.close(); } if
 * (pstmt != null) { pstmt.close(); } if (con != null) { con.close(); } }
 * 
 * return list; } }
 */