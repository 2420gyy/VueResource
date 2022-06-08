<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/emp/update" method="post">
    id: <h5>${requestScope.emp.id}</h5>
    <input type="hidden" name="id" value="${requestScope.emp.id}">
    name: <input type="text" name="name" value="${requestScope.emp.name}"><br>
    salary: <input type="text" name="salary" value="${requestScope.emp.salary}"><br>
    age: <input type="text" name="age" value="${requestScope.emp.age}"><br>
    <input type="submit" value="confirm">

</form>

</body>
</html>