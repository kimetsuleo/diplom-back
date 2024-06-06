-- liquibase formatted sql
--changeset l.frolenkov:D-00
--Комментарйи: создание позиции
create sequence s_position START WITH 1 INCREMENT BY 1;

create table positions
(
    id   int          not null,
    name varchar(255) not null,
    constraint pk_position primary key (id)
);

create unique index position_name_uidx on positions (name);

insert into positions(id, name)
values ((SELECT NEXTVAL('s_position')), 'Гениральный директор'),
       ((SELECT NEXTVAL('s_position')), 'Ведущий HR менеджер'),
       ((SELECT NEXTVAL('s_position')), 'Ведущий инженер по разработке'),
       ((SELECT NEXTVAL('s_position')), 'Старший инженер по разработке'),
       ((SELECT NEXTVAL('s_position')), 'Инженер по разработке'),
       ((SELECT NEXTVAL('s_position')), 'Ведущий аналитик'),
       ((SELECT NEXTVAL('s_position')), 'Старший аналитик'),
       ((SELECT NEXTVAL('s_position')), 'Аналитик'),
       ((SELECT NEXTVAL('s_position')), 'Ведущий тестировщик'),
       ((SELECT NEXTVAL('s_position')), 'Старший тестировщик'),
       ((SELECT NEXTVAL('s_position')), 'Тестировщик'),
       ((SELECT NEXTVAL('s_position')), 'Вудещий Devops инженер'),
       ((SELECT NEXTVAL('s_position')), 'Старший Devops инженер'),
       ((SELECT NEXTVAL('s_position')), 'Devops инженер');

--rollback drop sequence s_position;
--rollback drop table position;

--changeset l.frolenkov:D-01
--Комментарйи: создание сотрудника
create sequence s_employee START WITH 1 INCREMENT BY 1;

create table employees
(
    id          int               not null,
    first_name  varchar(255)      not null,
    last_name   varchar(255)      not null,
    patronymic  varchar(255),
    position_id int               not null,
    phone       varchar(20)       not null,
    email       varchar(255)      not null,
    passport    varchar(50)       not null,
    birthdate   date,
    active      boolean           not null,
    password    character varying not null,

    constraint pk_employee primary key (id),
    constraint fk_employee_position foreign key (position_id) references positions (id)
);

create index employee_email_idx on employees (email);
create index employee_first_name_last_name_idx on employees (first_name, last_name);
create index employee_first_name_idx on employees (first_name);
create index employee_last_name_idx on employees (last_name);
create index employee_phone_idx on employees (phone);

create unique index employee_email_uidx on employees (email);
create unique index employee_passport_uidx on employees (passport);
create unique index employee_phone_uidx on employees (phone);

--rollback drop sequence s_employee;
--rollback drop table employees;

--changeset l.frolenkov:D-02
create sequence s_order START WITH 1 INCREMENT BY 1;

create table orders
(
    id             int               not null,
    responded_id   int               not null,
    responsible_id int               not null,
    date_at        date              not null default now(),
    subject        text              not null,
    status         character varying not null,

    constraint pk_order primary key (id),
    constraint fl_order_responsible foreign key (responsible_id) references employees (id),
    constraint fl_order_responded foreign key (responded_id) references employees (id)
);

create index order_responded_id_idx on orders (responded_id);
create index order_responsible_id_idx on orders (responsible_id);

--rollback drop sequence s_order;
--rollback drop table orders;

--changeset l.frolenkov:D-03
create sequence s_contract START WITH 1 INCREMENT BY 1;

create table contracts
(
    id          int                                    not null,
    employee_id int                                    not null,
    position_id int                                    not null,
    rate        double precision                       not null,
    created_at  timestamp with time zone default now() not null,

    constraint pk_contract primary key (id),
    constraint fk_contract_employee_id foreign key (employee_id) references employees (id),
    constraint fk_contract_position_id foreign key (position_id) references positions (id)
);

--rollback drop sequence s_contract;
--rollback drop table contracts;


--changeset l.frolenkov:D-05
create sequence s_shift START WITH 1 INCREMENT BY 1;

create table shifts
(
    id       int                      not null,
    day      character varying        not null,
    start_at timestamp with time zone not null,
    end_at   timestamp with time zone not null,

    constraint pk_shift primary key (id)
);

--changeset l.frolenkov:D-06
create sequence s_schedule START WITH 1 INCREMENT BY 1;

create table schedules
(
    id          int  not null,
    employee_id int  not null,
    shift_id    int  not null,
    date_at     date not null,

    constraint pk_schedule primary key (id),
    constraint fk_schedule_employee_id foreign key (employee_id) references employees (id),
    constraint fk_schedule_shift_id foreign key (shift_id) references shifts (id)
);
