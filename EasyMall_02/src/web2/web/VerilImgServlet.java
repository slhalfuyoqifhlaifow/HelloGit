package web2.web;

import web2.utiles.VerifyCode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VerilImgServlet", value = "/VerilImgServlet")
public class VerilImgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ֹ����
        response.setIntHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");

        //������֤��
        VerifyCode vc = new VerifyCode();
        //�����֤��
        vc.drawImage(response.getOutputStream());
        //�洢��session
        String code = vc.getCode();
        request.getSession().setAttribute("valistr",code);

        System.out.println(code);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
