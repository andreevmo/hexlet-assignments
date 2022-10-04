<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!-- Устанавливаем тип содержимого и кодировку -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User</title>
        <!-- Подключаем стили Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <body>
        <form action="/users/delete" method="GET">
            <table>
                <tr>
                    <td>${user.get("id")}</td>
                    <td>${user.get("firstName")}</td>
                    <td>${user.get("lastName")}</td>
                    <td>${user.get("email")}</td>
                    <td>
                        <input type=hidden name="id" value=${user.get("id")} />
                        <input type=submit value="Delete user" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
<!-- END -->
