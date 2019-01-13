package com.dh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dh.db.DBOper;


public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistServlet() {
		super();
	}

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

		PrintWriter out = response.getWriter();// ��ȡ�����

		String username = request.getParameter("username");// ��ȡ���û���
		String userpass = request.getParameter("password");// ��ȡ���û�����

		ServletContext ctx = this.getServletContext();
		// ͨ��ServletContext���web.xml�����õĳ�ʼ������
		String server = ctx.getInitParameter("server");// ��ȡ��������ַ
		String dbname = ctx.getInitParameter("dbname");// ��ȡ���ݿ���
		String user = ctx.getInitParameter("user");// ��ȡ���ݿ��û���
		String pwd = ctx.getInitParameter("pwd");// ��ȡ���ݿ�����

		DBOper db = new DBOper();
		try {
			// �������ݿ�
			db.getConn(server, dbname, user, pwd);
			// ��userdetail����һ����¼
			String sql = "INSERT INTO userdetail(username,userpass,regtime) values(?,?,?)";
			//��ȡ��ǰע��ʱ��
			Date curTime=new Date();
			//��ʽ����ǰ����
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
			String regtime=dateFormat.format(curTime);
			// ִ�в�ѯ��username��userpass������������Ϊ����
			int rs = db.executeUpdate(sql, new String[] { username, userpass,regtime });
			if (rs > 0) {// ����ɹ�
				out.println("ע��ɹ�!���ס�����û���������");
				out.println("<br><a href='index.html'>���¼</a>");
			} else {// ����ʧ��
				out.println("ע��ʧ��!");
				out.println("<br><a href='regist.html'>����ע��</a>");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
