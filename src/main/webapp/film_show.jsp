<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flim 列表页面</title>
</head>
<body>
<div>
	<table border="1" cellspacing="0" cellpadding="5" bordercolor="silver" align="center" style="text-align: center;font-size: 12px;">
		<tr colspan="6" align="center" bgcolor="#E8E8E8" style="text-align: center">Film 列表</tr>
		<tr>
			<td>Film_id</td>
			<td>Title</td>
			<td>Desciption</td>
			<td>Language</td>
			<td></td>
			<td></td>
		</tr>
		<%
			ResultSet rs = (ResultSet)request.getAttribute("rs");
			while(rs.next()){
		%>
		<tr>
			<td><%=rs.getInt(1) %></td>
			<td><%=rs.getString(2) %></td>
			<td><%=rs.getString(3) %></td>
			<td><%=rs.getString(4) %></td>
			<td><a href="<%= request.getContextPath() %>/UpdateTempServlet?id=<%= rs.getInt(1)%>">修改</a></td>
			<td><a href="<%= request.getContextPath() %>/DeleteServlet?id=<%= rs.getInt(1)%>">删除</a></td>
		</tr>
		<%} %>
		<tr colspan="6" align="center" bgcolor="#E8E8E8" style="text-align: center"><a href="<%= request.getContextPath() %>/TempServlet">添加</a></tr>
	</table>
</div>
</body>
</html>