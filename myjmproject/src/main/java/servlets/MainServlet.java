package servlets;

import exception.DBException;
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
import java.util.List;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
        UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            userService.createTable();
            List<User> userList = userService.getAllUsers();
            req.setAttribute("usersFromServer", userList);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/mainpage.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException | DBException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(name,username,password);

        try {
            userService.updateUser(user,id);
            resp.sendRedirect("mainpage.jsp");
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
