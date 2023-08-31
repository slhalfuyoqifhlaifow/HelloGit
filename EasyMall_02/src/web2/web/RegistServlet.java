package web2.web;


import web2.domain.User;
import web2.exception.MsgException;
import web2.service.UserServiceImpl;
import web2.utiles.MD5Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegistServlet", value = "/RegistServlet")
public class RegistServlet extends HttpServlet {
    private UserServiceImpl userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        //校验数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String valistr = request.getParameter("valistr");

        //--验证码校验
        String valistr2= (String) request.getSession().getAttribute("valistr");
        if(valistr==null || "".equals(valistr)
                || valistr2==null || "".equals(valistr2)
                || !valistr.equals(valistr2)){
            request.setAttribute("msg","验证码不正确");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }else {
            request.getSession().removeAttribute("valistr");
        }
        //--非空校验
        if(username == null || "".equals(username)){
            request.setAttribute("msg","用户名不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(password == null || "".equals(password)){
            request.setAttribute("msg","密码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(password2 == null || "".equals(password2)){
            request.setAttribute("msg","确认密码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(nickname == null || "".equals(nickname)){
            request.setAttribute("msg","昵称不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(email == null || "".equals(email)){
            request.setAttribute("msg","邮箱不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //--两次密码一致校验
        if(!password.equals(password2)){
            request.setAttribute("msg","两次密码不一致");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        //--邮箱格式校验
        if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
            request.setAttribute("msg","邮箱格式不正确");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        //注册用户
        try {

            userService.regist(new User(0,username,password,nickname,email));
        } catch (MsgException e) {
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //定时刷新回到新的主页
        response.getWriter().write("恭喜注册成功,3秒后跳转首页");
        response.setHeader("refresh","3;url="+request.getContextPath()+"/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
