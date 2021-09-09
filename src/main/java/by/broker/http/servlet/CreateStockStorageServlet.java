package by.broker.http.servlet;

import by.broker.http.dao.CurrencyDao;
import by.broker.http.dto.StorageStockDto;
import by.broker.http.service.StorageStockService;
import by.broker.http.util.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.broker.http.util.UrlPath.*;

@WebServlet(ADD_STOCK)
public class CreateStockStorageServlet extends HttpServlet {

    private final StorageStockService storageStockService=StorageStockService.getInstance();
    private final CurrencyDao currencyDao=CurrencyDao.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currency", currencyDao.findAll());
        req.getRequestDispatcher(ServletUtil.getFullPath("add-stock-to-storage"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        storageStockService.save(
                StorageStockDto.builder()
                        .name(req.getParameter("name"))
                        .ticker(req.getParameter("ticker"))
                        .amount(req.getParameter("amount"))
                        .costOneStock(req.getParameter("costOneStock"))
                        .country(req.getParameter("country"))
                        .dividend(req.getParameter("dividend"))
                        .currency(req.getParameter("currency"))
                        .build()
        );

        resp.sendRedirect(STOCKS);
    }
}
