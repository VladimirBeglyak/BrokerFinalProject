package by.broker.http.servlet;

import by.broker.http.util.ServletUtil;
import by.broker.http.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.broker.http.util.UrlPath.*;

@WebServlet(PRIVATE_CABINET)
public class PrivateCabinetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ServletUtil.getFullPath("private-cabinet"))
                .forward(req,resp);
    }
}
