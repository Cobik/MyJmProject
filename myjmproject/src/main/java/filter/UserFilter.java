package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/user")
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        try {
            if (session.getAttribute("role").equals("user")) {
                filterChain.doFilter(request, response);
            } else if (session.getAttribute("role").equals("admin")) {
                filterChain.doFilter(request, response);
            }
        } catch (NullPointerException n) {
            response.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
