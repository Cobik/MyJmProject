package servlets;

import exception.DBException;
import model.User;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/main")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserServiceImpl.getInstance().createTable();
            List<User> userList = UserServiceImpl.getInstance().getAllUsers();
            req.setAttribute("usersFromServer", userList);
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/mainpage.jsp");
            dispatcher.forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
