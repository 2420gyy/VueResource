<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style type="text/css">
        body{
            text-align: center;
        }
        table{
            margin: 0 auto;
        }
    </style>
    <script type="text/javascript">
        function nowTime(){
            var now = new Date();
            var temp = "";
            var year = now.getFullYear();
            temp += year + "-";
            var month = now.getMonth() + 1;
            temp += month + "-";
            var day = now.getDay();
            temp += day + " ";
            var hour = now.getHours();
            temp += hour + ":";
            var min = now.getMinutes();
            temp += min + ":";
            var sec = now.getSeconds();
            temp += sec + " ";
            // var week = now.getDay();
            // temp += "星期" + week;
            document.getElementById("main").innerHTML ="当前时间 " + temp;
        }
    </script>
</head>
<body onload="nowTime()">
<div id="main">	</div>
<div>
<%--    ${sessionScope.expression} expression：request 对象中的属性。--%>
<h2>Welcome ${sessionScope.user.username}！</h2>
    <a type="button" value="findAll" href="${pageContext.request.contextPath}/emp/findAll">findAll</a>
<table border="1px">
    <thead>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>salary</td>
        <td>age</td>
        <td colspan="2">Operation</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="emp" items="${requestScope.emps}">
    <tr >
        <td>${emp.id}</td>
        <td>${emp.name}</td>
        <td>${emp.salary}</td>
        <td>${emp.age}</td>
        <td><a href="${pageContext.request.contextPath}/emp/toDelete?id=${emp.id}">delete</a></td>
<%--        <td><a href="${pageContext.request.contextPath}/emp/toDelete/${emp.id}">delete</a></td>--%>
        <td><a href="${pageContext.request.contextPath}/emp/findOne?id=${emp.id}">update</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
<%--    <button  href="${pageContext.request.contextPath}/addEmp">Add User</button>--%>
    <p>
        <input type="button" value="Add Employee" onclick="location='${pageContext.request.contextPath}/ems/addEmp.jsp'">
    </p>
</div>
</body>
</html>