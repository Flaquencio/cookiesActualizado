<%--
  Created by IntelliJ IDEA.
  User: israe
  Date: 4/6/2025
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.israel.cookies.services.models.UsuarioPermiso" %>
<%
    UsuarioPermiso permiso = (UsuarioPermiso) request.getAttribute("permiso");
    if (permiso == null) {
        permiso = new UsuarioPermiso();
    }
%>
<html>
<head>
    <title>Formulario Usuario-Permiso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light text-dark">
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-sm border-0">
                <div class="card-body">
                    <h2 class="text-center mb-4">Formulario Usuario-Permiso</h2>

                    <form action="<%=request.getContextPath()%>/usuarioPermiso/form" method="post">
                        <input type="hidden" name="idUsuarioPermiso" value="<%= permiso.getIdUsuarioPermiso() %>">

                        <div class="mb-3">
                            <label for="idUsuario" class="form-label">ID Usuario:</label>
                            <input type="number" id="idUsuario" name="idUsuario" class="form-control"
                                   value="<%= permiso.getIdUsuario() != null ? permiso.getIdUsuario() : "" %>" required>
                        </div>

                        <div class="mb-3">
                            <label for="idPermiso" class="form-label">ID Permiso:</label>
                            <input type="number" id="idPermiso" name="idPermiso" class="form-control"
                                   value="<%= permiso.getIdPermiso() != null ? permiso.getIdPermiso() : "" %>" required>
                        </div>

                        <div class="text-end">
                            <input type="submit" class="btn btn-secondary"
                                   value="<%=(permiso.getIdUsuarioPermiso()!= null && permiso.getIdUsuarioPermiso()>0) ? "Editar" : "Crear"%>">
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