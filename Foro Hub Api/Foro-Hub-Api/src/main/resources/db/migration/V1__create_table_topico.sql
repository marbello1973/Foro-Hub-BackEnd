create table usuario (

    id bigint not null auto_increment,
    nombre varchar(100) not null,
    correoElectronico varchar(100) not null unique,
    password varchar(100) not null unique,
    perfil varchar(100) not null,
    status smallint,

    primary key (id)
);
