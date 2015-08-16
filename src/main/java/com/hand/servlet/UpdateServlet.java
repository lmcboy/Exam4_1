package com.hand.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","mc","123456");
			conn.setAutoCommit(false);
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String des = request.getParameter("des");
			int lan = Integer.parseInt(request.getParameter("lan"));
			String sql = "UPDATE film SET title='" + title + "',description='" + des + "',language_id=" + lan
						+ " WHERE film_id = " + id;
			Statement st;
			st = (Statement) conn.createStatement();
			st.executeUpdate(sql);
			RequestDispatcher rd = request.getRequestDispatcher("/ShowServlet");
			rd.forward(request, response);
			conn.commit();
			conn.close();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
