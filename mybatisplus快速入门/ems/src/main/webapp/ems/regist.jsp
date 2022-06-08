<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/regist" method="post">
        <table cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td valign="middle" align="right">用户名:</td>
                <td valign="middle" align="left">
                    <input type="text" name="username">
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">真实姓名:</td>
                <td valign="middle" align="left">
                    <input type="text" name="realname">
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">密码:</td>
                <td valign="middle" align="left">
                    <input type="password" name="password">
                </td>
            </tr>
            <tr>
                <td valign="middle" align="right">性别:</td>
                <td valign="middle" align="left">
                    <input type="radio" name="sex">男
                    <input type="radio" name="sex">女
                </td>
            </tr>
            <tr>
                <td>验证码:</td>

                <td>
                    <img id="num" src="verifyCode" alt="验证码" onClick="this.src=this.src+'?'+Math.random();" >
                    <input type="text" class="inputgri" name="code">
                    <span></span>
                </td>
            </tr>
        </table>
        <p>
            <input type="submit" value="submit>>">
        </p>
    </form>
</div>

</body>
</html>