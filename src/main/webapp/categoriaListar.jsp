<%--
  Created by IntelliJ IDEA.
  User: israe
  Date: 28/5/2025
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, org.israel.cookies.services.models.*"%>

<%
    List<Categorias> categorias = (List<Categorias>) request.getAttribute("categorias");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>

<html>
<head>
    <title>Listado Categoria</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container py-4">

    <% if (username.isPresent()) { %>
    <div class="alert alert-secondary">
        <strong>Hola, <%= username.get() %></strong> bienvenido
    </div>
    <div class="mb-3">
        <a href="${pageContext.request.contextPath}/categoria/form" class="btn btn-outline-primary">
            Añadir categoría
        </a>
    </div>
    <% } %>

    <h2 class="mb-4">Listado Categoría</h2>

    <div class="table-responsive">
        <table class="table table-bordered table-hover table-sm">
            <thead class="table-secondary">
            <tr>
                <th>ID CATEGORÍA</th>
                <th>NOMBRE</th>
                <th>DESCRIPCIÓN</th>
                <th>CONDICIÓN</th>
                <th>ACCIÓN</th>
            </tr>


            </thead>

            <%
                for (Categorias cate : categorias){%>
            <tbody>
            <td><%=cate.getIdCategoria()%></td>
            <td><%=cate.getNombre()%></td>
            <td><%=cate.getDescripcion()%></td>
            <td><%=cate.getCondicion()%></td>
            <%if(username.isPresent())%>
            <td>
                <a href="<%=request.getContextPath()%>/categoria/form?id=<%=cate.getIdCategoria()%>">Editar</a>
            </td>

            </tbody>

            <%}%>

        </table>
    </div>

</div>

<script src="./js/bootstrap.bundle.min.js"></script>
</body>
</html>
