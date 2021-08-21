CREATE TABLE account
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR NOT NULL,
    email    VARCHAR NOT NULL UNIQUE,
    phone    VARCHAR NOT NULL UNIQUE
);

CREATE TABLE ticket
(
    id         SERIAL PRIMARY KEY,
    session_id INT NOT NULL,
    row        INT NOT NULL,
    cell       INT NOT NULL,
    account_id INT NOT NULL REFERENCES account (id)
);

ALTER TABLE ticket
    ADD CONSTRAINT no_duplicate_tickets UNIQUE (session_id, row, cell);

insert into account(username, email, phone)
values ('name1', 'email1', 'phone1');
insert into account(username, email, phone)
values ('name2', 'email2', 'phone2');
insert into account(username, email, phone)
values ('name3', 'email3', 'phone3');

insert into ticket(session_id, row, cell, account_id)
VALUES (111, 1, 1, 1);
insert into ticket(session_id, row, cell, account_id)
VALUES (222, 2, 2, 3);
insert into ticket(session_id, row, cell, account_id)
VALUES (333, 3, 3, 3);
