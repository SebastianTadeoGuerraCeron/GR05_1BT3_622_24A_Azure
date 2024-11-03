<%--
  Created by IntelliJ IDEA.
  User: Anthony
  Date: 31/10/2024
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Bienvenido a Las HueQuitas</title>
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
            min-height: 100vh;
        }

        .container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            width: 100%;
            max-width: 500px;
            text-align: center;
        }

        h2 {
            color: #FF6D6D;
            font-size: 2.5em;
            font-weight: 700;
            margin-bottom: 10px;
        }

        p {
            font-size: 1.1em;
            color: #555;
            margin-bottom: 20px;
        }

        .nav-links {
            display: flex;
            flex-direction: column;
            gap: 15px;
            margin-top: 20px;
        }

        .nav-links a {
            background-color: #FF6D6D;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            font-weight: bold;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .nav-links a:hover {
            background-color: #FF4A4A;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Bienvenido a Las HueQuitas</h2>
    <p>¡Explora, comenta y comparte reseñas gastronómicas!</p>

    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/ResenaSv">Ir a Reseñas</a>
        <a href="${pageContext.request.contextPath}/AnuncioSv">Ir a Anuncios</a>
        <a href="${pageContext.request.contextPath}/LogoutSv">Cerrar sesión</a>
    </div>
</div>
</body>
</html>


