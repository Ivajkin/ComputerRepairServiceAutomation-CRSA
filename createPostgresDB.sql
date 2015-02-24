--	Приложение к техническому заданию №2. Таблицы БД.

drop schema public cascade;
create schema public;

--	Категории -> Category -> category
--		Код категории	-> category code -> id
--		Название категории -> category name -> name


CREATE TABLE if not exists category (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO category (name) VALUES
('Видеокарты'),
('Мониторы');

--
--	Таблица оборудования -> Hardware -> hardware
--		Код оборудования -> id
--		Экраны                    }
--		Комплектующие             }
--		Жесткие диски             }
--		Зарядные устройства       } -> category_id
--		Клавиатуры                }
--		Аккумуляторы              }
--		Роутеры                   }
--		Софт                      }
--		Прочее                    }
--    Name -> name : string unique
--    Description -> description : string default null

create table if not exists hardware (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique,
  description varchar(200) default null,
  category_id integer not null,
  foreign key (category_id) references category(id) ON UPDATE CASCADE
);

INSERT INTO hardware (name, description, category_id) VALUES
('NVIDIA GTX 520', 'Жизнь стремительна, таким должен быть и твой ПК. Мощь позволит тебе ускорить редактирование фотографий и HD видео.', 1);

--	Поставщик -> provider
--		Код поставщика -> id
--		Наименование поставщика -> name
create table if not exists provider (
  id BIGSERIAL not null unique primary key,
  name varchar(256) not null unique
);

INSERT INTO provider (name) VALUES
('ООО "Технопоинт"'),
('ЗАО "Бизнес-Фабрика"');

--
--	Таблица производителей -> manufacturer
--		Код производителя	-> id
--		Наименование производителя -> name
create table if not exists manufacturer (
  id BIGSERIAL not null  unique primary key,
  name varchar(100) not null unique
);

INSERT INTO manufacturer (name) VALUES ('Aser');
INSERT INTO manufacturer (name) VALUES ('ASUS');
INSERT INTO manufacturer (name) VALUES ('Intel');

--	Склад -> Warehouse item -> warehouse_item
--    Код товара на складе -> id
--    Код категории        ->  Code category
--    Код оборудования    -> Hardware code -> hardware_id : integer, not unique
--    Код производителя   -> Code manufacturer
--    Модель              -> Model
--    Серийный номер      -> Serial number
--		Номер накладной     -> Invoice number -> invoice_number : string, unique
--    Поставщик           -> Provider
--    Комментарии         -> Note
--		Дата оприходования  -> Date of posting -> posting_date : date
--		Количество штук     -> Number of items -> item_count     CONSTRAINT item_count_value CHECK (item_count > 0)
--		Гарантия            -> Warranty -> warranty : string, null, not unique
--		Своя цена процент          -> its price percent?
--		Ремонтная цена процент      -> repair price percent?
--		Закупочная цена     -> purchase price?
--		Розничная цена процент     -> retail price percent?
--		Нулевая цена процент       -> A zero price percent?

create table if not exists warehouse_item (
  id BIGSERIAL not null unique primary key,
  category_id integer not null references category(id) on update cascade on delete restrict,
  hardware_id integer not null references hardware(id) ON UPDATE CASCADE ON DELETE RESTRICT,
  manufacturer_id integer not null references manufacturer(id) on update cascade on delete restrict,
  model varchar(100) not null,
  invoice_number varchar(100) not null unique,
  provider_id integer not null references provider(id) ON UPDATE CASCADE ON DELETE RESTRICT,
  note varchar(100),
  posting_date date not null,
  item_count integer not null, CONSTRAINT item_count_value CHECK (item_count >= 0),
  warranty varchar(200) not null,
  purchase_price integer default 0 not null,
  its_price_percent integer,
  repair_price_percent integer,
  retail_price_percent integer,
  zero_price_percent integer
);

INSERT INTO warehouse_item (category_id, hardware_id, manufacturer_id, model, invoice_number,  provider_id, note, posting_date, item_count, warranty, purchase_price, its_price_percent, repair_price_percent, retail_price_percent, zero_price_percent) VALUES
((select id from category limit 1), (select id from hardware limit 1), (select id from manufacturer limit 1), 'SERYFF', 'es0215', (select id from provider limit 1), 'Новый с японии', '2015-01-10', 15, '5 лет, с 30 мая 2012', 12000, 5, 3, 7, 2);

