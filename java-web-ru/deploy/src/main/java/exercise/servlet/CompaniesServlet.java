package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // BEGIN
        String companies = null;
        String parameter = request.getParameter("search");
        if (parameter == null) {
            companies = String.join("\n", getCompanies());
        } else {
            companies = getCompanies().stream()
                    .filter(company -> company.contains(parameter))
                    .collect(Collectors.joining("\n"));
        }
        companies = companies.length() == 0 ? "Companies not found" : companies;
        PrintWriter out = response.getWriter();
        out.println(companies);
        // END
    }
}
