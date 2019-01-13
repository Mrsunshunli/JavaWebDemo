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

		// 将输入转换为中文
		request.setCharacterEncoding("GBK");
		// 设置输出为中文
		response.setContentType("text/html;charset=GBK");

		PrintWriter out = response.getWriter();// 获取输出流

		String username = request.getParameter("username");// 获取表单用户名
		String userpass = request.getParameter("password");// 获取表单用户密码

		ServletContext ctx = this.getServletContext();
		// 通过ServletContext获得web.xml中设置的初始化参数
		String server = ctx.getInitParameter("server");// 获取服务器地址
		String dbname = ctx.getInitParameter("dbname");// 获取数据库名
		String user = ctx.getInitParameter("user");// 获取数据库用户名
		String pwd = ctx.getInitParameter("pwd");// 获取数据库密码

		DBOper db = new DBOper();
		try {
			// 连接数据库
			db.getConn(server, dbname, user, pwd);
			// 向userdetail插入一条记录
			String sql = "INSERT INTO userdetail(username,userpass,regtime) values(?,?,?)";
			//获取当前注册时间
			Date curTime=new Date();
			//格式化当前日期
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
			String regtime=dateFormat.format(curTime);
			// 执行查询，username和userpass放入数组中作为参数
			int rs = db.executeUpdate(sql, new String[] { username, userpass,regtime });
			if (rs > 0) {// 插入成功
				out.println("注册成功!请记住您的用户名和密码");
				out.println("<br><a href='index.html'>请登录</a>");
			} else {// 插入失败
				out.println("注册失败!");
				out.println("<br><a href='regist.html'>重新注册</a>");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
