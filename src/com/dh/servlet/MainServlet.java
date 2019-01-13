package com.dh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ������ת��Ϊ����
		request.setCharacterEncoding("GBK");
		// �������Ϊ����
		response.setContentType("text/html;charset=GBK");
		// ��ȡ�����
		PrintWriter out = response.getWriter();
		// ��ȡSession
		HttpSession session = request.getSession();
		// ��Session����ȡ�û���
		String username = (String) session.getAttribute("username");
		// ��Session����ȡ�û���¼ʱ��
		String logtime = (String) session.getAttribute("logtime");
		// ���
		out.println("��¼����" + username + "&nbsp;&nbsp;|&nbsp;&nbsp;��¼ʱ�䣺"
				+ logtime);
		out.println("��ӭ" + username);
	}

}
