drop table RESTCOMPONENTE;
drop table plato;
drop table componente;
drop table restaurante;
drop table administrador;

CREATE TABLE ADMINISTRADOR(
	adm_usuario varchar(50),
    adm_clave varchar(50),
    constraint primary key(adm_usuario)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE RESTAURANTE(
	rest_id int not null auto_increment,
	rest_nombre varchar(50),
    rest_direccion varchar(50),
    rest_ciudad varchar(50),
    rest_telefono varchar(15),
    adm_usuario varchar(50),
    constraint primary key(rest_id),
    constraint foreign key(adm_usuario) references administrador(adm_usuario)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE PLATO(
    pla_id int not null auto_increment,
    pla_descripcion text,
    pla_precio int not null,
    pla_cantidad int,
    rest_id int,
    constraint primary key(pla_id,rest_id),
    constraint foreign key(rest_id) references restaurante(rest_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE COMPONENTE(
	comp_id int,
    comp_nombre varchar(50) not null,
    comp_tipo varchar(9) not null,
    constraint primary key(comp_id,comp_nombre),
    constraint check(comp_tipo in ('ENTRADA','PRINCIPIO','PROTEINA','BEBIDA')),
    constraint unique(comp_nombre)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE RESTCOMPONENTE(
	rest_id int,
    comp_id int,
    comp_nombre varchar(50) not null,
    dia varchar(9),
    constraint primary key(rest_id,comp_id,comp_nombre,dia),
    constraint foreign key(rest_id) references restaurante(rest_id),
    constraint foreign key(comp_id,comp_nombre) references componente(comp_id,comp_nombre),
    -- constraint foreign key(comp_nombre) references componente(comp_nombre),
    constraint check(dia in ('LUNES','MARTES','MIERCOLES','JUEVES','VIERNES','SABADO','DOMINGO'))
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*----------------------------------*/
/*INSERTAR COMPONENTES              */
/*----------------------------------*/
insert into componente values(1,'lentejas','PRINCIPIO');
insert into componente values(2,'frijoles','PRINCIPIO');
insert into componente values(3,'guiso de habichuela','PRINCIPIO');
insert into componente values(4,'garbanzos horneados','PRINCIPIO');

insert into componente values(1,'sopa de verduras','ENTRADA');
insert into componente values(2,'sancocho de gallina','ENTRADA');
insert into componente values(3,'sopa de costilla','ENTRADA');
insert into componente values(4,'masamorra','ENTRADA');
insert into componente values(5,'ensala de frutas','ENTRADA');

insert into componente values(1,'chuleta de pollo','PROTEINA');
insert into componente values(2,'chuleta de cerdo','PROTEINA');
insert into componente values(3,'carne frita','PROTEINA');
insert into componente values(4,'arroz con pollo con adicion de carne','PROTEINA');

insert into componente values(1,'jugo de lulo','BEBIDA');
insert into componente values(2,'limonada','BEBIDA');
insert into componente values(3,'jugo de tomate','BEBIDA');
insert into componente values(3,'gaseosa de manzana','BEBIDA');

delete from componente where componente.comp_id=6;
/*----------------------------------*/
/*INSERTAR ADMINISTRADOR            */
/*----------------------------------*/
insert into administrador values('admin','123456');

/*----------------------------------*/
/*INSERTAR RESTAURANTE              */
/*----------------------------------*/
insert into restaurante values(1,'LA COSECHA','Cra 11 # 3-45','Popayan',800001,'admin');
insert into restaurante values(2,'RESTAURANTE 2','Cra 11 # 3-45','Popayan',800001,'admin');
insert into restaurante values(3,'RESTAURANTE 3','Cra 11 # 3-45','Popayan',800001,'admin');
insert into restaurante values(4,'RESTAURANTE 4','Cra 11 # 3-45','Popayan',800001,'admin');
/*----------------------------------*/
/*INSERTAR PLATO                    */
/*----------------------------------*/
insert into plato values(1,'es un prueba',0,0,1);
/*----------------------------------*/
/*INSERTAR RESTCOMPONENTE           */
/*----------------------------------*/
insert into restcomponente values(1,1,'lentejas','LUNES');
insert into restcomponente values(1,2,'frijoles','LUNES');
insert into restcomponente values(1,3,'guiso de habichuela','LUNES');

insert into restcomponente values(1,1,'sopa de verduras','LUNES');
insert into restcomponente values(1,2,'sancocho de gallina','LUNES');
insert into restcomponente values(1,3,'sopa de costilla','LUNES');
insert into restcomponente values(1,4,'masamorra','LUNES');

insert into componente values(5,'ensala de frutas','ENTRADA');
insert into componente values(6,'postre 3 leches','ENTRADA');

insert into restcomponente values(1,1,'chuleta de pollo','LUNES');
insert into restcomponente values(1,2,'chuleta de cerdo','LUNES');
insert into restcomponente values(1,3,'carne frita','LUNES');

insert into restcomponente values(1,1,'jugo de lulo','LUNES');
insert into restcomponente values(1,2,'limonada','LUNES');
insert into restcomponente values(1,3,'jugo de tomate','LUNES');
