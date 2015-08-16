<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.mysql.jdbc.Statement"%>
<%@page import="java.security.interfaces.RSAKey"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改电影信息页面</title>
<style type="text/css">
	body{
		color:#000;
		font-size:14px;
		margin:20px auto;
	}
</style>
</head>
<form action="<%= request.getContextPath() %>/UpdateServlet" method="post">
		<table border="1" cellspacing="0" cellpadding="5" bordercolor="silver" align="center">
		<% 
			ResultSet rs = (ResultSet)request.getAttribute("rs"); 
			int id = (Integer)request.getAttribute("id");
			if(rs.next()){
		%>
			<tr>
				<td colspan="2" align="center" bgcolor="#E8E8E8">修改电影信息</td>
			</tr>
			<tr>
				<td>Film_id：</td>
				<td><input type="text" name="id" readonly="readonly" value="<%= id %>"/></td>
			</tr>
			<tr>
				<td>Title：</td>
				<td><input type="text" name="title" value="<%=rs.getString(2) %>"/></td>
			</tr>
			<tr>
				<td>Description：</td>
				<td><input type="text" name="des" value="<%=rs.getString(3) %>"/></td>
			</tr>
			<tr>
				<td>Language：</td>
				<td>
					<% 
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila","mc","123456");						
						String sql = "SELECT language_id,name FROM language ";
						Statement st;
						st = (Statement) conn.createStatement();
						ResultSet rs1 = st.executeQuery(sql);				
						int count = 0;
						while(rs1.next()){
					%>
					<input type="radio" name="lan" value="<%=rs1.getInt(1) %>" 
							<% if(rs.getInt(4) == rs1.getInt(1)){%>checked="checked"
							<%} %> /><%=rs1.getString(2) %>
					<%
							count++;
							if(count == 5){
					%>
					<br/>
					<%
							}
						}
						conn.close();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
					%>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="修改" onclick="return check(this)"/>
					<input type="reset" value="重置"/>
				</td>
			</tr>
		</table>		
	</form>
</body>
</body>
</html>