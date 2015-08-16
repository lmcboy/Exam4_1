package com.hand.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","mc","123456");
			String sql = "SELECT first_name FROM customer WHERE first_name = '" + name + "'";
			Statement st;
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				request.getSession().setAttribute("log", "login_success");
				RequestDispatcher rd = request.getRequestDispatcher("/login_success.jsp");
				request.setAttribute("msg", "恭喜，登录成功！!!!");
				rd.forward(request, response);
			}
			else{
				request.getSession().setAttribute("log", "login_fail");
				RequestDispatcher rd = request.getRequestDispatcher("/login_fail.jsp");
				request.setAttribute("msg", "很遗憾，登录失败！！！");
				rd.forward(request, response);
			}
			conn.close();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
