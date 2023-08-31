package web2.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginOutServlet", value = "/LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session并且杀死
        request.getSession().invalidate();
        //删除30天自动登录
        Cookie autoLoginC=new Cookie("autologin","");
        autoLoginC.setMaxAge(0);
        autoLoginC.setPath(request.getContextPath()+"/");
        response.addCookie(autoLoginC);
        //重定向回首页
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
