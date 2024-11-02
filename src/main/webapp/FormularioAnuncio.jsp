<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Crear Nuevo Anuncio</title>
  <script>
    window.onload = function() {
      <% if ("true".equals(request.getParameter("error"))) { %>
      alert("El anuncio contiene palabras ofensivas y no se ha publicado.");
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
      padding: 12px;
      font-size: 1em;
      border-radius: 8px;
      border: none;
      cursor: pointer;
      width: 30%;
      margin-top: 20px;
      text-align: center;
      text-decoration: none;
      transition: background-color 0.3s;
    }

    .button:hover {
      background-color: #FF4A4A;
    }

    .button-secondary {
      background-color: #34495e;
      margin-top: 10px;
    }

    .button-secondary:hover {
      background-color: #2c3e50;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Crear Nuevo Anuncio</h2>
  <form action="${pageContext.request.contextPath}/AnuncioSv" method="post">
    <label for="nombreRestaurante">Nombre del Restaurante:</label>
    <input type="text" id="nombreRestaurante" name="nombreRestaurante" required>

    <label for="tipoComida">Tipo de comida:</label>
    <select id="tipoComida" name="tipoComida" required>
      <option value="Extranjera">Extranjera</option>
      <option value="Snacks">Snacks</option>
      <option value="Tradicional">Tradicional</option>
    </select>

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
    </select>

    <label for="descripcionOfertas">Descripción de Ofertas:</label>
    <textarea id="descripcionOfertas" name="descripcionOfertas" required></textarea>

    <button type="submit" class="button">Crear Anuncio</button>
  </form>

  <a href="${pageContext.request.contextPath}/AnuncioSv" class="button button-secondary">Volver a lista de anuncios</a>
</div>
</body>
</html>
