<%--
  Created by IntelliJ IDEA.
  User: Anthony
  Date: 31/10/2024
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Comentario" %>
<%@ page import="modelo.Resena" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Comentarios</title>
</head>
<body>
<h2>Comentarios para la Reseña de <%= ((Resena) request.getAttribute("resena")).getRestaurante() %></h2>
<a href="${pageContext.request.contextPath}/ResenaSv">Volver a reseñas</a>

<!-- Mostrar lista de comentarios -->
<%
  List<Comentario> comentarios = (List<Comentario>) request.getAttribute("comentarios");
  if (comentarios == null || comentarios.isEmpty()) {
%>
<p>No hay comentarios para esta reseña.</p>
<%
} else {
  for (Comentario comentario : comentarios) {
%>
<div style="border: 1px solid #ddd; padding: 5px; margin: 5px;">
  <p><%= comentario.getContenido() %></p>
  <p>Por: <%= comentario.getUsuario().getUsername() %> el <%= comentario.getFechaCreacion() %></p>
</div>
<%
    }
  }
%>

<!-- Formulario para agregar un nuevo comentario -->
<h3>Agregar un comentario</h3>
<form action="${pageContext.request.contextPath}/ComentarioSv" method="post">
  <input type="hidden" name="resenaId" value="<%= ((Resena) request.getAttribute("resena")).getId() %>">
  <label for="contenido">Comentario:</label>
  <textarea id="contenido" name="contenido" required></textarea><br><br>

  <button type="submit">Publicar Comentario</button>
</form>
</body>
</html>



