package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Cache-control","no-cache");
        resp.setHeader("Cache-control","no-store");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Expires","0");
        resp.setDateHeader("Expires", -1);
        RequestDispatcher requestDispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/user.jsp");
        requestDispatcher.forward(req, resp);
    }
}
