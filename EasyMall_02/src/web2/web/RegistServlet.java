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
        //�������
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");


        //У������
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String valistr = request.getParameter("valistr");

        //--��֤��У��
        String valistr2= (String) request.getSession().getAttribute("valistr");
        if(valistr==null || "".equals(valistr)
                || valistr2==null || "".equals(valistr2)
                || !valistr.equals(valistr2)){
            request.setAttribute("msg","��֤�벻��ȷ");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }else {
            request.getSession().removeAttribute("valistr");
        }
        //--�ǿ�У��
        if(username == null || "".equals(username)){
            request.setAttribute("msg","�û�������Ϊ��");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(password == null || "".equals(password)){
            request.setAttribute("msg","���벻��Ϊ��");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(password2 == null || "".equals(password2)){
            request.setAttribute("msg","ȷ�����벻��Ϊ��");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(nickname == null || "".equals(nickname)){
            request.setAttribute("msg","�ǳƲ���Ϊ��");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(email == null || "".equals(email)){
            request.setAttribute("msg","���䲻��Ϊ��");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //--��������һ��У��
        if(!password.equals(password2)){
            request.setAttribute("msg","�������벻һ��");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        //--�����ʽУ��
        if(!email.matches("^\\w+@\\w+(\\.\\w+)+$")){
            request.setAttribute("msg","�����ʽ����ȷ");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }

        //ע���û�
        try {

            userService.regist(new User(0,username,password,nickname,email));
        } catch (MsgException e) {
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //��ʱˢ�»ص��µ���ҳ
        response.getWriter().write("��ϲע��ɹ�,3�����ת��ҳ");
        response.setHeader("refresh","3;url="+request.getContextPath()+"/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
