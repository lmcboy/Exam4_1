<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加电影页面</title>
<style type="text/css">
	body{
		color:#000;
		font-size:14px;
		margin:20px auto;
	}
</style>
</head>
<body>
<form action="<%= request.getContextPath() %>/AddServlet" method="post">
		<table border="1" cellspacing="0" cellpadding="5" bordercolor="silver" align="center">
			<tr>
				<td colspan="2" align="center" bgcolor="#E8E8E8">添加新电影</td>
			</tr>
			<tr>
				<td>Title：</td>
				<td><input type="text" name="title"/></td>
			</tr>
			<tr>
				<td>Description：</td>
				<td><input type="text" name="des"/></td>
			</tr>
			<tr>
				<td>Language：</td>
				<td>
					<% 
						int count = 0;
						ResultSet rs = (ResultSet)request.getAttribute("rs");
						while(rs.next()){
					%>
					<input type="radio" name="lan" value="<%=rs.getInt(1) %>" /><%=rs.getString(2) %>
					<%
							count++;
							if(count == 5){
					%>
					<br/>
					<%
							}
						}
					%>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="添加" onclick="return check(this)"/>
					<input type="reset" value="重置"/>
				</td>
			</tr>
		</table>		
	</form>
</body>
</html>