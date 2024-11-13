<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Crear Nueva Reseña</title>
  <script>
    window.onload = function() {
      // Verificar si existen mensajes de error y mostrarlos como alert
      <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        String errorLengthMessage = (String) request.getAttribute("errorLengthMessage");
      %>
      <% if (errorMessage != null) { %>
      alert("<%= errorMessage %>");
      <% } %>
      <% if (errorLengthMessage != null) { %>
      alert("<%= errorLengthMessage %>");
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
      height: 100vh;
    }

    .container {
      width: 90%;
      max-width: 600px;
      background-color: #ffffff;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      padding: 20px;
      margin: 20px;
    }

    h2 {
      color: #FF6D6D;
      font-size: 2em;
      text-align: center;
      margin-bottom: 20px;
    }

    label {
      font-weight: bold;
      color: #555;
      display: block;
      margin-top: 10px;
    }

    input[type="text"], select, textarea {
      width: 95%;
      padding: 10px;
      margin-top: 5px;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-size: 1em;
      color: #333;
      background-color: #f5f5f5;
      resize: none; /* Evita redimensionar */
    }

    textarea {
      height: 100px;
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
      width: 35%;
      margin-top: 10px;
      text-align: center;
    }

    .button:hover {
      background-color: #FF4A4A;
    }

    .button-secondary {
      background-color: #34495e;
    }

    .button-secondary:hover {
      background-color: #2c3e50;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Crear Nueva Reseña</h2>
  <form action="${pageContext.request.contextPath}/ResenaSv" method="post">
    <label for="restaurante">Nombre del restaurante:</label>
    <input type="text" id="restaurante" name="restaurante" required>

    <label for="tipoComida">Tipo de comida:</label>
    <select id="tipoComida" name="tipoComida" required>
      <option value="Extranjera">Extranjera</option>
      <option value="Snacks">Snacks</option>
      <option value="Tradicional">Tradicional</option>
    </select>

    <label for="descripcion">Descripción:</label>
    <textarea id="descripcion" name="descripcion" required></textarea>

    <button type="submit" class="button">Publicar Reseña</button>
  </form>

  <a href="${pageContext.request.contextPath}/ResenaSv" class="button button-secondary">Volver a lista de reseñas</a>
</div>
</body>
</html>
