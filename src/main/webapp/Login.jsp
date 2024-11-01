<%--
  Created by IntelliJ IDEA.
  User: Anthony
  Date: 31/10/2024
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Iniciar Sesión</title>
</head>
<body>
<h2>Iniciar Sesión</h2>
<form action="${pageContext.request.contextPath}/LoginSv" method="POST" autocomplete="off">
  <label for="email">Correo electrónico:</label>
  <input type="email" id="email" name="email"><br><br>

  <label for="password">Contraseña:</label>
  <input type="password" id="password" name="password"><br><br>

  <button type="submit">Iniciar sesión</button>
</form>

<c:if test="${not empty errorMessage}">
  <p style="color: red;">${errorMessage}</p>
</c:if>

<p>¿No tienes una cuenta? <a href="${pageContext.request.contextPath}/Registro.jsp">Registrarse</a></p>
</body>
</html>


