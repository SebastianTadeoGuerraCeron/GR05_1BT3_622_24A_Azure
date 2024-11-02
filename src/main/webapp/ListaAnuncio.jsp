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

<!-- Formulario de filtros y búsqueda -->
<form action="${pageContext.request.contextPath}/AnuncioSv" method="get">
    <label for="nombreRestauranteFilter">Buscar por nombre de restaurante:</label>
    <input type="text" name="nombreRestaurante" id="nombreRestauranteFilter" placeholder="Ingrese nombre de restaurante"><br><br>

    <!-- Filtro por ubicación -->
    <label for="ubicacionFilter">Filtrar por ubicación:</label>
    <select name="ubicacion" id="ubicacionFilter">
        <option value="Todas">Todas</option>
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
<br><br>
<a href="${pageContext.request.contextPath}/FormularioAnuncio.jsp">Agregar Nuevo Anuncio</a>
<br><br>
<a href="${pageContext.request.contextPath}/Home.jsp">
    <button type="button">Regresar a Home</button>
</a>
</body>
</html>
