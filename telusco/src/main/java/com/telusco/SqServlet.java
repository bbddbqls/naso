package com.telusco;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sq")
public class SqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int square = (int) req.getAttribute("sunmo");
		square *= square;
		PrintWriter out = res.getWriter();
		out.println("Result(sq): " + square);
		
	}

}
