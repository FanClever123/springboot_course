<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员添加</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/static/js/sweetalert/sweetalert.css">
	<script type="text/javascript" src="<%=basePath%>/static/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/sweetalert/sweetalert.min.js"></script>
</head>
<body>
	<center>
		<h1>添加管理员</h1>
		<hr>
		<form action="javascript:void(0)" method="post" onsubmit="return validateCode()">
			<table width="300px" cellspacing="0px" cellpadding="0px" border="1px">
				<tr>
					<td>用户名</td>
					<td><input type="text" name="username" placeholder="用户名为3-12位字母数字或下划线组合" pattern="\w{3,12}" required="required"></td>
				</tr>
				<tr>
					<td>密&nbsp;码</td>
					<td><input type="password" name="password" placeholder="密码长度为5-12位字母数字或下划线组合" pattern="\w{5,12}" required="required"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="添加">
						<input type="reset" value="取消">
					</td>
				</tr>
			</table>
		</form>
	</center>
	<script type="text/javascript">
		$(function(){
		    $("input[type=submit]").click(function(){
		        $.ajax({
					url:"<%=basePath %>/user/addUser",
					type:"POST",
					dataType:"JSON",
					data:{
					    "username":$("input[name=username]").val(),
					    "password":$("input[name=password]").val()
					},
					success:function(rs){
						var flag = rs.flag;
						if(flag){//true
						    swal({
								title:"温馨提示",
								text:"添加成功"
							},function(){
						        location.href="<%=basePath %>/user/getUsers";
							});
						}else{//false
                            swal({
                                title:"温馨提示",
                                text:"添加失败,"+rs.msg
                            });
						}
					},
					error:function(rs){
                        swal({
                            title:"温馨提示",
                            text:"添加失败"
                        });
					}
				});
			});
		});
	</script>
</body>
</html>