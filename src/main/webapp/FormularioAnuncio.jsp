<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 01/11/2024
  Time: 08:44 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Crear Nuevo Anuncio</title>
</head>
<body>
<h2>Crear Nuevo Anuncio</h2>
<form action="${pageContext.request.contextPath}/AnuncioSv" method="post">
  <label for="nombreRestaurante">Nombre del Restaurante:</label>
  <input type="text" id="nombreRestaurante" name="nombreRestaurante" required><br><br>

  <label for="tipoComida">Tipo de comida:</label>
  <select id="tipoComida" name="tipoComida" required>
    <option value="Extranjera">Extranjera</option>
    <option value="Snacks">Snacks</option>
    <option value="Tradicional">Tradicional</option>
  </select><br><br>

  <label for="ubicacion">Ubicación:</label>
  <input type="text" id="ubicacion" name="ubicacion" required><br><br>

  <label for="descripcionOfertas">Descripción de Ofertas:</label>
  <textarea id="descripcionOfertas" name="descripcionOfertas" rows="4" cols="50" required></textarea><br><br>

  <button type="submit">Crear Anuncio</button>
</form>

<a href="${pageContext.request.contextPath}/AnuncioSv">Volver a lista de anuncios</a>
</body>
</html>

