<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Anuncio" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Lista de Anuncios</title>
  <script>
    window.onload = function() {
      const urlParams = new URLSearchParams(window.location.search);

      // Mostrar mensaje de éxito si anuncioSuccess=true
      if (urlParams.get('anuncioSuccess') === 'true') {
        alert('¡Anuncio publicado exitosamente!');
      }

      // Verificar si existen mensajes de error y mostrarlos como alert
      <% String errorSpecialCharacterMessage = (String) request.getAttribute("errorSpecialCharacterMessage"); %>
      <% if (errorSpecialCharacterMessage != null) { %>
      alert("<%= errorSpecialCharacterMessage %>");
      <% } %>
    };
  </script>
  <style>
    /* Importar Google Fonts */
    @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap');

    body {
      font-family: 'Roboto', sans-serif;
      background: linear-gradient(to right, #FF914D, #FF6D6D); /* Fondo degradado */
      color: #333;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .container {
      width: 90%;
      max-width: 1000px;
      margin: 20px auto;
      background-color: #ffffff;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      padding: 20px;
      display: flex;
    }

    .sidebar {
      width: 25%;
      padding-right: 20px;
      display: flex;
      flex-direction: column;
      align-items: flex-start;
    }

    .main-content {
      width: 75%;
    }

    .header {
      text-align: center;
      margin-bottom: 20px;
    }

    h2 {
      color: #FF6D6D; /* Color de título */
      font-size: 2.8em;
      font-weight: 700;
      margin: 0;
    }

    .button {
      display: inline-flex;
      align-items: center;
      background-color: #FF6D6D;
      color: white;
      padding: 8px 16px; /* Tamaño reducido */
      font-size: 0.9em; /* Tamaño de fuente reducido */
      border-radius: 8px;
      text-decoration: none;
      font-weight: 500;
      transition: background-color 0.3s ease;
      border: none;
      cursor: pointer;
      margin: 5px 0; /* Separación entre botones */
      width: 100%;
      max-width: 250px; /* Limitar el ancho del botón */
    }

    .button:hover {
      background-color: #FF4A4A;
    }

    .button-secondary {
      background-color: #34495e;
      font-size: 1.1em; /* Aumentar el tamaño de la fuente en el botón secundario también */
    }

    .button-secondary:hover {
      background-color: #2c3e50;
    }

    .filter-form {
      display: flex;
      flex-direction: column;
      align-items: flex-start;
      width: 100%;
    }

    .filter-form input, .filter-form select {
      padding: 10px;
      margin: 5px 0;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-size: 14px;
      width: 90%;
      max-width: 250px;
      color: #333;
      background-color: #f5f5f5;
    }

    .filter-form button {
      padding: 10px 20px;
      font-size: 1em;
      border-radius: 8px;
      cursor: pointer;
      background-color: #FF6D6D;
      color: white;
      border: none;
      margin-top: 10px;
      width: 90%;
      max-width: 250px;
    }

    .filter-form button:hover {
      background-color: #FF4A4A;
    }

    .action-buttons {
      margin-top: 30px; /* Margen superior para separar los botones de acción del botón de filtro */
      display: flex;
      flex-direction: column;
      width: 80%;
    }

    .anuncio {
      border: 1px solid #ddd;
      padding: 15px;
      border-radius: 8px;
      margin-bottom: 15px;
      text-align: left;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .anuncio h3 {
      color: #FF6D6D;
      font-size: 1.5em;
      margin: 0 0 5px 0;
    }

    .anuncio p {
      font-size: 1.1em;
      margin: 5px 0;
      color: #555;
    }

    .oferta {
      font-size: 1.2em;
      color: #FF4A4A;
      background-color: #FFF3E0;
      padding: 10px;
      border-radius: 5px;
      font-weight: bold;
      margin-top: 10px;
    }
  </style>
</head>
<body>
<div class="container">
  <!-- Barra lateral izquierda para el filtro -->
  <div class="sidebar">
    <!-- Formulario de filtros y búsqueda -->
    <form action="${pageContext.request.contextPath}/AnuncioSv" method="get" class="filter-form">
      <label for="nombreRestauranteFilter" style="font-weight: bold; color: #553;">Filtrar por tipo de comida:</label>
      <input type="text" name="nombreRestaurante" id="nombreRestauranteFilter" placeholder="Ingresa el nombre de restaurante">

      <label for="ubicacionFilter" style="font-weight: bold; color: #554;">Buscador por el nombre de restaurante:</label>
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
      </select>

      <button type="submit">Filtrar</button>
    </form>

    <!-- Botones de acción separados del botón de filtro -->
    <div class="action-buttons">
      <a href="${pageContext.request.contextPath}/FormularioAnuncio.jsp" class="button">Agregar Nuevo Anuncio</a>
      <a href="${pageContext.request.contextPath}/Home.jsp" class="button button-secondary">Regresar a Home</a>
    </div>
  </div>

  <!-- Contenido principal para los anuncios -->
  <div class="main-content">
    <div class="header">
      <h2>Anuncios de Restaurantes</h2>
    </div>

    <!-- Mostrar lista de anuncios -->
    <%
      List<Anuncio> anuncios = (List<Anuncio>) request.getAttribute("anuncios");
      if (anuncios == null || anuncios.isEmpty()) {
    %>
    <p>No hay anuncios disponibles para mostrar</p>
    <%
    } else {
      for (Anuncio anuncio : anuncios) {
    %>
    <div class="anuncio">
      <h3><%= anuncio.getNombreRestaurante() %> - <%= anuncio.getTipoComida() %></h3>
      <p>Ubicación: <%= anuncio.getUbicacion() %></p>
      <p class="oferta">Descripción de Ofertas: <%= anuncio.getDescripcionOfertas() %></p>
      <p>Publicado el: <%= anuncio.getFechaPublicacion() %></p>
    </div>
    <%
        }
      }
    %>
  </div>
</div>
</body>
</html>