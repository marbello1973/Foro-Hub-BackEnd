create table topicos (
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(300) not null,
    fecha varchar(100) not null,
    status tinyint not null,
    curso varchar(100) not null,
    autor varchar(100) not null,

    primary key (id)
);



