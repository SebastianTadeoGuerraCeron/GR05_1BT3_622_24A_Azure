<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Resena" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Reseñas</title>
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
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        h2 {
            color: #FF6D6D; /* Color de título */
            font-size: 3.0em;
            font-weight: 700;
            margin: 0;
            text-align: center; /* Centrar el texto */
        }

        .button {
            display: inline-flex;
            align-items: center;
            background-color: #FF6D6D; /* Color de fondo del botón principal */
            color: white;
            padding: 12px 20px;
            font-size: 1em;
            border-radius: 8px;
            text-decoration: none;
            font-weight: 500;
            transition: background-color 0.3s ease;
            border: none;
            cursor: pointer;
        }

        .button:hover {
            background-color: #FF4A4A; /* Color al hacer hover */
        }

        .button-secondary {
            background-color: #34495e; /* Color del botón secundario */
            margin-bottom: 10px;
        }

        .button-secondary:hover {
            background-color: #2c3e50; /* Hover para botón secundario */
        }

        .filter-form {
            text-align: center;
            margin: 10px 0;
            display: flex;
            align-items: center;
            gap: 10px;
            justify-content: center;
        }

        .review {
            border: 1px solid #ddd;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 15px;
            text-align: left;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .review h3 {
            color: #FF6D6D; /* Color del título de la reseña */
            font-size: 1.5em;
            margin: 0 0 5px 0;
        }

        .review p {
            font-size: 1.2em;
            margin: 5px 0;
            color: #555;
        }

        .reaction-buttons {
            margin-top: 10px;
            font-size: 0.9em;
        }

        .small-button {
            padding: 5px 12px;
            font-size: 0.9em;
            border-radius: 5px;
            transition: background-color 0.3s;
            cursor: pointer;
        }

        .small-button:hover {
            opacity: 0.8;
        }

        .icon-plus {
            font-size: 1.2em;
            margin-right: 8px;
        }
    </style>
    <script>
        // Función para mostrar el pop-up de error
        function showErrorPopup(message) {
            alert(message); // Se utiliza alert para el pop-up básico; puedes cambiarlo por un modal si deseas.
        }

        // Ejecutar el pop-up si showPopup está activado
        window.onload = function () {
            <%
                Boolean showPopup = (Boolean) request.getAttribute("showPopup");
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (showPopup != null && showPopup) {
            %>
            showErrorPopup("<%= errorMessage %>");
            <% } %>
        };
    </script>
</head>
<body>
<div class="container">
    <div class="header">
        <h2>Reseñas</h2>

        <!-- Botón para crear nueva reseña con un ícono de "+" -->
        <form action="${pageContext.request.contextPath}/FormularioResena.jsp" method="get" style="display: inline;">
            <button type="submit" class="button">
                <span class="icon-plus">+</span> Crear Nueva Reseña
            </button>
        </form>
    </div>

    <!-- Botón para cerrar sesión -->
    <a href="${pageContext.request.contextPath}/LogoutSv" class="button button-secondary">Cerrar sesión</a>

    <!-- Botón para regresar a Home, debajo del botón de Cerrar sesión -->
    <a href="${pageContext.request.contextPath}/Home.jsp" class="button button-secondary">Regresar a Home</a>

    <!-- Filtro por tipo de comida -->
    <form action="${pageContext.request.contextPath}/ResenaSv" method="get" class="filter-form">
        <label for="tipoComida" style="font-weight: bold; color: #555;">Filtrar por tipo de comida:</label>
        <select name="tipoComida" id="tipoComida" style="padding: 8px 12px; border: 1px solid #ccc; border-radius: 5px; font-size: 14px; color: #333; background-color: #f5f5f5;">
            <option value="Todas">Todas</option>
            <option value="Extranjera">Extranjera</option>
            <option value="Snacks">Snacks</option>
            <option value="Tradicional">Tradicional</option>
        </select>
        <button type="submit" class="button small-button">Filtrar</button>
    </form>

    <!-- Mostrar lista de reseñas -->
    <%
        List<Resena> resenas = (List<Resena>) request.getAttribute("resenas");
        if (resenas == null || resenas.isEmpty()) {
    %>
    <p>No hay reseñas disponibles en este momento</p>
    <%
    } else {
        for (Resena resena : resenas) {
    %>
    <div class="review">
        <h3><%= resena.getRestaurante() %> - <%= resena.getTipoComida() %></h3>
        <p><%= resena.getDescripcion() %></p>
        <p style="font-size: 0.9em; color: #666;">Creado por: <%= resena.getUsuario().getUsername() %> el <%= resena.getFechaCreacion() %></p>
        <p style="font-size: 0.9em; color: #666;">
            Likes: <span id="likeCount-<%= resena.getId() %>"><%= resena.getReacciones().stream().filter(r -> r.getTipo().equals("like")).count() %></span>
            | Dislikes: <span id="dislikeCount-<%= resena.getId() %>"><%= resena.getReacciones().stream().filter(r -> r.getTipo().equals("dislike")).count() %></span>
        </p>

        <div class="reaction-buttons">
            <!-- Botón para dar Like -->
            <form action="${pageContext.request.contextPath}/ReaccionSv" method="post" style="display:inline;">
                <input type="hidden" name="tipo" value="like">
                <input type="hidden" name="resenaId" value="<%= resena.getId() %>">
                <button type="submit" class="button small-button">👍 Like</button>
            </form>

            <!-- Botón para dar Dislike -->
            <form action="${pageContext.request.contextPath}/ReaccionSv" method="post" style="display:inline;">
                <input type="hidden" name="tipo" value="dislike">
                <input type="hidden" name="resenaId" value="<%= resena.getId() %>">
                <button type="submit" class="button button-secondary small-button">👎 Dislike</button>
            </form>

            <a href="${pageContext.request.contextPath}/ComentarioSv?resenaId=<%= resena.getId() %>" class="button small-button">Ver comentarios</a>
        </div>
    </div>
    <%
            }
        }
    %>
</div>
</body>
</html>
