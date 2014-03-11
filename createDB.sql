--	Приложение к техническому заданию №2. Таблицы БД.
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

-- DB: core5429_repair
-- User: core5429_repair
-- Pass: nNTTsq8VZnTnLh9q

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;


CREATE DATABASE IF NOT EXISTS `core5429_repair` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `core5429_repair`;




--	Категории -> Category -> category
--		Код категории	-> category code -> id
--		Название категории -> category name -> name

create table if not exists `category` (
  `id` int not null AUTO_INCREMENT unique primary key,
  `name` varchar(100) CHARACTER SET utf8 not null unique
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3;

INSERT INTO `category` (`name`) VALUES
('Видеокарты');

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

create table if not exists `hardware` (
  `id` int not null AUTO_INCREMENT unique primary key,
  `name` varchar(100) CHARACTER SET utf8 not null unique,
  `description` varchar(200) CHARACTER SET utf8 default null,
  `category_id` integer not null references category(id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3;

INSERT INTO `hardware` (`name`, `description`, `category_id`) VALUES
('NVIDIA GTX 520', 'Жизнь стремительна, таким должен быть и твой ПК. Мощь позволит тебе ускорить редактирование фотографий и HD видео.', 1);

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

create table if not exists `warehouse_item` (
  `invoice_number` varchar(100) CHARACTER SET utf8 not null unique primary key,
  `hardware_id` integer not null references hardware(id) ON UPDATE CASCADE ON DELETE RESTRICT,
  `posting_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `item_count` integer not null, CONSTRAINT item_count_value CHECK (item_count >= 0),
  `warranty` varchar(200) CHARACTER SET utf8 not null

) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3;

INSERT INTO `warehouse_item` (`invoice_number`, `hardware_id`, `item_count`, `warranty`) VALUES
('es0215', 1, 15, '5 лет, с 30 мая 2012');



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
create table if not exists `request` (
  `req_num_id` int not null AUTO_INCREMENT unique primary key,
  `hardware_id` integer not null references hardware(id),
  `manufacturer_id` integer not null references manufacturer(id),
  `model` varchar(100) CHARACTER SET utf8 not null,
  `serial_number` varchar(100) CHARACTER SET utf8 not null,
  `fault_id` integer not null references fault(id),
  `appearance_id` integer not null references appearance(id),
  `completeness_id` integer not null references completeness(id),
  `phone` varchar(20),
  `address` varchar(300) CHARACTER SET utf8 default null,
  `customer_name` varchar(300) CHARACTER SET utf8 not null,
  `source_id` integer not null references source(id),  
  `date_of_call` varchar(20),
  `note` varchar(200),
  `approximate_cost` int default null,
  `prepayment` int default null,
  `acceptor_id` integer not null references employee(id),
  `responsible_id` integer not null references employee(id),
  `date_of_reciept` varchar(20) not null,
  `date_of_issue` varchar(20),
  `amount` int,
  `method_of_peyment` varchar(100),
  `request_status_id` integer not null references status(id),
  `completed_works_id` integer not null references completed_works(id),
  `parts_installed_id` integer not null references goods(id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3;

-- ------------------------------------------------------------------


--		
--	Таблица производителей
--		Код производителя	
--		Наименование производителя	
--		
--	Таблица неисправностей
--		Код неисправности	
--		Наименование неисправности	
--		
--	Внешний вид
--		Код внешнего вида	
--		Наименование внешнего вида	
--		
--	Комплектность
--		Код комплектности	
--		Наименование комплектности	
--		
--	Источник
--		Код источника	
--		Наименование источника	
--		
--	Выполненные работы
--		Код выполненной работы	
--		Наименование выполненной работы	
--

--	Товар
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
--	Сотрудники
--		Код сотрудника	
--		ФИО	
--		Логин	
--		Пароль	
--		Процент оплаты	
--		Роль	
--		
--	Прейскурант работ
--		Код работы	
--		Вид работы	
--		Цена работы	
--		
--	Касса
--		Код кассы	
--		Сумма	
--		Сотрудник	
--		Пояснение	
--		Код статуса	
--		Дата 	
--		Наименование товара	
--		Остаток	
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
