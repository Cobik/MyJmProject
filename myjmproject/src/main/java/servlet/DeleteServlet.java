package servlet;


import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        try {
            if (UserServiceImpl.getInstance().deleteUser(Long.parseLong(id))) {
                resp.sendRedirect("/admin/main");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.getWriter().println("Have a problem");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}