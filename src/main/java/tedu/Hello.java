package tedu;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Hello {
    @RequestMapping( "/templates/hello")
    public String index(){
        return "hello";
    }

    public static void main(String[] args) {
        System.out.println("更新1");
        System.out.println("创建分支");
        System.out.println("主干添加");
    }
}

