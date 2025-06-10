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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light text-dark">

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-sm border-0">
                <div class="card-body">
                    <h2 class="text-center mb-4">Formulario Categoría</h2>

                    <form action="<%=request.getContextPath()%>/categoria/form" method="post">
                        <input type="hidden" name="idCategoria" value="<%= categorias.getIdCategoria() %>">

                        <div class="mb-3">
                            <label for="nombre" class="form-label">Ingrese el nombre de categoría:</label>
                            <input type="text" id="nombre" name="nombre" class="form-control"
                                   value="<%= categorias.getNombre() != null ? categorias.getNombre() : "" %>" required>
                        </div>

                        <div class="mb-3">
                            <label for="descripcion" class="form-label">Ingrese la descripción:</label>
                            <input type="text" id="descripcion" name="descripcion" class="form-control"
                                   value="<%= categorias.getDescripcion() != null ? categorias.getDescripcion() : "" %>" required>
                        </div>

                        <div class="text-end">
                            <input type="submit" class="btn btn-secondary" value="<%=(categorias.getIdCategoria()!= null && categorias.getIdCategoria()>0)?"Editar":"Crear"%>">
                        </div>
                    </form>

                    <%--traemos la alerta --%>
                    <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger">
                        <%= request.getAttribute("error") %>
                    </div>
                    <% } %>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>