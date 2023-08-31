package web2.web;

import web2.domain.User;
import web2.exception.MsgException;
import web2.service.UserServiceImpl;
import web2.utiles.MD5Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取用户名
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //--处理记住用户名
        if ("ture".equals(request.getParameter("remname"))) {
            Cookie remname = new Cookie("remname", URLEncoder.encode(username, "utf-8"));
            remname.setMaxAge(60 * 60 * 24 * 30);
            remname.setPath(request.getContextPath() + "/login.jsp");
            response.addCookie(remname);
        } else {
            Cookie remname = new Cookie("remname", URLEncoder.encode(username, "utf-8"));
            remname.setMaxAge(0);
            remname.setPath(request.getContextPath() + "/login.jsp");
            response.addCookie(remname);
        }
        //登录用户
        try {
            User user = userService.login(username, password);
            request.getSession().setAttribute("user", user);

            //处理30天自动登录业务
            if ("ture".equals(request.getParameter("autologin"))) {
                Cookie autoLoginC = new Cookie("autologin", URLEncoder.encode(username + "#" + password, "utf-8"));
                autoLoginC.setMaxAge(60 * 60 * 24 * 30);
                autoLoginC.setPath(request.getContextPath()+"/");
                response.addCookie(autoLoginC);
            }
        }catch (MsgException e){
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            e.printStackTrace();
        }
        //回到首页
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
