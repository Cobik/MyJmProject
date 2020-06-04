package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
            RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
            requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user = new User(username, password);

        String role = UserServiceImpl.getInstance().getRoleByUsername(username);

        try {
            if (UserServiceImpl.getInstance().signInUser(user)) {
                resp.sendRedirect("/admin/main");
                session.setAttribute("role", role);
                session.setAttribute("username", username);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else if (UserServiceImpl.getInstance().validateUser(user)) {
                resp.getWriter().println("Wrong username or password");
                session.invalidate();
            } else {
                resp.sendRedirect("/admin/signUp");
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
