import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class NameFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = request.getSession();

        if("/form".equals(request.getRequestURI())) {
            super.doFilter(request, response, chain);
            return;
        }

        if (session.getAttribute("name") == null || session.getAttribute("age") == null) {
            response.sendRedirect("/form");
            return;
        }

        super.doFilter(request, response, chain);
    }

}
