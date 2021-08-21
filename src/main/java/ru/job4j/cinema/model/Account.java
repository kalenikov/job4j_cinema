package ru.job4j.cinema.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class Account {
    private int id;
    private String username;
    private String email;
    private String phone;

    public Account(String username, String phone) {
        this.username = username;
        this.phone = phone;
    }
}
