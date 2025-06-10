<%--
  Created by IntelliJ IDEA.
  User: israe
  Date: 4/6/2025
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.israel.cookies.services.models.Usuario" %>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    if (usuario == null) {
        usuario = new Usuario();
    }
%>
<html>
<head>
    <title>Formulario Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light text-dark">
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-sm border-0">
                <div class="card-body">
                    <h2 class="text-center mb-4">Formulario Usuario</h2>

                    <form action="<%=request.getContextPath()%>/usuario/form" method="post">
                        <input type="hidden" name="idUsuario" value="<%= usuario.getIdUsuario() %>">

                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" class="form-control"
                                   value="<%= usuario.getNombre() != null ? usuario.getNombre() : "" %>" required>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Correo electrónico:</label>
                            <input type="email" id="email" name="email" class="form-control"
                                   value="<%= usuario.getEmail() != null ? usuario.getEmail() : "" %>" required>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña:</label>
                            <input type="password" id="password" name="password" class="form-control" required>
                        </div>

                        <div class="text-end">
                            <input type="submit" class="btn btn-secondary"
                                   value="<%=(usuario.getIdUsuario()!= null && usuario.getIdUsuario()>0) ? "Editar" : "Crear"%>">
                        </div>
                    </form>

                    <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger mt-3">
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