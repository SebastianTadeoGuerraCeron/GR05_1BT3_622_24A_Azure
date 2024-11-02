<%--
  Created by IntelliJ IDEA.
  User: Anthony
  Date: 31/10/2024
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Bienvenido</title>
</head>
<body>
<h2>Bienvenido a Las HueQuitas</h2>
<p>¡Explora, comenta y comparte reseñas gastronómicas!</p>

<!-- Enlaces a Reseñas y Anuncios -->
<a href="${pageContext.request.contextPath}/ResenaSv">Ir a Reseñas</a> |
<a href="${pageContext.request.contextPath}/AnuncioSv">Ir a Anuncios</a> |
<a href="${pageContext.request.contextPath}/LogoutSv">Cerrar sesión</a>
</body>
</html>


