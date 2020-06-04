package filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class RoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        try {
            String role = (String) session.getAttribute("role");

            if (role.equals("admin")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (role.equals("user")) {
                response.sendRedirect("/user");
            } else {
                response.getWriter().println("Error");
            }
        } catch (NullPointerException n) {
            response.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
