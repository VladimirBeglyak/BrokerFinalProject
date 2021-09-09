package by.broker.http.servlet;

import by.broker.http.dto.ClientDto;
import by.broker.http.service.ClientService;
import by.broker.http.util.ServletUtil;
import by.broker.http.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

import static by.broker.http.util.UrlPath.*;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    private final ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ServletUtil.getFullPath("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");


        clientService.login(req.getParameter("email"), req.getParameter("password"))
                .ifPresentOrElse(
                        clientDto ->
                                onLoginSuccess(clientDto, req, resp),
                                () -> onLoginFail(req, resp)

                );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&email=" + req.getParameter("email"));
    }


    @SneakyThrows
    private void onLoginSuccess(ClientDto clientDto, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("client", clientDto);
        response.sendRedirect("/private");
    }


}
