package by.broker.http.servlet;

import by.broker.http.dto.CreateClientDto;
import by.broker.http.entity.Client;
import by.broker.http.exception.ValidationException;
import by.broker.http.service.ClientService;
import by.broker.http.util.ServletUtil;
import by.broker.http.util.UrlPath;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.broker.http.util.UrlPath.*;

@WebServlet(REGISTRATION)
public class RegisterServlet extends HttpServlet {

    private final ClientService clientService=ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ServletUtil.getFullPath("registration"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        CreateClientDto clientDto = CreateClientDto.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .fatherName(req.getParameter("fatherName"))
                .citizenship(req.getParameter("citizenship"))
                .passportCode(req.getParameter("passportCode"))
                .birthday(req.getParameter("birthday"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        try {
                clientService.save(clientDto);
            resp.sendRedirect(LOGIN);
            } catch (ValidationException e){
            req.setAttribute("errors",e.getErrors());
            doGet(req,resp);
        }

        //resp.sendRedirect(LOGIN);

    }
}