--	Серийный номер -> Serial number
--    Код серийного номера -> id
--    Номер       ->  number
--    Код склада -> warehouse_item_id

CREATE TABLE if not exists serial_number (
  id BIGSERIAL not null unique primary key,
  number varchar(100) not null unique,
  warehouse_item_id integer not null references warehouse_item (id)  ON UPDATE CASCADE ON DELETE RESTRICT,
  check (length(number) >= 3 and length(number) <= 100)

);

INSERT into serial_number (number, warehouse_item_id) values
('RE543EWQ',(select id from warehouse_item limit 1));


--
--	Таблица неисправностей -> fault
--		Код неисправности
--		Наименование неисправности
create table if not exists fault (
  id BIGSERIAL not null unique primary key,
  name varchar(200) not null unique
);

INSERT INTO fault (name) VALUES
('Разрыв шлейфов');


--
--	Внешний вид -> appearance
--		Код внешнего вида
--		Наименование внешнего вида
create table if not exists appearance (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO appearance (name) VALUES ('Потертости без ЗУ');
INSERT INTO appearance (name) VALUES ('Царапины');
INSERT INTO appearance (name) VALUES ('Разбит экран');

--
--	Комплектность -> completeness
--		Код комплектности
--		Наименование комплектности
create table if not exists completeness (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO completeness (name) VALUES
('Полная комплектность');

--
--	Источник -> source
--		Код источника
--		Наименование источника
create table if not exists source (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO source (name) VALUES ('Google');
INSERT INTO source (name) VALUES ('Яндекс');
INSERT INTO source (name) VALUES ('2gis');
INSERT INTO source (name) VALUES ('клиент');
INSERT INTO source (name) VALUES ('Деловой Хабаровск');

--
--	Тип выполненной работы -> task_type
--		Код типа выполненной работы
--		Наименование типа выполненной работы
create table if not exists task_type (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO task_type (name) VALUES
('Замена шлейфа');


--
--	Статус заявки -> request_status
--		Идентификатор
--		Наименование статуса
create table if not exists request_status (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO request_status (name) VALUES
('Принят'),
('Готов'),
('Выдан/Выполнен');

--
--	Роль сотрудника в системе -> role
--		Идентификатор
--		Наименование роли
create table if not exists role (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO role (name) VALUES
('Администратор'),
('Приемщик');


--	Сотрудники -> employee
--		Код сотрудника -> id
--		ФИО -> name
--    email для сброса пароля -> email
--		Логин -> login
--		Пароль -> password_hash (sha-256(login+pass))
--		Процент оплаты -> fee
--		Роль -> role_id
create table if not exists employee (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique,
  email varchar(100) not null unique,
  login varchar(100) not null unique,
  password_hash varchar(256) not null,
  fee integer default 0, CONSTRAINT percent_value CHECK (fee >= 0 and fee <=100),
  role_id integer not null references role(id)
);

-- login: milkshake32 pass: milkshake32
-- login: avangebit pass: 123456
INSERT INTO employee (name, email, login, password_hash, fee, role_id) VALUES ('Иванов Иван Гольцман', 'i.golz@mail.ru', 'milkshake32', 'milk', 10, 2);
INSERT INTO employee (name, email, login, password_hash, fee, role_id) VALUES ('Алексей Верещагин', 'avangebit@gmail.com', 'avangebit', '12345678', 10, 1);
INSERT INTO employee (name, email, login, password_hash, fee, role_id) VALUES ('Доримова Екатерина Олеговна', 'dorimova@mail.ru', 'dorimova123', 'dorimova', 35, 2);
INSERT INTO employee (name, email, login, password_hash, fee, role_id) VALUES ('Techno Media Ltd', 'info@tmedia.pro', 'admin', '12345678', 0, 1);



--	Таблица заявок -> Request -> request
--		Номер заявки -> request Number? (string or int??)
--		Наименование оборудования -> name of equipment?
--		Производитель -> manufacturer?
--		Модель -> model?
--		Серийный номер -> serial number?
--		Адрес -> address?
--		Телефон -> phone?
--		Неисправность -> fault?
--		инженер -> engineer?
--		Дата получения (приема) -> Date of receipt ( receiving )?
--		Дата выдачи -> date of issue?
--		Сумма -> amount?
--		Способ оплаты -> method of payment?
--		Внешний вид -> appearance?
--		Комплектность -> completeness?
--		Источник -> source?
--		Выполненные работы -> Completed works?
--		Дата звонка -> Date of call?
--		Примечание -> note?
--		Имя клиента -> customer Name?
--		Статус заявки -> application Status?
--		Примерная стоимость -> The approximate cost?
--		Предоплата -> prepayment?
--		Установленные запчасти -> parts installed?
create table if not exists request (
  req_num_id BIGSERIAL not null unique primary key,
  request_number varchar(8) not null unique,
  hardware_id integer not null references hardware(id),
  manufacturer_id integer not null references manufacturer(id),
  model varchar(100) not null,
  serial_number varchar(100) not null,
  fault_id integer not null references fault(id),
  appearance_id integer not null references appearance(id),
  completeness_id integer not null references completeness(id),
  phone varchar(20),
  address varchar(300)  default null,
  customer_name varchar(300) not null,
  source_id integer not null references source(id),
  date_of_call date,
  note varchar(200),
  approximate_cost int default null,
  prepayment int default null,
  acceptor_id integer not null references employee(id),
  responsible_id integer not null references employee(id),
  date_of_receipt date not null,
  date_of_issue date,
  amount int,
  method_of_payment varchar(100),
  request_status_id integer not null references request_status(id),
  delivery boolean,
  slot_id int not null,
  check (date_of_call <= date_of_receipt),
  check (date_of_receipt <= date_of_issue),
  check (slot_id >= 1 and slot_id <= 60)
);

INSERT INTO public.request (req_num_id, request_number, hardware_id, manufacturer_id, model, serial_number, fault_id, appearance_id, completeness_id, phone, address, customer_name, source_id, date_of_call, note, approximate_cost, prepayment, acceptor_id, responsible_id, date_of_receipt, date_of_issue, amount, method_of_payment, request_status_id, slot_id) VALUES (6, '501-720', 1, 1, 'T8', 'UIO124', 1, 1, 1, '+7122332121', '', 'Частное лицо', 1, '2015-01-10', '', null, 0, 4, 1, '2015-01-10', null, null, '1', 1, 60);
INSERT INTO public.request (req_num_id, request_number, hardware_id, manufacturer_id, model, serial_number, fault_id, appearance_id, completeness_id, phone, address, customer_name, source_id, date_of_call, note, approximate_cost, prepayment, acceptor_id, responsible_id, date_of_receipt, date_of_issue, amount, method_of_payment, request_status_id, slot_id) VALUES (7, '501-721', 1, 1, 'T8', 'POL97', 1, 1, 1, '+7122332121', '', 'Частное лицо', 1, '2015-01-10', '', null, 0, 4, 1, '2015-01-10', null, null, '1', 1, 59);
INSERT INTO public.request (req_num_id, request_number, hardware_id, manufacturer_id, model, serial_number, fault_id, appearance_id, completeness_id, phone, address, customer_name, source_id, date_of_call, note, approximate_cost, prepayment, acceptor_id, responsible_id, date_of_receipt, date_of_issue, amount, method_of_payment, request_status_id, slot_id) VALUES (8, '501-324', 1, 1, 'T8', 'UI120', 1, 1, 1, '+7-2323213123', '', 'Частное лицо', 1, '2015-01-10', '', null, 0, 4, 1, '2015-01-10', null, null, '1', 1, 58);
INSERT INTO public.request (req_num_id, request_number, hardware_id, manufacturer_id, model, serial_number, fault_id, appearance_id, completeness_id, phone, address, customer_name, source_id, date_of_call, note, approximate_cost, prepayment, acceptor_id, responsible_id, date_of_receipt, date_of_issue, amount, method_of_payment, request_status_id, slot_id) VALUES (9, '501-590', 1, 1, 'T8', 'UI120', 1, 1, 1, '+7122332121', '', 'Частное лицо', 1, '2015-01-10', '', null, 0, 4, 1, '2015-01-10', null, null, '1', 1, 57);
INSERT INTO public.request (req_num_id, request_number, hardware_id, manufacturer_id, model, serial_number, fault_id, appearance_id, completeness_id, phone, address, customer_name, source_id, date_of_call, note, approximate_cost, prepayment, acceptor_id, responsible_id, date_of_receipt, date_of_issue, amount, method_of_payment, request_status_id, slot_id) VALUES (10, '501-591', 1, 1, 'RE34', '7865463728FDR', 1, 1, 1, '+793452634', '', 'Частное лицо', 1, '2015-01-10', '', null, 0, 4, 3, '2015-01-10', null, null, '1', 1, 56);
INSERT INTO public.request (req_num_id, request_number, hardware_id, manufacturer_id, model, serial_number, fault_id, appearance_id, completeness_id, phone, address, customer_name, source_id, date_of_call, note, approximate_cost, prepayment, acceptor_id, responsible_id, date_of_receipt, date_of_issue, amount, method_of_payment, request_status_id, slot_id) VALUES (11, '501-563', 1, 1, 'T8', 'UI120', 1, 1, 1, '+723232', '', 'Частное лицо', 1, '2015-01-10', '', null, 0, 4, 1, '2015-01-10', null, null, null, 1, 55);
INSERT INTO public.request (req_num_id, request_number, hardware_id, manufacturer_id, model, serial_number, fault_id, appearance_id, completeness_id, phone, address, customer_name, source_id, date_of_call, note, approximate_cost, prepayment, acceptor_id, responsible_id, date_of_receipt, date_of_issue, amount, method_of_payment, request_status_id, slot_id) VALUES (12, '501-414', 1, 1, 'T8', 'UI120', 1, 1, 1, '+7122332121', '', 'Частное лицо', 1, '2015-01-10', '', null, 0, 4, 1, '2015-01-10', null, null, null, 1, 54);


--
--	Выполненные работы -> task
--		Код выполненной работы
--		Инженер -> engineer_id
--		Цена -> price
--		Наименование выполненной работы -> task_type_id
--		Заявка для которой выполнена работа -> request_id
create table if not exists task (
  id BIGSERIAL not null unique primary key,
  engineer_id integer not null references employee(id),
  price integer not null,
  task_type_id integer not null references task_type(id),
  request_id integer not null references request(req_num_id)
);

INSERT INTO task (engineer_id, price, task_type_id, request_id) VALUES
((select id from employee limit 1), 100, (select id from task_type limit 1), (select req_num_id from request limit 1));


--
--	Выполненные работы -> task
--		Код выполненной работы
--		Инженер -> engineer_id
--		Цена -> price
--		Наименование выполненной работы -> task_type_id
--		Заявка для которой выполнена работа -> request_id
create table if not exists task (
  id BIGSERIAL not null unique primary key,
  engineer_id integer not null references employee(id),
  price integer not null,
  task_type_id integer not null references task_type(id),
  request_id integer not null references request(req_num_id)
);

INSERT INTO task (engineer_id, price, task_type_id, request_id) VALUES
((select id from employee limit 1), 100, (select id from task_type limit 1), (select req_num_id from request limit 1));

-- Установленные запчасти -> parts_installed
--		Код установленной запчасти
--		наименование -> hardware_id
--		Цена -> price
--		количество -> count
--		Заявка для которой выполнена работа -> request_id
create table if not exists parts_installed (
  id BIGSERIAL not null unique primary key,
  hardware_id integer not null references employee(id),
  price integer not null,
  count integer not null,
  request_id integer not null references request(req_num_id)
);

INSERT INTO parts_installed (hardware_id, price, count, request_id) VALUES
((select id from hardware limit 1), 100, 2, (select req_num_id from request limit 1));



-- ------------------------------------------------------------------

--

--	Товар  - ?
--		Код товара	
--		Название товара	
--		Код категория	
--
--		
--	Операции
--		Номер накладной	
--		Код поставщика	
--		Дата операции	
--		Комментарии	
--		
--	Серийный номер
--		Код товара	
--		Номер накладной	
--		Номер серийника	
--
--		
--	Прейскурант работ
--		Код работы	
--		Вид работы	
--		Цена работы	
--		



-- Тип кассы - cash_type
-- 		id
--		name
create table if not exists cash_type (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique,
  saldo int not null
);

INSERT INTO cash_type (name, saldo) VALUES
('Наличные', 1000),
('Карта', 0),
('Безналичные', 0);


-- Код статуса кассовой операции - cash_operation_status
-- 		id
--		name
create table if not exists cash_operation_status (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO cash_operation_status (name) VALUES
('Приход'),
('Расход');

-- Юридические лица - customers
-- id
-- name
-- phone
-- note

create table if not exists customer (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique,
  phone varchar(100),
  note varchar(300)
);

INSERT INTO customer (name, phone, note)
  VALUES ('Частное лицо', '', '');
INSERT INTO customer (name, phone, note)
  VALUES ('ООО «Восточный ветер»', '+7-984-123-45-23', 'Сфера строительства и ремонта');
INSERT INTO customer (name, phone, note)
  VALUES ('ИП Корнечук К.Л.', '43-23-54', 'Компания в сфере сбыта продуктов питания');

--	Кассовая операция - cash_operation
--		Код кассы	- id
--		Сумма		- amount
--		Сотрудник	- employee_id
--		Пояснение	- description
--		Код статуса кассовой операции	- cash_operation_status_id (приход, расход)
--		Дата 		- operation_date
--		Наименование товара	- product_id (connected to hardware_id)
--		Остаток		- saldo
--		Тип кассы	- cash_type_id (безналичные, наличные, карта)
create table if not exists cash_operation (
  id BIGSERIAL not null unique primary key,
  amount int,
  product_id integer not null references hardware(id),
  employee_id integer not null references employee(id),
  description varchar(400)  default null,
  cash_operation_status_id integer not null references cash_operation_status(id),
  operation_date date not null,
  saldo int not null,
  cash_type_id integer not null references cash_type(id),
  check (saldo >= 0),
  check (amount >= 1),
  check (amount <= 500000)
);

INSERT INTO cash_operation (
  amount,
  product_id,
  employee_id,
  description,
  cash_operation_status_id,
  operation_date,
  saldo,
  cash_type_id
) VALUES
(1000, (select id from hardware limit 1), (select id from employee limit 1), 'Приход в кассу по безналичному', (select id from cash_operation_status limit 1), '2011-01-01', 1000, (select id from cash_type limit 1));



--		
--	Справочники юридических лиц
--		Код юр.лица	
--		Клиент	
--		Телефон	
--		Примечание	
--		
--	Поставщик
--		Код поставщика	
--		Наименование поставщика	
--		
--	Талон
--		Номер талона	
--		Дата выдачи	
--		Модель 	
--		Серийный номер	
--		Внешний вид и комплектация	
--		Чек
--		Номер чека	
--		Дата выдачи	
--		Товар 	
--		Код гарантийного чека	
--		Количество	
--		Цена за штуку	
--		Сумма	
--		
--	Выполненные работы
--		Код выполненной работы	
--		Наименование работы	
--		Цена	
--		Инженер	

-- ----------------- EN:

--	Annex to the technical task number 2. DB tables.
--		
--	Table manufacturers?
--		manufacturer code?
--		name of the manufacturer?
--		
--	fault Table?
--		trouble Code?
--		name of fault?
--		
--	appearance?
--		Code appearance?
--		Designation appearance?
--		
--	completeness?
--		code completeness?
--		name completeness?
--		
--	source?
--		source code?
--		name of source?
--		
--	Completed works?
--		Code of the work performed?
--		Name of the work performed?
--
--		
--	goods?
--		Product Code?
--		Product Name?
--		category code?
--		
--	operations?
--		invoice number?
--		supplier code?
--		Date of operation?
--		Comments?
--		
--	serial number?
--		Product Code?
--		invoice number?
--		Phone seriynika?
--		
--	staff?
--		officer code?
--		Name?
--		login?
--		password?
--		percentage of payment?
--		role?
--		
--	price list of works?
--		work Code?
--		Type of work?
--		Price of?
--		
--	cash?
--		code Case?
--		amount?
--		employee?
--		clarification?
--		status Code?
--		date?
--		description of goods?
--		residue?
--		
--	Reference entities?
--		code of legal entity?
--		client?
--		phone?
--		note?
--		
--	supplier?
--		supplier code?
--		name of supplier?
--		
--	ticket?
--		Phone coupon?
--		date of issue?
--		model?
--		serial number?
--		Appearance and equipment?
--		check?
--		check number?
--		date of issue?
--		goods?
--		Code warranty check?
--		number?
--		Price per piece?
--		amount?
--		
--	Completed works?
--		Code of the work performed?
--		name of work?
--		price?
--		engineer?
