<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 01/11/2024
  Time: 08:47 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Anuncio" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Anuncios</title>
</head>
<body>
<h2>Anuncios de Restaurantes</h2>

<!-- Filtro por tipo de comida -->
<form action="${pageContext.request.contextPath}/AnuncioSv" method="get">
    <label for="tipoComidaFilter">Filtrar por tipo de comida:</label>
    <select name="tipoComida" id="tipoComidaFilter">
        <option value="Todas">Todas</option>
        <option value="Extranjera">Extranjera</option>
        <option value="Snacks">Snacks</option>
        <option value="Tradicional">Tradicional</option>
    </select>
    <button type="submit">Filtrar</button>
</form>

<!-- Mostrar lista de anuncios -->
<%
    List<Anuncio> anuncios = (List<Anuncio>) request.getAttribute("anuncios");
    if (anuncios == null || anuncios.isEmpty()) {
%>
<p>No hay anuncios para mostrar.</p>
<%
} else {
    for (Anuncio anuncio : anuncios) {
%>
<div style="border: 1px solid #ccc; padding: 10px; margin: 10px;">
    <h3><%= anuncio.getNombreRestaurante() %> - <%= anuncio.getTipoComida() %></h3>
    <p>Ubicación: <%= anuncio.getUbicacion() %></p>
    <p>Descripción de Ofertas: <%= anuncio.getDescripcionOfertas() %></p>
    <p>Publicado el: <%= anuncio.getFechaPublicacion() %></p>
</div>
<%
        }
    }
%>
<br>
<br>
<!-- Enlace para ir a la página de creación de un nuevo anuncio -->
<a href="${pageContext.request.contextPath}/FormularioAnuncio.jsp">Agregar Nuevo Anuncio</a>

<!-- Botón para regresar a la página Home -->
<br><br>
<a href="${pageContext.request.contextPath}/Home.jsp">
    <button type="button">Regresar a Home</button>
</a>
</body>
</html>

