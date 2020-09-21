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
<title>课程添加</title>
</head>
<body>
	<center>
		<h1>课程添加</h1>
		<hr>
		<form action="<%=basePath%>/course/addCourse" method="post" onsubmit="return validateCode()">
			<table width="400px" cellspacing="0px" cellpadding="0px" border="1px">
				<tr>
					<td>课程名</td>
					<td><input type="text" name="courseName" value="${requestScope.course.couseName}"></td>
				</tr>
				<tr>
					<td>方向</td>
					<td>
						<select name="cID">
							<c:forEach items="${requestScope.ctList}" var="ctMap">
								<option value="${ctMap.id}" ${requestScope.course.cID==ctMap.id?'selected':''}>${ctMap.courseType}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>描述</td>
					<td>
						<textarea name="description">${requestScope.course.description}</textarea>
					</td>
				</tr>
				<tr>
					<td>时长</td>
					<td>
						<input name="courseTime" type="text" value="${requestScope.course.courseTime}">
					</td>
				</tr>
				<tr>
					<td>操作人</td>
					<td>
						<input name="operator" type="text" value="${sessionScope.username }" readonly="readonly">
					</td>
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
</body>
</html>