<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h2>登录页面</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    username:<input type="text" name="username"/><br>
    password:<input type="text" name="password"/>
<p>
    <input type="submit" value="Submit">
    <input type="button" onclick="location.href='${pageContext.request.contextPath}/ems/regist.jsp'" value="Regist">
</p>
</form>
</body>
</html>