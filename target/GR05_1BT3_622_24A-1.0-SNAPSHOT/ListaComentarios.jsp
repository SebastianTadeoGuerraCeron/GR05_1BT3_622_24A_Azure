<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Comentario" %>
<%@ page import="modelo.Resena" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Comentarios</title>
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
      flex-direction: column;
      align-items: center;
    }

    .container {
      width: 90%;
      max-width: 800px;
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
      color: #FF6D6D;
      font-size: 2.2em;
      font-weight: 700;
      margin: 0 0 20px;
    }

    h3 {
      color: #34495e;
      font-size: 1.5em;
      margin: 20px 0 10px;
    }

    .button {
      display: inline-flex;
      align-items: center;
      background-color: #FF6D6D;
      color: white;
      padding: 10px 20px;
      font-size: 1em;
      border-radius: 8px;
      text-decoration: none;
      font-weight: 500;
      transition: background-color 0.3s ease;
      border: none;
      cursor: pointer;
    }

    .button:hover {
      background-color: #FF4A4A;
    }

    .button-secondary {
      background-color: #34495e;
      padding: 10px 20px;
    }

    .button-secondary:hover {
      background-color: #2c3e50;
    }

    .comment {
      border: 1px solid #ddd;
      padding: 15px;
      border-radius: 8px;
      margin-bottom: 15px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .comment p {
      font-size: 1.1em;
      margin: 5px 0;
      color: #555;
    }

    .comment-info {
      font-size: 0.9em;
      color: #666;
    }

    /* Estilo para el formulario de nuevo comentario */
    form {
      margin-top: 20px;
      display: flex;
      flex-direction: column;
    }

    label {
      font-weight: bold;
      color: #555;
      margin-bottom: 5px;
    }

    textarea {
      width: 90%;
      max-width: 100%;
      height: 80px;
      padding: 10px;
      font-size: 1em;
      border: 1px solid #ccc;
      border-radius: 5px;
      resize: none; /* Deshabilitar redimensionamiento */
      margin-bottom: 15px;
    }

    .submit-button {
      width: 150px;
      align-self: flex-start;
      background-color: #FF6D6D;
      color: white;
      padding: 10px 20px;
      font-size: 1em;
      border-radius: 8px;
      border: none;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .submit-button:hover {
      background-color: #FF4A4A;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="header">
    <h2>Comentarios para la Reseña de <%= ((Resena) request.getAttribute("resena")).getRestaurante() %></h2>
    <a href="${pageContext.request.contextPath}/ResenaSv" class="button button-secondary">Volver a reseñas</a>
  </div>

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
  <div class="comment">
    <p><%= comentario.getContenido() %></p>
    <p class="comment-info">Por: <%= comentario.getUsuario().getUsername() %> el <%= comentario.getFechaCreacion() %></p>
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
    <textarea id="contenido" name="contenido" required></textarea>
    <button type="submit" class="submit-button">Publicar Comentario</button>
  </form>
</div>
</body>
</html>
