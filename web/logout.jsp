<%--
  Created by IntelliJ IDEA.
  User: wuxiutong
  Date: 2015/8/27
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登出</title>
</head>
<body>
<h2>正在登出................</h2>
<%
  request.getSession().removeAttribute("userid");
  request.getSession().removeAttribute("username");
  request.getSession().removeAttribute("message");
  request.getRequestDispatcher("/login.jsp").forward(request,response);
%>
</body>
</html>
