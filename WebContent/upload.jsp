<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="upload" method="post" enctype="multipart/form-data">
学号：${param.id}<br>
<input type="hidden" name="id" value="${param.id}">
图片上传：<input type="file" name="photo">
<input type="submit" value="上传">
</form>
</body>
</html>