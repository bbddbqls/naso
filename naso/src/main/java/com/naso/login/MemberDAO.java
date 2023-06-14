package com.naso.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	 //로그인
	    public boolean authenticateUser(String M_USER_ID, String M_PW) {
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
	                
	                rs.close();
	                pstmt.close();
	                con.close();
	                return count > 0; // 로그인 성공 여부를 반환합니다.
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 

	        return false;
	    }
	}


