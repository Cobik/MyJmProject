package servlets;

import model.User;

import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class SignInServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user = new User(username, password);

        String role = UserService.getInstance().getRoleByUsername(username);

        try {
            if (UserService.getInstance().signInUser(user)) {
                resp.sendRedirect("/admin/main");
                session.setAttribute("role", role);
                session.setAttribute("username", username);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else if (UserService.getInstance().validateUser(user)) {
                resp.getWriter().println("Wrong username or password");
            } else {
                resp.sendRedirect("signup.jsp");
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
