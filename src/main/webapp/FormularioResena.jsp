<%--
  Created by IntelliJ IDEA.
  User: Anthony
  Date: 31/10/2024
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Crear Nueva Reseña</title>
</head>
<body>
<h2>Crear Nueva Reseña</h2>
<form action="${pageContext.request.contextPath}/ResenaSv" method="post">
  <label for="restaurante">Nombre del restaurante:</label>
  <input type="text" id="restaurante" name="restaurante" required><br><br>

  <label for="tipoComida">Tipo de comida:</label>
  <select id="tipoComida" name="tipoComida" required>
    <option value="Extranjera">Extranjera</option>
    <option value="Snacks">Snacks</option>
    <option value="Tradicional">Tradicional</option>
  </select><br><br>

  <label for="descripcion">Descripción:</label>
  <textarea id="descripcion" name="descripcion" required></textarea><br><br>

  <button type="submit">Publicar Reseña</button>
</form>
<a href="${pageContext.request.contextPath}/ResenaSv">Volver a lista de reseñas</a>
</body>
</html>


