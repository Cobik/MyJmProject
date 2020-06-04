package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Cache-control","no-cache");
        resp.setHeader("Cache-control","no-store");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Expires","0");
        resp.setDateHeader("Expires", -1);

        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect("/login");
    }
}
