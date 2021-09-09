package by.broker.http.servlet;

import by.broker.http.service.StorageStockService;
import by.broker.http.util.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.broker.http.util.UrlPath.*;

@WebServlet(STOCKS)
public class ListStorageStockServlet extends HttpServlet {

    private final StorageStockService storageStockService =StorageStockService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("stocks", storageStockService.findAll());
        req.getRequestDispatcher(ServletUtil.getFullPath("stocks"))
                .forward(req,resp);
    }
}
