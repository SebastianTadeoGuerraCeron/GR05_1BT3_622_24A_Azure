<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Promocion" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Promociones</title>
  <script>
    window.onload = function() {
      const urlParams = new URLSearchParams(window.location.search);

      // Mostrar mensaje de éxito si promocionSuccess=true
      if (urlParams.get('promocionSuccess') === 'true') {
        alert('¡Promoción publicada exitosamente!');
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
      padding: 8px 16px;
      font-size: 0.9em;
      border-radius: 8px;
      text-decoration: none;
      font-weight: 500;
      transition: background-color 0.3s ease;
      border: none;
      cursor: pointer;
      margin: 5px 0;
      width: 100%;
      max-width: 250px;
    }

    .button:hover {
      background-color: #FF4A4A;
    }

    .button-secondary {
      background-color: #34495e;
      font-size: 1.1em;
    }

    .button-secondary:hover {
      background-color: #2c3e50;
    }

    .action-buttons {
      margin-top: 30px;
      display: flex;
      flex-direction: column;
      align-items: flex-start; /* Alinear los botones a la izquierda */
      width: 100%;
    }

    .promocion {
      border: 1px solid #ddd;
      padding: 15px;
      border-radius: 8px;
      margin-bottom: 15px;
      text-align: left;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 750px;
    }

    .promocion h3 {
      color: #FF6D6D;
      font-size: 1.5em;
      margin: 0 0 5px 0;
    }

    .promocion p {
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
  <!-- Título de la página -->
  <div class="header">
    <h2>Promociones</h2>
  </div>

  <!-- Mostrar lista de promociones -->
  <%
    List<Promocion> promociones = (List<Promocion>) request.getAttribute("promociones");
    if (promociones == null || promociones.isEmpty()) {
  %>
  <p>No hay promociones disponibles en este momento</p>
  <%
  } else {
    for (Promocion promocion : promociones) {
  %>
  <div class="promocion">
    <h3><%= promocion.getTitulo() != null ? promocion.getTitulo() : "Título no disponible" %> -
      <%= promocion.getTipoPromocion() != null ? promocion.getTipoPromocion() : "Tipo de promoción no especificado" %>
    </h3>
    <p>Nombre del Restaurante: <%= promocion.getNombreRestaurante() != null ? promocion.getNombreRestaurante() : "No disponible" %></p>
    <p>Ubicación: <%= promocion.getUbicacion() != null ? promocion.getUbicacion() : "Ubicación no disponible" %></p>
    <p class="oferta">Condiciones: <%= promocion.getCondiciones() != null ? promocion.getCondiciones() : "Condiciones no disponibles" %></p>
    <p>Publicado el: <%= promocion.getFechaPublicacion() != null ? promocion.getFechaPublicacion().toString() : "Fecha no disponible" %></p>
  </div>
  <%
      }
    }
  %>

  <!-- Botones de acción -->
  <div class="action-buttons">
    <a href="${pageContext.request.contextPath}/FormularioPromocion.jsp" class="button">Agregar Nueva Promoción</a>
    <a href="${pageContext.request.contextPath}/Home.jsp" class="button button-secondary">Regresar a Home</a>
  </div>
</div>
</body>
</html>
