insert into user(id, username, name, email, phone, password, address,credit_card_id, created_at, created_by, image_path)
values(1000, 'duygu' ,'Duygu Aktukmak', 'duyguaktokmak@gmail.com', '05553445677', '$2a$10$FC3mTz23fIrQ/pLfhpvu8el5g2vzqcuZZKp9KAQau5i./jy6knFBe', 'Mamak, Yüzüncü Yıl, Ankara', '', '2020-11-01', 'duygu', 'http://localhost:8080/woman.png');

insert into user(id, username, name, email, phone, password, address,credit_card_id, created_at, created_by, image_path)
values(1001, 'emine' ,'Emine Ates', 'emine@gmail.com', '05345634543', '$2a$10$WPPwrjuim8Qqo0Tcxoklleh47562bbR/Bgr.xZTz92VkMpw4cy/h2', 'Yüzüncü Yıl, Ankara', '', '2020-11-01', 'emine', 'http://localhost:8080/woman.png');

insert into user(id, username, name, email, phone, password, address,credit_card_id, created_at, created_by, image_path)
values(1002, 'hasan' ,'Hasan Kara', 'hasan@gmail.com', '05534345453', '$2a$10$Fk3W2ujQVsLUGHjNf7b8G.c/WhaMjlJQjCtZGktZJTqVXaIZiZ.qq', 'Yüzüncü Yıl, Ankara', '', '2020-11-01', 'hasan', 'http://localhost:8080/man.png');

insert into user(id, username, name, email, phone, password, address,credit_card_id, created_at, created_by, image_path)
values(1003, 'ali' ,'Ali Gunes', 'ali@gmail.com', '05534345453', '$2a$10$Sz/n2pFNnYMuGGp7ggvBZ.U2wDEuOeXkJ/0SeyLEqD83W9PRN/aVO', 'Yüzüncü Yıl, Ankara', '', '2020-11-01', 'ali', 'http://localhost:8080/man.png');


insert into item(id, name, description, price, image_path,state, user_id, tags, created_at, created_by)
values(1000,'Samsung phone charger', 'samsung phone charger', 10.0,'http://localhost:8080/phone_charger.png', 'AVAILABLE', 1000, 'phone,charger,samsung', '2020-11-01', 'duygu');

insert into item(id, name, description, price, image_path,state, user_id, tags, created_at, created_by)
values(1002,'Window Wiper Tool Kit', 'This is a window wiper tool kit which I want to rent for a month.It clears windows very effectively.', 3.0,'http://localhost:8080/wiper_1.png,http://localhost:8080/wiper_2.png,http://localhost:8080/wiper_3.png', 'AVAILABLE', 1000, 'window,wiper,cleaner,home', '2020-11-01', 'duygu');

insert into item(id, name, description, price, image_path,state, user_id, tags, created_at, created_by)
values(1001,'Ski Suit', 'This suit is unisex, its size is Medium. It consists of sweat, pant and shoes.', 20.0,'http://localhost:8080/ski_1.png,http://localhost:8080/ski_2.png,http://localhost:8080/ski_3.png,http://localhost:8080/ski_4.png', 'AVAILABLE', 1001, 'ski' ,'2020-11-01', 'emine');

insert into item(id, name, description, price, image_path,state, user_id, tags, created_at, created_by)
values(1003,'Home Repair Tool Kit', 'The equipment is usable and consists of all pieces that you might need.', 5.0,'http://localhost:8080/repair_kit_1.png', 'AVAILABLE', 1003, 'home,repair,equipment', '2020-11-01', 'ali');


insert into available_time(id, item_id, start_date, end_date)
values(1000,1000, '2020-10-02T14:00:00.000', '2020-10-02 20:00:00.000');
insert into available_time(id, item_id, start_date, end_date)
values(1001,1000, '2020-10-03T14:00:00.000', '2020-10-03 20:00:00.000');
insert into available_time(id, item_id, start_date, end_date)
values(1002,1000, '2020-10-04T14:00:00.000', '2020-10-04 20:00:00.000');

insert into available_time(id, item_id, start_date, end_date)
values(1003,1001, '2020-10-02T08:00:00.000', '2020-10-15 23:59:00.000');

insert into available_time(id, item_id, start_date, end_date)
values(1005,1003, '2021-01-01T08:00:00.000', '2021-02-01 23:59:00.000');

insert into available_time(id, item_id, start_date, end_date)
values(1004,1002, '2021-01-01T08:00:00.000', '2021-02-01 23:59:00.000');


insert into rent(id, user_id, item_id, start_time, end_time, offered_price, comment, state, created_at, created_by)
values(1000, 1000, 1001, '2020-09-04 08:00:00.000', '2020-09-07 23:59:00.000', 15, '', 'FINISHED' ,'2020-09-03 12:00:00.000', 'duygu');

insert into rent(id, user_id, item_id, start_time, end_time, offered_price, comment, state, created_at, created_by)
values(1001, 1002, 1001, '2020-08-15 08:00:00.000', '2020-09-01 23:59:00.000', 13, '', 'FINISHED' ,'2020-08-12 12:00:00.000', 'hasan');

insert into rent(id, user_id, item_id, start_time, end_time, offered_price, comment, state, created_at, created_by)
values(1002, 1001, 1000, '2020-10-02T15:00:00.000', '2020-10-02T17:00:00.000', 8, 'I want to get and leave phone charger myself so I want to have discount to 8 TL.','OFFERED' ,'2020-10-02T12:00:00.000', 'emine');

