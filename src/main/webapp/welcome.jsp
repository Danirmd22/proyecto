<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Bienvenido, <%= request.getParameter("nombre") %> <%= request.getParameter("apellidos") %></h1>
</body>
</html>