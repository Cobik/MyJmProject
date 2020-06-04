package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/login")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);


        try {
            if (session == null) {
                filterChain.doFilter(request, response);
            } else if (session.getAttribute("role").equals("admin")) {
                response.sendRedirect("/admin/main");
            } else if (session.getAttribute("role").equals("user")) {
                response.sendRedirect("/user");
            } else {
                response.getWriter().println("login error");
            }

        } catch (NullPointerException n) {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
