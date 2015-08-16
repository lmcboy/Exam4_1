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

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
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
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","mc","123456");
			int id = Integer.parseInt(request.getParameter("id"));
			conn.setAutoCommit(false);
			String sql1 = "DELETE FROM film"
						+ " WHERE film_id = " + id;
			
			String sql2 = "DELETE FROM film_category"
					+ " WHERE film_id = " + id;
			
			String sql3 = "DELETE FROM inventory"
					+ " WHERE film_id = " + id;
			
			String sql4 = "DELETE FROM rental"
					+ " WHERE inventory_id in (select inventory_id from inventory where film_id=" + id + ")" ;
			
//			String sql5 =  "DELETE FROM film_text"
//					+ " WHERE film_id = " + id;
//			
//			String sql6 = "DELETE FROM payment"
//					+ " WHERE rental_id = (select rental_id from rental"
//					+ " WHERE inventory_id = (select inventory_id from inventory where film_id=" + id + " limit 1) limit 1)";
			Statement st;
			st = (Statement) conn.createStatement();
//			st.execute(sql6);
//			st.execute(sql5);
			st.execute(sql2);
			st.execute(sql4);
			st.execute(sql3);
			st.execute(sql1);
			conn.commit();
			RequestDispatcher rd = request.getRequestDispatcher("/ShowServlet");
			rd.forward(request, response);
			conn.close();
		} catch (ClassNotFoundException e1) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
