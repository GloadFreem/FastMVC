<%@page import="javax.naming.*"%>
<%@page import="java.sql.*" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//初始化Context,使用InitialContext初始化Context
//Context context = new InitialContext();
//通过JNDI查找数据源 java:com/env/jdbc/descbase
//BasicDataSource ds = (BasicDataSource)context.lookup("java:comp/env/jdbc/descbase");
//获取数据库连接
//Connection conn = ds.getConnection();
//获取Statement
//Statement stmt = conn.createStatement();
//执行查询,返回ResultSet对象
//ResultSet rs = stmt.executeQuery("select * from User");
//while(rs.next()){
//	out.println(rs.getString(2)+"\t"+rs.getString(3)+"<br/>");
//}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<title>文件上传</title>
  </head>
  
  <body>
  <form method="post" action="updateIdentiyTypeUser.action" enctype="multipart/form-data">
  	文件名:<input type="text" id="ideniyType" name="ideniyType"/><br/>
  	选择A文件:<input type="file" id="identiyCarA" name="file"/><br/>
  	选择B文件:<input type="file" id="identiyCarB" name="identiyCarB"/><br/>
  	<input type="submit" value="上传"/><br/>
  </form>
  </body>
</html>
