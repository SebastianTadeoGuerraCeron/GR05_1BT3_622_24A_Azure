<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 10/11/2024
  Time: 06:29 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Nueva Promoción</title>
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
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            width: 100%;
            max-width: 600px;
            text-align: center;
        }

        h2 {
            color: #FF6D6D;
            font-size: 2.5em;
            font-weight: 700;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }

        .form-group label {
            font-weight: bold;
            color: #333;
            display: block;
            margin-bottom: 5px;
        }

        .form-group input, .form-group select, .form-group textarea {
            width: 100%;
            padding: 10px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }

        .form-group textarea {
            resize: vertical;
            min-height: 80px; /* Mantiene la altura fija */
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
            margin-top: 20px;
            margin-bottom: 20px; /* Mayor separación entre los botones */
        }

        .button:hover {
            background-color: #FF4A4A;
        }

        .button-secondary {
            background-color: #34495e;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 100%;
        }

        .button-secondary:hover {
            background-color: #2c3e50;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Crear Nueva Promoción</h2>
    <form action="${pageContext.request.contextPath}/PromocionSv" method="post">
        <div class="form-group">
            <label for="titulo">Título</label>
            <input type="text" id="titulo" name="titulo" placeholder="Ingresa el título de la promoción" required>
        </div>

        <div class="form-group">
            <label for="restaurante">Restaurante</label>
            <input type="text" id="restaurante" name="restaurante" placeholder="Ingresa el nombre del restaurante" required>
        </div>

        <div class="form-group">
            <label for="ubicacion">Ubicación</label>
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
        </div>

        <div class="form-group">
            <label for="tipoPromocion">Tipo de Promoción</label>
            <select id="tipoPromocion" name="tipoPromocion" required>
                <option value="Cortesia">Cortesía</option>
                <option value="Porcentaje de descuento">Porcentaje de descuento</option>
                <option value="Descuento por método de pago">Descuento por método de pago</option>
                <option value="Descuento por evento especial">Descuento por evento especial</option>
            </select>
        </div>

        <div class="form-group">
            <label for="condiciones">Condiciones</label>
            <textarea id="condiciones" name="condiciones" placeholder="Especifica las condiciones de la promoción"></textarea>
        </div>

        <!-- Botones de acción -->
        <button type="submit" class="button">Crear Promoción</button>
        <a href="${pageContext.request.contextPath}/ListaPromocion.jsp" class="button-secondary">Volver a lista de promociones</a>
    </form>
</div>
</body>
</html>