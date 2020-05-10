package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/login")
public class LoginFilterServlet implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);


        try {
            if (session == null){
                filterChain.doFilter(request,response);
            } else {
               request.getRequestDispatcher("/login").forward(request,response);
            }

        } catch (NullPointerException n) {
            n.printStackTrace();
            response.getWriter().println("Error in login");
        }
    }

    @Override
    public void destroy() {

    }
}
