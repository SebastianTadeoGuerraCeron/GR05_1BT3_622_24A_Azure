<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 01/11/2024
  Time: 08:44 p.m.
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
  <select id="ubicacion" name="ubicacion" required>
    <optgroup label="Zona Norte">
      <option value="Calderón">Calderón</option>
      <option value="Carcelén">Carcelén</option>
      <option value="Ponceano">Ponceano</option>
      <option value="Cotocollao">Cotocollao</option>
      <option value="El Condado">El Condado</option>
    </optgroup>
    <optgroup label="Zona Centro Norte">
      <option value="La Carolina">La Carolina</option>
      <option value="González Suárez">González Suárez</option>
      <option value="Quito Tenis">Quito Tenis</option>
      <option value="La Pradera">La Pradera</option>
      <option value="Bellavista">Bellavista</option>
    </optgroup>
    <optgroup label="Zona Sur">
      <option value="Chillogallo">Chillogallo</option>
      <option value="La Magdalena">La Magdalena</option>
      <option value="Quitumbe">Quitumbe</option>
      <option value="El Recreo">El Recreo</option>
      <option value="Solanda">Solanda</option>
    </optgroup>
    <optgroup label="Zona Centro Colonial">
      <option value="San Roque">San Roque</option>
      <option value="San Juan">San Juan</option>
      <option value="El Tejar">El Tejar</option>
    </optgroup>
    <optgroup label="Zona Valle de Tumbaco">
      <option value="Cumbayá">Cumbayá</option>
      <option value="Tumbaco">Tumbaco</option>
      <option value="San Juan de Cumbayá">San Juan de Cumbayá</option>
      <option value="Nayón">Nayón</option>
      <option value="Lumbisí">Lumbisí</option>
    </optgroup>
    <optgroup label="Zona Valle de Los Chillos">
      <option value="San Rafael">San Rafael</option>
      <option value="Conocoto">Conocoto</option>
      <option value="La Armenia">La Armenia</option>
      <option value="Fajardo">Fajardo</option>
    </optgroup>
  </select><br><br>

  <label for="descripcionOfertas">Descripción de Ofertas:</label>
  <textarea id="descripcionOfertas" name="descripcionOfertas" rows="4" cols="50" required></textarea><br><br>

  <button type="submit">Crear Anuncio</button>
</form>

<a href="${pageContext.request.contextPath}/AnuncioSv">Volver a lista de anuncios</a>
</body>
</html>
