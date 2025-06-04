<%--
  Created by IntelliJ IDEA.
  User: israe
  Date: 29/5/2025
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.israel.cookies.services.models.Categorias" %>

<%
    Categorias categorias = (Categorias) request.getAttribute("categorias");
%>
<html>
<head>
    <title>Formulario Categorías</title>
</head>
<body>
<h1>Formulario Categoría</h1>

<form action="<%=request.getContextPath()%>/categoria/form" method="post">
    <div>
        <label for="nombre">Ingrese el nombre de categoría</label>
        <div>
            <input type="hidden" name="idCategoria" value="<%=categorias.getIdCategoria()%>">
            <input type="text" id="nombre" name="nombre" value="<%=categorias.getNombre() != null ? categorias.getNombre() : ""%>">
        </div>
    </div>

    <div>
        <label for="descripcion">Ingrese la descripción</label>
        <div>
            <input type="text" id="descripcion" name="descripcion" value="<%=categorias.getDescripcion() != null ? categorias.getDescripcion() : ""%>">
        </div>
    </div>

    <div>
        <input type="submit" value="ENVIAR">
    </div>
</form>

</body>
</html>