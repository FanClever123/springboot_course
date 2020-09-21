<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员查询</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath%>/static/js/sweetalert/sweetalert.css">
	<script type="text/javascript" src="<%=basePath%>/static/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/sweetalert/sweetalert.min.js"></script>
</head>
<body>
	<center>
		<h1>管理员查询</h1>
		<hr>
		<table cellspacing="0px" cellpadding="0px" border="1px" width="600px">
			<thead>
				<tr>
					<th>用户名</th>
					<th>类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.userList }" var="map">
					<tr>
						<td>${map.username }</td>
						<td>
							<c:choose>
								<c:when test="${map.flag==1 }">超级管理员</c:when>
								<c:otherwise>普通管理员</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${map.flag==1}">
									暂无操作
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" onclick="delUser(${map.id})">删除</a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</center>
<script type="text/javascript">
	function delUser(userId){
	    $.ajax({
			url:"<%=basePath %>/user/scUser",
			type:"POST",
			dataType:"JSON",
			data:{
			    "userId":userId
			},
			success:function(rs){
				var f = rs.flag;
				if(f){//删除成功
                    swal({
                        title:"温馨提示",
                        text:"删除成功",
						type:"success"
                    },function(){
                        location.reload();
					});
				}else{//删除失败
					var msg = rs.msg;
                    swal({
                        title:"温馨提示",
                        text:"删除失败,"+msg
                    });
				}
			},
			error:function(rs){
				swal({
					title:"温馨提示",
					text:"哎呀，俺正在忙，请稍后"
				});
			}
		});
	}
</script>
</body>
</html>