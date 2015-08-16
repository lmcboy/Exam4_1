package com.hand.Exam1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class Exam1 {

	static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","mc","123456");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			return conn;
		}
	}
	public static void main(String[] args) {
		
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("请输入 Country ID：");
			int id = sc.nextInt();
			Connection conn = getConn();
			Statement st = (Statement) conn.createStatement();
			String sql1 = "SELECT country FROM country WHERE country_id=" + id;
			ResultSet rs1 = st.executeQuery(sql1);
			if(rs1.next()){
				System.out.println("Country " + rs1.getString(1) + " 的城市->");
			}
			String sql2 = "SELECT city_id, city FROM city WHERE country_id=" + id;
			ResultSet rs2 = st.executeQuery(sql2);
			System.out.println("城市ID \t" + "| \t" + "城市名称");
			while(rs2.next()){
				System.out.print(rs2.getInt(1) + " \t");
				System.out.print("| \t");
				System.out.println(rs2.getString(2));
			}
			conn.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
