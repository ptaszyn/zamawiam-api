INSERT INTO restaurants(id, name, description, contact, requirement, version) VALUES
(1, 'India King', 'Restauracja Indyjska', '070072772', 'minimum 5 zestawów', 0),
(2, 'Grill Inn', 'Restauracja Śródziemnomorska', '070072772', 'minimalna kwota 35zł', 0);

insert into food_groups ( id, name, restaurant_id, is_main) values
(1, 'Dania', 1, 1),
(2, 'Dodatki', 1, 0);

insert into food_items (id, food_group_id, name, price, description, version) values
(1, 1, 'Butter Chicken', 20.00, 'Kurczak z masłem', 0),
(2, 1, 'Malai Paneer Tikka', 24.99, 'Warzywka w szpinaku', 0),
(3, 2, 'ryż', 0, '', 0),
(4, 2, 'chleb', 1, '', 0);

insert into group_groups (main_id, side_id) values
(1, 2);

insert into order_statuses (id, name, sequence) values
(1, 'Otwarte', 1),
(2, 'Zamknięte', 2),
(3, 'Zamówione', 3),
(4, 'Dostarczone', 4),
(5, 'Opłacone', 5);

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');