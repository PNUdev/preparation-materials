package com.ihorpolataiko;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class SomeServlet extends HttpServlet {

    @Autowired
    private SomeService someService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ApplicationContextHolder.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("data", someService.getSomeString());

        req.getRequestDispatcher("/WEB-INF/some.jsp").forward(req, resp);
    }
}
