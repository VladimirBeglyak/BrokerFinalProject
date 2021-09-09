package by.broker.http.servlet;


import by.broker.http.dao.ClientStockDao;
import by.broker.http.entity.ClientStock;
import by.broker.http.util.ServletUtil;
import by.broker.http.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.broker.http.util.UrlPath.*;

@WebServlet(BUY)
public class BuyStockServlet extends HttpServlet {

    private final ClientStockDao clientStockDao = ClientStockDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ServletUtil.getFullPath("buy"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ClientStock clientStock = clientStockDao.findById(1L).get();
        Long oldAmount = clientStock.getAmount();
        clientStock.setAmount(oldAmount+Long.parseLong(req.getParameter("amount")));

//        long id = Long.parseLong(req.getParameter("id"));
//        System.out.println(id);


//        long amount = Long.parseLong(req.getParameter("amount"));
//        System.out.println(amount);

        clientStockDao.update(clientStock);
        req.getRequestDispatcher(ServletUtil.getFullPath("private-cabinet"))
                .forward(req,resp);
    }
}
