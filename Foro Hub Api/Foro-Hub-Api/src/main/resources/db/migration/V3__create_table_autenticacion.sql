create table autenticacion(

    id bigint not null auto_increment,
    email varchar(100) not null,
    password varchar(400) not null,

    primary key (id)
);