package servlets;

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

@WebServlet("/admin/signUp")
public class SignUpServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/signup.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        HttpSession session = req.getSession();

        User user = new User(name, username, password, role);

        try {
            if (UserServiceImpl.getInstance().validateUser(user)) {
                resp.sendRedirect("login.jsp");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                UserServiceImpl.getInstance().addUser(user);

                session.setAttribute("role", role);
                session.setAttribute("username",username);
                resp.sendRedirect("/admin/main");
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
