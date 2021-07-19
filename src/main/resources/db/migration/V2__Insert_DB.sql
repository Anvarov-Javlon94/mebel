insert into category (type_of_product) values('МЕБЕЛЬ ДЛЯ КУХНИ'), ('МЕБЕЛЬ ДЛЯ ЗАЛА'), ('МЕБЕЛЬ ДЛЯ ВАННОЙ');
insert into product(name, photo, price, product_character, product_description, register_date, category_id, view_count, product_availability)
    values ('Газовая плита Artel Apetito G Brown/Gray', '/static/images/3b99a6b4-9d59-4395-b886-ea9e54fbdbe5.icon.jpg', 1200000.0, 'Тут характеристика', 'Тут комментарии',
            '15.07.2021', 1, 15, true),

           ('Artel UA43H3301 Steel/Gray', '/static/images/3b99a6b4-9d59-4395-b886-ea9e54fbdbe5.icon.jpg', 1400000.0, 'Тут характеристика', 'Тут комментарии',
            '15.07.2021', 2, 14, true),

           ('Artel Apetito 02-G Black (чугун)', '/static/images/3b99a6b4-9d59-4395-b886-ea9e54fbdbe5.icon.jpg', 2100000.0, 'Тут характеристика', 'Тут комментарии',
            '15.07.2021', 3, 10, false),

           ('Тефаль', '/static/images/3b99a6b4-9d59-4395-b886-ea9e54fbdbe5.icon.jpg', 2300000.0, 'Тут характеристика', 'Тут комментарии',
            '15.07.2021', 1, 1, true);

