package com.dh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html;charset=GBK");
		PrintWriter out = resp.getWriter();
		String valcode = req.getParameter("Valcode");
		HttpSession session = req.getSession();
		String randomImageStr = (String) session.getAttribute("randomImageStr");
		if (valcode != null) {
			if (valcode.toLowerCase().equals(randomImageStr)) {
				
				out.println("验证码匹配！");
			} else {
				out.println("验证码不匹配");
				out.println("<a href='login.html'>重新登录</a>");
			}
		}

	}

}