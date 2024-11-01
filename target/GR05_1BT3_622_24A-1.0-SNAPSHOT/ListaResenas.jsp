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

<!-- Filtro por tipo de comida -->
<form action="${pageContext.request.contextPath}/ResenaSv" method="get">
    <label for="tipoComida">Filtrar por tipo de comida:</label>
    <select name="tipoComida" id="tipoComida">
        <option value="Todas">Todas</option>
        <option value="Extranjera">Extranjera</option>
        <option value="Snacks">Snacks</option>
        <option value="Tradicional">Tradicional</option>
    </select>
    <button type="submit">Filtrar</button>
</form>

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
    <p>
        Likes: <span id="likeCount-<%= resena.getId() %>"><%= resena.getReacciones().stream().filter(r -> r.getTipo().equals("like")).count() %></span>
        | Dislikes: <span id="dislikeCount-<%= resena.getId() %>"><%= resena.getReacciones().stream().filter(r -> r.getTipo().equals("dislike")).count() %></span>
    </p>

    <!-- Botón para dar Like -->
    <form action="${pageContext.request.contextPath}/ReaccionSv" method="post" style="display:inline;">
        <input type="hidden" name="tipo" value="like">
        <input type="hidden" name="resenaId" value="<%= resena.getId() %>">
        <button type="submit">👍 Like</button>
    </form>

    <!-- Botón para dar Dislike -->
    <form action="${pageContext.request.contextPath}/ReaccionSv" method="post" style="display:inline;">
        <input type="hidden" name="tipo" value="dislike">
        <input type="hidden" name="resenaId" value="<%= resena.getId() %>">
        <button type="submit">👎 Dislike</button>
    </form>

    <a href="${pageContext.request.contextPath}/ComentarioSv?resenaId=<%= resena.getId() %>">Ver comentarios</a>
</div>

<%
        }
    }
%>
<br>
<br>
<!-- Botón para ir al formulario de creación de reseña -->
<form action="${pageContext.request.contextPath}/FormularioResena.jsp" method="get" style="display: inline;">
    <button type="submit">Crear Nueva Reseña</button>
</form>
</body>
</html>





