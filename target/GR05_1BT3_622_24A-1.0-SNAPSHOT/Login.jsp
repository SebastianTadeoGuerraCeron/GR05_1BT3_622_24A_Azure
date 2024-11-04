<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Iniciar Sesión</title>
  <style>
    /* Importar Google Fonts */
    @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap');

    /* Estilos básicos */
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
    }

    body {
      font-family: 'Roboto', sans-serif;
      background: linear-gradient(to right, #FF914D, #FF6D6D);
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      color: #333;
    }

    .login-container {
      background-color: #ffffff;
      padding: 40px;
      border-radius: 10px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
      text-align: center;
      width: 100%;
      max-width: 400px;
    }

    .title {
      font-size: 2.5em;
      color: #FF6D6D;
      font-weight: bold;
      margin-bottom: 5px;
    }

    h2 {
      color: #555;
      font-size: 1.8em;
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
    input[type="password"] {
      width: 100%;
      padding: 12px;
      margin-top: 8px;
      margin-bottom: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-size: 1em;
      box-sizing: border-box;
      transition: border-color 0.3s ease, box-shadow 0.3s ease;
      height: 40px;
    }

    input[type="email"]:focus,
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

    .register-link {
      margin-top: 20px;
      font-size: 0.9em;
      color: #555;
    }

    .register-link a {
      color: #FF6D6D;
      text-decoration: none;
      font-weight: bold;
    }

    .register-link a:hover {
      text-decoration: underline;
    }

    .error-message {
      color: #FF4A4A;
      margin-top: 10px;
      font-size: 0.9em;
    }
  </style>

  <script>
    // Mostrar el mensaje de éxito si el parámetro "success" o "logout" está presente en la URL
    window.onload = function() {
      const urlParams = new URLSearchParams(window.location.search);
      if (urlParams.get('success') === 'true') {
        alert('¡Registro exitoso! Por favor, inicia sesión.');
      }
      if (urlParams.get('logout') === 'true') {
        alert('¡Cierre de sesión exitoso!');
      }
    };
  </script>
</head>
<body>
<div class="login-container">
  <div class="title">Las Huequitas</div>
  <h2>Iniciar Sesión</h2>
  <form action="${pageContext.request.contextPath}/LoginSv" method="POST" autocomplete="off">
    <label for="email">Correo electrónico:</label>
    <input type="email" id="email" name="email" required>

    <label for="password">Contraseña:</label>
    <input type="password" id="password" name="password" required>

    <button type="submit" class="button">Iniciar sesión</button>
  </form>

  <!-- Mostrar mensaje de error si existe -->
  <c:if test="${not empty errorMessage}">
    <p class="error-message">${errorMessage}</p>
  </c:if>

  <p class="register-link">¿No tienes una cuenta? <a href="${pageContext.request.contextPath}/Registro.jsp">Registrarse</a></p>
</div>
</body>
</html>
