<%@ page import="web2.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<link rel="stylesheet" href="css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />

<div id="common_head">
  <div id="line1">
    <div id="content">
      <%
        if(session.getAttribute("user")==null){
      %>
      <a href="<%=request.getContextPath()%>/login.jsp">登录</a>
      &nbsp;&nbsp;|&nbsp;&nbsp;
      <a href="regist.jsp">注册</a>
      <%
      }else {
      %>
      欢迎回来,<%=((User)session.getAttribute("user")).getUsername()%>!
      <a href="<%=request.getContextPath()%>/LoginOutServlet">登出</a>
      <%
        }
      %>
    </div>
  </div>
  <div id="line2">
    <img id="logo" src="img/head/logo.jpg"/>
    <input type="text" name=""/>
    <input type="button" value="搜索"/>
    <span id="goto">
			<a id="goto_order" href="#">我的订单</a>
			<a id="goto_cart" href="#">我的购物车</a>
		</span>
    <img id="erwm" src="img/head/qr.jpg"/>
  </div>
  <div id="line3">
    <div id="content">
      <ul>
        <li><a href="#">首页</a></li>
        <li><a href="#">全部商品</a></li>
        <li><a href="#">手机数码</a></li>
        <li><a href="#">电脑平板</a></li>
        <li><a href="#">家用电器</a></li>
        <li><a href="#">汽车用品</a></li>
        <li><a href="#">食品饮料</a></li>
        <li><a href="#">图书杂志</a></li>
        <li><a href="#">服装服饰</a></li>
        <li><a href="#">理财产品</a></li>
      </ul>
    </div>
  </div>
</div>
