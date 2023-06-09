package com.telusco;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		int i = Integer.parseInt(req.getParameter("num1"));
		int k = Integer.parseInt(req.getParameter("num2"));

		int sum = i + k;
		System.out.println(sum);

		PrintWriter out = res.getWriter();
		out.println("result(add): " + sum);

		
		req.setAttribute("sunmo", sum);

		RequestDispatcher rd = req.getRequestDispatcher("sq");
		rd.forward(req, res);

	}

}
