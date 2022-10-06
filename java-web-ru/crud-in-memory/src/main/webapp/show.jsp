<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <a href="/users">All users</a>
            <table>
                <tr>
                    <td>id: ${user.get("id")}</td>
                </tr>
                <tr>
                    <td>Full Name: ${user.get("firstName")} ${user.get("lastName")}</td>
                </tr>
                <tr>
                    <td>Email: ${user.get("email")}</td>
                </tr>
            </table>
            <a class="btn btn-primary" href='/users/delete?id=${user.get("id")}'>Delete</a>
            <!-- BEGIN -->
            <a class="btn btn-primary" href='/users/edit?id=${user.get("id")}'>Edit</a>
            <!-- END -->
        </div>
    </body>
</html>
