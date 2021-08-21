package ru.job4j.cinema.repository.jdbc;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import ru.job4j.cinema.model.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketListHandler extends BeanListHandler<Ticket> {

    public TicketListHandler() {
        super(Ticket.class, new BasicRowProcessor(new BeanProcessor(mapColumnsToFields())));
    }

    public static Map<String, String> mapColumnsToFields() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("session_id", "sessionId");
        columnsToFieldsMap.put("account_id", "accountId");
        return columnsToFieldsMap;
    }
}
