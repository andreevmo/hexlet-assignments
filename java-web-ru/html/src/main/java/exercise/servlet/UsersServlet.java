package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.User;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        String usersString = Files.readString(Path.of("src/main/resources/users.json"));
        List<User> users = new ObjectMapper().readValue(usersString, new TypeReference<ArrayList<User>>() {});
        return users;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<User> users = getUsers();
        StringBuilder usersTable = new StringBuilder("<table>");
        for (User user : users) {
            usersTable.append("<tr>")
                    .append("<td>").append(user.getId()).append("</td>")
                    .append("<td>")
                    .append("<a href=\"/users/" + user.getId() + "\">" +
                            user.getFirstName() + " " + user.getLastName() +
                            "</a>")
                    .append("</td>")
                    .append("</tr>");
        }
        usersTable.append("</table>");

        PrintWriter answer = response.getWriter();
        answer.println(usersTable);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<User> users = getUsers();
        User user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findAny()
                .orElse(null);
        PrintWriter answer = response.getWriter();
        if (user == null) {
            response.sendError(404);
        } else {
            StringBuilder usersTable = new StringBuilder("<table>");
            usersTable.append("<tr>")
                    .append("<td>").append(user.getId()).append("</td>")
                    .append("<td>").append(user.getFirstName()).append("</td>")
                    .append("<td>").append(user.getLastName()).append("</td>")
                    .append("<td>").append(user.getEmail()).append("</td>")
                    .append("</tr>");
            usersTable.append("</table>");
            answer.println(usersTable);
        }
        // END
    }
}
