--	Приложение к техническому заданию №2. Таблицы БД.


--	Категории -> Category -> category
--		Код категории	-> category code -> id
--		Название категории -> category name -> name


CREATE TABLE if not exists category (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO category (id, name) VALUES
  (1, 'Видеокарты'),
  (2, 'Мониторы');

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

INSERT INTO hardware (id, name, description, category_id) VALUES
(1, 'NVIDIA GTX 520', 'Жизнь стремительна, таким должен быть и твой ПК. Мощь позволит тебе ускорить редактирование фотографий и HD видео.', 1);

--	Склад -> Warehouse item -> warehouse_item
--		x Номер накладной     -> Invoice number -> invoice_number : string, unique
--		Код оборудования    -> Hardware code -> hardware_id : integer, not unique
--		Дата оприходования  -> Date of posting -> posting_date : date
--		Количество штук     -> Number of items -> item_count     CONSTRAINT item_count_value CHECK (item_count > 0)
--		Гарантия            -> Warranty -> warranty : string, null, not unique
--		Своя цена           -> its price?
--		Ремонтная цена      -> repair price?
--		Закупочная цена     -> purchase price?
--		Розничная цена      -> retail price?
--		Нулевая цена        -> A zero price?

create table if not exists warehouse_item (
  invoice_number varchar(100) not null unique primary key,
  hardware_id integer not null references hardware(id) ON UPDATE CASCADE ON DELETE RESTRICT,
  posting_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  item_count integer not null, CONSTRAINT item_count_value CHECK (item_count >= 0),
  warranty varchar(200) not null,
  repair_price integer default 0 not null,
  provider_id integer not null references provider(id) ON UPDATE CASCADE ON DELETE RESTRICT


);

INSERT INTO warehouse_item (invoice_number, hardware_id, item_count, warranty) VALUES
('es0215', 1, 15, '5 лет, с 30 мая 2012');


--
--	Таблица производителей -> manufacturer
--		Код производителя	-> id
--		Наименование производителя -> name
create table if not exists manufacturer (
  id BIGSERIAL not null  unique primary key,
  name varchar(100) not null unique
);

INSERT INTO manufacturer (id, name) VALUES
(1, 'ASUS');


--
--	Таблица неисправностей -> fault
--		Код неисправности
--		Наименование неисправности
create table if not exists fault (
  id BIGSERIAL not null unique primary key,
  name varchar(200) not null unique
);

INSERT INTO fault (id, name) VALUES
(1, 'Разрыв шлейфов');


--
--	Внешний вид -> appearance
--		Код внешнего вида
--		Наименование внешнего вида
create table if not exists appearance (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO appearance (id, name) VALUES
(1, 'В отличном состоянии');

--
--	Комплектность -> completeness
--		Код комплектности
--		Наименование комплектности
create table if not exists completeness (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO completeness (id, name) VALUES
(1, 'Полная комплектность');

--
--	Источник -> source
--		Код источника
--		Наименование источника
create table if not exists source (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO source (id, name) VALUES
(1, 'Заявка клиента');

--
--	Тип выполненной работы -> task_type
--		Код типа выполненной работы
--		Наименование типа выполненной работы
create table if not exists task_type (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO task_type (id, name) VALUES
(1, 'Замена шлейфа');


--
--	Статус заявки -> request_status
--		Идентификатор
--		Наименование статуса
create table if not exists request_status (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO request_status (id, name) VALUES
(1, 'Принят'),
(2, 'Готов'),
(3, 'Выдан/Выполнен');

--
--	Роль сотрудника в системе -> role
--		Идентификатор
--		Наименование роли
create table if not exists role (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO role (id, name) VALUES
(1, 'Администратор'),
(2, 'Приемщик');


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
INSERT INTO employee (id, name, email, login, password_hash, fee, role_id) VALUES (1, 'Иванов Иван Гольцман', 'i.golz@mail.ru', 'milkshake32', 'milk', 10, 2);
INSERT INTO employee (id, name, email, login, password_hash, fee, role_id) VALUES (2, 'Алексей Верещагин', 'avangebit@gmail.com', 'avangebit', '12345678', 10, 1);
INSERT INTO employee (id, name, email, login, password_hash, fee, role_id) VALUES (3, 'Доримова Екатерина Олеговна', 'dorimova@mail.ru', 'dorimova123', 'dorimova', 35, 2);
INSERT INTO employee (id, name, email, login, password_hash, fee, role_id) VALUES (4, 'Techno Media Ltd', 'info@tmedia.pro', 'admin', '12345678', 0, 1);



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
  check (date_of_call <= date_of_receipt),
  check (date_of_receipt <= date_of_issue)
);


INSERT INTO request (hardware_id, manufacturer_id, model, serial_number, fault_id, appearance_id, completeness_id, phone, address, customer_name, source_id, date_of_call, note, approximate_cost, prepayment, acceptor_id, responsible_id, date_of_receipt, date_of_issue, amount, method_of_payment, request_status_id/*, completed_works_id, parts_installed_id*/) VALUES
(1, 1, 'model', 'serial_number', 1, 1, 1, '+7-924-123-45-67', 'address', 'customer_name', 1, '2011-01-01', 'note', 123, 55, 1, 1, '2011-01-01', '2011-01-01', 12, 'method_of_payment', 1/*, 1, 1*/);


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
(1, 100, 1, 1);


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
(1, 100, 1, 1);

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
(1, 100, 2, 1);


--	Поставщик -> provider
--		Код поставщика -> id
--		Наименование поставщика -> name
create table if not exists provider (
  id BIGSERIAL not null unique primary key,
  name varchar(256) not null unique
);

INSERT INTO provider (id, name) VALUES
(1, 'ООО "Технопоинт"'),
(2, 'ЗАО "Бизнес-Фабрика"');


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

INSERT INTO cash_type (id, name, saldo) VALUES
(1, 'Наличные', 1000),
(2, 'Карта', 0),
(3, 'Безналичные', 0);


-- Код статуса кассовой операции - cash_operation_status
-- 		id
--		name
create table if not exists cash_operation_status (
  id BIGSERIAL not null unique primary key,
  name varchar(100) not null unique
);

INSERT INTO cash_operation_status (id, name) VALUES
(1, 'Приход'),
(2, 'Расход');

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

INSERT INTO customer (id, name, phone, note)
  VALUES (1, 'Частное лицо', '', '');
INSERT INTO customer (id, name, phone, note)
  VALUES (2, 'ООО «Восточный ветер»', '+7-984-123-45-23', 'Сфера строительства и ремонта');
INSERT INTO customer (id, name, phone, note)
  VALUES (3, 'ИП Корнечук К.Л.', '43-23-54', 'Компания в сфере сбыта продуктов питания');

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
					id,
					amount,
					product_id,
					employee_id,
					description,
					cash_operation_status_id,
					operation_date,
					saldo,
					cash_type_id
) VALUES
(1, 1000, 1, 1, 'Приход в кассу по безналичному', 1, '2011-01-01', 1000, 1);

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
