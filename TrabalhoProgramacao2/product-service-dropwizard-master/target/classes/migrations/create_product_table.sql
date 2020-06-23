--liquibase formatted sql

--changeset taiza:4
create table youtube (
    id bigint not null auto_increment primary key,
    nomevideo varchar(150),
    nomecanal varchar(80),
    dataenvio DateTime
);