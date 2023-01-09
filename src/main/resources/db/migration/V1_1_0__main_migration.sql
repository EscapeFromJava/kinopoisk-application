create table actor
(
    id         bigserial
        primary key,
    first_name varchar(255),
    last_name  varchar(255)
);

alter table actor
    owner to postgres;

create table director
(
    id         bigserial
        primary key,
    first_name varchar(255),
    last_name  varchar(255)
);

alter table director
    owner to postgres;

create table movie
(
    id          bigserial
        primary key,
    description varchar(255),
    genre       smallint,
    title       varchar(255),
    year        integer not null,
    director_id bigint
        constraint fkbi47w3cnsfi30gc1nu2avgra2
            references director
);

alter table movie
    owner to postgres;

create table actor_movie
(
    movie_id bigint not null
        constraint fk10wqik6soqi350gph7sjdgpl4
            references movie,
    actor_id bigint not null
        constraint fkpjjfl93tcc6ix1ugr0bn3so7h
            references actor
);

alter table actor_movie
    owner to postgres;

