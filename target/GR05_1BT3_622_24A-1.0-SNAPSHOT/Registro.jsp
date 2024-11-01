<%--
  Created by IntelliJ IDEA.
  User: Anthony
  Date: 31/10/2024
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Registro</title>
</head>
<body>
<h2>Registro de Usuario</h2>
<form action="${pageContext.request.contextPath}/RegistroSv" method="post" autocomplete="off">
  <label for="email">Correo electrónico:</label>
  <input type="email" id="email" name="email" required><br><br>

  <label for="username">Nombre de usuario:</label>
  <input type="text" id="username" name="username" required><br><br>


  <label for="password">Contraseña:</label>
  <input type="password" id="password" name="password" required><br><br>

  <button type="submit">Registrarse</button>
</form>
<p>¿Ya tienes una cuenta? <a href="${pageContext.request.contextPath}/Login.jsp">Iniciar sesión</a></p>
</body>
</html>


