package ru.job4j.cinema.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cinema.model.Account;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.model.TicketDTO;
import ru.job4j.cinema.service.AccountService;
import ru.job4j.cinema.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


public class HallController extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json; charset=utf-8");
        String sessionId = req.getParameter("sessionId");
        TicketService ts = TicketService.getInstance();
        List<TicketDTO> tickets = ts.getPurchasedPlaces(Integer.valueOf(sessionId));
        OutputStream output = resp.getOutputStream();
        output.write(GSON.toJson(tickets).getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");

        AccountService as = AccountService.getInstance();
        Optional<Account> account = as.getByPhone(phone);
        if (account.isEmpty()) {
            account = as.save(new Account(username, phone));
        }

        TicketService ts = TicketService.getInstance();
        Optional<Ticket> ticket = ts.save(new Ticket(
                Integer.parseInt(req.getParameter("sessionId")),
                Integer.parseInt(req.getParameter("row")),
                Integer.parseInt(req.getParameter("cell")),
                account.get().getId()
        ));

        if (ticket.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Ticket to this session and place already purchased");
            return;
        }

        OutputStream output = resp.getOutputStream();
        output.write(GSON.toJson(ticket.get()).getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
