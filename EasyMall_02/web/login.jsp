<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: LIU
  Date: 2023/8/9
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css"/>
    <title>EasyMall欢迎您登录</title>
    <%
        Cookie[] cs = request.getCookies();
        Cookie findC = null;
        if (cs != null) {
            for (Cookie c : cs) {
                if ("remname".equals(c.getName())) {
                    findC = c;
                    break;
                }
            }
        }
        String username = "";
        if (findC != null) {
            username = URLDecoder.decode(findC.getValue(),"utf-8");
        }
    %>
</head>
<body>
<h1>欢迎登录EasyMall</h1>
<form action="<%=request.getContextPath()%>/LoginServlet" method="post">
    <table>
        <tr>
            <td colspan="2" style="color: red;text-align: center">
                <%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>
            </td>
        </tr>
        <tr>
            <td class="tdx">用户名:</td>
            <td><input type="text" name="username" value="<%=username%>"/></td>
        </tr>
        <tr>
            <td class="tdx">密码:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="checkbox" name="remname" value="true"
                        <%if (findC != null) {%>
                       checked="checked"
                        <%}%>
                />记住用户名
                <input type="checkbox" name="autologin" value="true"/>30天内自动登陆
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登录"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

