import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/my")
public class MyServlet extends HttpServlet {

    private SmartService smartService = new SmartService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//

        String somethingSmart = smartService.doSomethingSmart();

        req.setAttribute("myAttr", somethingSmart);

        req.getRequestDispatcher("/WEB-INF/noaccess.jsp").forward(req, resp);
    }
}
