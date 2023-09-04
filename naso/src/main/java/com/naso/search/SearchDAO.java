package com.naso.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SearchDAO {
   private Connection con;
   private PreparedStatement pstmt;
   private static DataSource ds;

   public SearchDAO() {
      try {
         Context ctx = new InitialContext(); // 톰캣에 저장되어 있는 context 정보 조회를 위한 설정
         Context env = (Context) ctx.lookup("java:/comp/env"); // context에 저장되어 있는 환경(설정) 정보 조회용
         ds = (DataSource) env.lookup("jdbc/oracle"); // connection pool 정보 조회
      } catch (Exception ex) {
         ex.printStackTrace(); // console 창에 로그(메시지) 출력
      }
   }
   
   // DB의 멤버 테이블에서 전체 회원 목록을 가져와서 DTO에 아이디와 이름을 저장하는 메서드
   public ArrayList<SearchDTO> searchUser() {
      ArrayList<SearchDTO> list = new ArrayList<>();
      try {
         con = ds.getConnection();

         String query = "select * from member";

         pstmt = con.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery();

         while (rs.next()) {
        	String mNum = rs.getString("M_NUM");
            String id = rs.getString("m_user_id");
            String name = rs.getString("m_name");

            SearchDTO dto = new SearchDTO();

            dto.setMNum(mNum);
            dto.setName(name);
            dto.setUserId(id);
            list.add(dto);
         }
         rs.close();
         pstmt.close();
         con.close();

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return list;
   }   
}