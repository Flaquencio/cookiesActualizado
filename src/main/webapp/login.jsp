<%--
  Created by IntelliJ IDEA.
  User: israe
  Date: 16/5/2025
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>

<%--<%  %> eso se le conoce como taks y es el encargado de introducir codigo en formato java
dentro de un formato html--%>

<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
</head>
<body class="bg-light text-dark">

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card border-0 shadow-sm bg-white">
                <div class="card-body">
                    <h2 class="text-center mb-4">Login de usuario</h2>

                    <form action="/cookies/login" method="post">
                        <div class="mb-3">
                            <label for="username" class="form-label">Nombre de usuario:</label>
                            <input type="text" class="form-control" name="username" id="username" required>
                        </div>

                        <div class="mb-3">
                            <label for="pass" class="form-label">Password:</label>
                            <input type="password" class="form-control" name="password" id="pass" required>
                        </div>

                        <div class="text-end">
                            <input type="submit" class="btn btn-secondary" value="Enviar">
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="./js/bootstrap.bundle.min.js"></script>
</body>
</html>