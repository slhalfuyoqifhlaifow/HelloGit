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
        //禁止缓存
        response.setIntHeader("Expires",-1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma","no-cache");

        //生成验证码
        VerifyCode vc = new VerifyCode();
        //输出验证码
        vc.drawImage(response.getOutputStream());
        //存储到session
        String code = vc.getCode();
        request.getSession().setAttribute("valistr",code);

        System.out.println(code);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
