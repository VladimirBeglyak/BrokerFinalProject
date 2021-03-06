package by.broker.http.servlet;

import by.broker.http.service.StorageStockService;
import by.broker.http.util.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/stock")
public class StockFullInfoServlet extends HttpServlet {

    private final StorageStockService storageStockService =StorageStockService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("stock", storageStockService.findById(id));
        req.getRequestDispatcher(ServletUtil.getFullPath("stock-full-info"))
                .forward(req,resp);
    }
}
