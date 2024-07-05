
create table foro_hub(

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(300) not null unique,
    fecha_de_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    activo varchar(100) not null,
    autor varchar(100) not null,
    curso varchar(100),

    primary key(id)

);
