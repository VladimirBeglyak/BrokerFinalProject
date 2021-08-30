package by.broker.http.servlet;

import by.broker.http.service.StockService;
import by.broker.http.util.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/stocks")
public class ListStockServlet extends HttpServlet {

    private final StockService stockService=StockService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("stocks",stockService.findAll());
        req.getRequestDispatcher(ServletUtil.getFullPath("stocks"))
                .forward(req,resp);
    }
}
