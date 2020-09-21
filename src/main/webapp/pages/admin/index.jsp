<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页面</title>
    <style type="text/css">
        .code
        {
            background:url(code_bg.jpg);
            font-family:Arial;
            font-style:italic;
            color:blue;
            font-size:20px;
            border:0;
            padding:2px 3px;
            letter-spacing:3px;
            font-weight:bolder;
            float:left;
            cursor:pointer;
            width:40x;
            height:20px;
            line-height:20px;
            text-align:center;
            vertical-align:middle;
        }
        a
        {
            text-decoration:none;
            font-size:12px;
            color:#288bc4;
        }
        a:hover
        {
            text-decoration:underline;
        }
    </style>
    <script type="text/javascript">
        var code;
        function createCode()
        {
            code = "";
            var codeLength = 4; //验证码的长度
            var checkCode = document.getElementById("checkCode");
            var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
            for(var i = 0; i < codeLength; i++)
            {
                var charNum = Math.floor(Math.random() * 52);
                code += codeChars[charNum];
            }
            if(checkCode)
            {
                checkCode.className = "code";
                checkCode.innerHTML = code;
            }
        }
        function validateCode()
        {
            var inputCode=document.getElementById("inputCode").value;
            if(inputCode.length <= 0)
            {
                alert("请输入验证码！");
                return false;
            }
            else if(inputCode.toUpperCase() != code.toUpperCase())
            {
                alert("验证码输入有误！");
                createCode();
                return false;
            }
            else
            {
                //alert("验证码正确！");
                return true;
            }
        }
    </script>
</head>
<body onload="createCode()">
<center>
    <h1>用户登录</h1>
    <form action="<%=basePath%>/user/login" method="post" onsubmit="return validateCode()">
        <table width="300px" cellspacing="0px" cellpadding="0px" border="1px">
            <tr>
                <td>用户名</td>
                <td colspan="2"><input type="text" name="username" placeholder="用户名为3-12位字母数字或下划线组合" pattern="\w{3,12}" required="required"></td>
            </tr>
            <tr>
                <td>密&nbsp;码</td>
                <td  colspan="2"><input type="password" name="password" placeholder="长度为5-12位字母数字或下划线组合" pattern="\w{5,12}" required="required"></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td style="border-right-style:none;">
                    <input type="text" name="checkCode" placeholder="请输入验证码" id="inputCode">
                </td>
                <td style="border-left-style:none;"><div class="code" id="checkCode" onclick="createCode()" ></div></td>
            </tr>
            <tr>
                <td colspan="3" style="text-align:center">
                    <input type="submit" value="登录">
                    <input type="reset" value="取消">
                </td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>