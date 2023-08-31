package web2.web;

import web2.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AjaxHasUserNameServlet", value = "/AjaxHasUserNameServlet")
public class AjaxHasUserNameServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取用户名
        String username = request.getParameter("username");
        //验证用户名是否存在
        boolean hasUsername = userService.hasUsername(username);
        //返回结果
        response.getWriter().write(hasUsername?"用户名已经存在":"用户名可用");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
