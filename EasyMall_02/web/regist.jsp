<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>欢迎注册EasyMall</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/regist.css"/>
    <script type="application/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.js"></script>
    <script type="application/javascript">
        function setMsg(id,msg,color="red") {
            document.getElementById(id+"_msg").innerText=msg;
            document.getElementById(id+"_msg").style.color=color;
        }

        function checkNull(name,msg) {
            setMsg(name,"");
            var v=document.getElementsByName(name)[0].value;
            if(v==null || v==""){
                setMsg(name,msg)
                return false;
            }
            return true;
        }

        function checkForm() {
            var canSub=true;
            canSub=checkNull("username","用户名不能为空")&&canSub;
            canSub=checkNull("password","密码不能为空")&&canSub;
            canSub=checkNull("password2","确认密码不能为空")&&canSub;
            canSub=checkNull("nickname","昵称不能为空")&&canSub;
            canSub=checkNull("email","邮箱不能为空")&&canSub;
            canSub=checkNull("valistr","验证码不能为空")&&canSub;

            //两次密码一致校验
            var psw1=document.getElementsByName("password")[0].value;
            var psw2=document.getElementsByName("password2")[0].value;
            if(psw1 != null && psw1 != "" && psw2 != null && psw2 != "" && psw1 != psw2){
                setMsg("password2","两次密码不一致");
                canSub=false;
            }
            //邮箱校验
            var email=document.getElementsByName("email")[0].value;
            if(email!=null && email!=""&& !/^\w+@\w+(\.\w+)+$/.test(email)){
                setMsg("email","邮箱格式不正确");
                canSub=false;
            }
            /*//用户名校验
            var username=document.getElementsByName("username")[0].value;
            if(username == null || username == ""){
                //alert("用户名不能为空")
                //document.getElementById("username_msg").innerText="用户名不能为空";
                setMsg("username","用户名不能为空");
            }
            //密码校验
            var password=document.getElementsByName("password")[0].value;
            if(password == null || password == ""){
                setMsg("password","密码不能为空");
            }
*/
            return canSub;
        }

        //失去焦点校验密码是否一致
        function checkPassword2() {
            setMsg("password2","");
            var psw1=document.getElementsByName("password")[0].value;
            var psw2=document.getElementsByName("password2")[0].value;
            if(psw2==null || psw2==""){
                setMsg("password2","确认密码不能为空");
                return;
            }
            if(psw1!=null && psw1!="" && psw2!=null && psw2!="" && psw2!=psw1){
                setMsg("password2","两次密码不一致")
            }
        }

        //失去焦点校验邮箱
        function checkEmail() {
            setMsg("email","");
            var email=document.getElementsByName("email")[0].value;
            if(email==null || email==""){
                setMsg("email","邮箱不能为空");
                return;
            }
            if(email!=null && psw1!="" && !/^\w+@\w+(\.\w+)+$/.test(email)){
                setMsg("email","邮箱格式不正确")
            }
        }

        function checkUsername() {
            var isNotNull=checkNull("username","用户名不能为空");
            if(isNotNull){
                //检查用户名是否存在
                var username=document.getElementsByName("username")[0].value;
                $.get("<%=request.getContextPath()%>/AjaxHasUserNameServlet",{"username":username},function (data) {
                    //alert(data)
                    setMsg("username",data,data == "用户名可用" ? "green" : "red");
                });
            }
        }


        function changeImg(imgObj) {
            imgObj.src="<%=request.getContextPath()%>/VerilImgServlet?time="+new Date().getTime();
        }
    </script>
</head>
<body>
<h1>欢迎注册EasyMall</h1>
<form action="servlet/RegistServlet" method="POST" onsubmit="return checkForm()">
    <table>
        <tr>
            <td colspan="2" style="color: red;text-align: center">
                <%= request.getAttribute("msg")==null ? "" :request.getAttribute("msg")%>
            </td>
        </tr>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                <input type="text" name="username" onblur="checkUsername()" value="<%=request.getParameter("username")==null?"":request.getParameter("username")%>">
                <span style="color: red" id="username_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">密码：</td>
            <td>
                <input type="password" name="password" onblur="checkNull('password','密码不能为空')">
                <span style="color: red" id="password_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="password2" onblur="checkPassword2()">
                <span style="color: red" id="password2_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">昵称：</td>
            <td>
                <input type="text" name="nickname" onblur="checkNull('nickname','昵称不能为空')">
                <span style="color: red" id="nickname_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">邮箱：</td>
            <td><input type="text" name="email" onblur="checkEmail()">
                <span style="color: red" id="email_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">验证码：</td>
            <td>
                <input type="text" name="valistr" onblur="checkNull('valistr','验证码不能为空')">
                <img id="yzm_img" src="<%=request.getContextPath()%>/VerilImgServlet" style="cursor: pointer" onclick="changeImg(this)"/>
                <span style="color: red" id="valistr_msg"></span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="注册用户"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>