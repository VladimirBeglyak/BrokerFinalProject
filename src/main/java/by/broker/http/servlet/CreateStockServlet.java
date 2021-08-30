package by.broker.http.servlet;

import by.broker.http.dto.StockDto;
import by.broker.http.entity.Currency;
import by.broker.http.entity.Stock;
import by.broker.http.service.StockService;
import by.broker.http.util.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-stock")
public class CreateStockServlet extends HttpServlet {

    private final StockService stockService=StockService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currency", Currency.values());
        req.getRequestDispatcher(ServletUtil.getFullPath("add-stock"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Stock save = stockService.save(
                StockDto.builder()
                        .name(req.getParameter("name"))
                        .ticker(req.getParameter("ticker"))
                        .cost(req.getParameter("cost"))
                        .dividend(req.getParameter("dividend"))
                        .currency(req.getParameter("currency"))
                        .build()
        );

        System.out.println(save);

        resp.sendRedirect("/stocks");
    }
}
