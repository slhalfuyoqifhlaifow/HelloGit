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
        //�����������
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //��ȡ�û���
        String username = request.getParameter("username");
        //��֤�û����Ƿ����
        boolean hasUsername = userService.hasUsername(username);
        //���ؽ��
        response.getWriter().write(hasUsername?"�û����Ѿ�����":"�û�������");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
