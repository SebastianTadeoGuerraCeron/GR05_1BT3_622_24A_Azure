<%--
  Created by IntelliJ IDEA.
  User: Anthony
  Date: 31/10/2024
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Resena" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reseñas</title>
</head>
<body>
<h2>Reseñas</h2>
<a href="${pageContext.request.contextPath}/LogoutSv">Cerrar sesión</a>

<!-- Mostrar lista de reseñas -->
<%
    List<Resena> resenas = (List<Resena>) request.getAttribute("resenas");
    if (resenas == null || resenas.isEmpty()) {
%>
<p>No hay reseñas para mostrar.</p>
<%
} else {
    for (Resena resena : resenas) {
%>
<div style="border: 1px solid #ccc; padding: 10px; margin: 10px;">
    <h3><%= resena.getRestaurante() %> - <%= resena.getTipoComida() %></h3>
    <p><%= resena.getDescripcion() %></p>
    <p>Creado por: <%= resena.getUsuario().getUsername() %> el <%= resena.getFechaCreacion() %></p>
    <a href="${pageContext.request.contextPath}/ComentarioSv?resenaId=<%= resena.getId() %>">Ver comentarios</a>
</div>
<%
        }
    }
%>

<!-- Formulario para crear una nueva reseña -->
<h3>Agregar una nueva reseña</h3>
<form action="${pageContext.request.contextPath}/ResenaSv" method="post">
    <label for="restaurante">Nombre del restaurante:</label>
    <input type="text" id="restaurante" name="restaurante" required><br><br>

    <label for="tipoComida">Tipo de comida:</label>
    <input type="text" id="tipoComida" name="tipoComida" required><br><br>

    <label for="descripcion">Descripción:</label>
    <textarea id="descripcion" name="descripcion" required></textarea><br><br>

    <button type="submit">Publicar Reseña</button>
</form>
</body>
</html>



