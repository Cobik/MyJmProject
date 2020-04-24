package servlets;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/admin/edit")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        User user = UserService.getInstance().getUserById(id);

        req.setAttribute("name", user.getName());
        req.setAttribute("username", user.getUsername());
        req.setAttribute("password", user.getPassword());
        req.setAttribute("role", user.getRole());
        RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        User user = new User(name, username, password, role);

        try {
            UserService.getInstance().updateUser(user, id);
            resp.sendRedirect("/admin/main");

            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
