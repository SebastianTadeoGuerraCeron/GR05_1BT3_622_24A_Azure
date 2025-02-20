<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Registro</title>
  <style>
    /* Importar Google Fonts */
    @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap');

    body {
      font-family: 'Roboto', sans-serif;
      background: linear-gradient(to right, #FF914D, #FF6D6D);
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
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
      width: 100%;
      max-width: 400px;
      text-align: center;
    }

    h2 {
      color: #FF6D6D;
      font-size: 2em;
      font-weight: 700;
      margin-bottom: 20px;
    }

    label {
      display: block;
      font-weight: bold;
      color: #555;
      margin-top: 10px;
      text-align: left;
    }

    input[type="email"],
    input[type="text"],
    input[type="password"] {
      width: 90%;
      padding: 12px;
      margin-top: 8px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-size: 1em;
      transition: border-color 0.3s ease;
    }

    input[type="email"]:focus,
    input[type="text"]:focus,
    input[type="password"]:focus {
      border-color: #FF6D6D;
      outline: none;
    }

    .button {
      background-color: #FF6D6D;
      color: white;
      padding: 12px 20px;
      border: none;
      border-radius: 5px;
      font-size: 1em;
      font-weight: bold;
      cursor: pointer;
      transition: background-color 0.3s ease;
      width: 100%;
      margin-top: 10px;
    }

    .button:hover {
      background-color: #FF4A4A;
    }

    .login-link {
      margin-top: 20px;
      font-size: 0.9em;
      color: #555;
    }

    .login-link a {
      color: #FF6D6D;
      text-decoration: none;
      font-weight: bold;
    }

    .login-link a:hover {
      text-decoration: underline;
    }

  </style>
  <script>
    // Mostrar el mensaje de éxito si el parámetro "success" está presente en la URL
    window.onload = function() {
      const urlParams = new URLSearchParams(window.location.search);
      if (urlParams.get('success') === 'true') {
        alert('¡Registro exitoso! Bienvenido.');
      }
    };
  </script>
</head>
<body>
<div class="container">
  <h2>Registro de Usuario</h2>
  <form action="${pageContext.request.contextPath}/RegistroSv" method="post" autocomplete="off">
    <label for="email">Correo electrónico:</label>
    <input type="email" id="email" name="email" required>

    <label for="username">Nombre de usuario:</label>
    <input type="text" id="username" name="username" required>

    <label for="password">Contraseña:</label>
    <input type="password" id="password" name="password" required>

    <button type="submit" class="button">Registrarse</button>
  </form>
  <p class="login-link">¿Ya tienes una cuenta? <a href="${pageContext.request.contextPath}/Login.jsp">Iniciar sesión</a></p>
</div>
</body>
</html>
