CREATE TABLE ROOMS (
    room_type text,
    room_number int PRIMARY KEY,
    cost_per_night int
);

CREATE TABLE CUSTOMERS (
    customer text,
    room_number int,
    days_staying int,
    id serial,
    starting_day date NOT NULL default current_timestamp,
    FOREIGN KEY (room_number) REFERENCES ROOMS (room_number)
);