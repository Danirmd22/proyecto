<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
</head>
<body>
    <h2>Inicio de sesi�n</h2>
    <form action="LoginServlet" method="post">
        Usuario: <input type="text" name="username"><br>
        Contrase�a: <input type="password" name="password"><br>
        <input type="submit" value="Iniciar sesi�n">
    </form>
</body>
</html>