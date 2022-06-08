<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/ems/emplist.jsp">main</a>
<h2>add Emp info:</h2>
<div>
    <form action="${pageContext.request.contextPath}/emp/saveEmp" method="post">
    name: <input type="text" name="name"><br>
    salary: <input type="text" name="salary"><br>
    age: <input type="text" name="age"><br>
        <input type="submit" value="confirm">
    </form>
</div>
</body>
</html>