DROP TABLE employees CASCADE;

DROP TABLE workingtimes CASCADE;

DROP TABLE invoices CASCADE;

DROP TABLE categories CASCADE;

DROP TABLE projects CASCADE;

DROP TABLE activities CASCADE;

DROP TABLE companies CASCADE;

drop table personroles cascade;




DROP SEQUENCE employees_seq;

DROP SEQUENCE workingtimes_seq;

DROP SEQUENCE invoices_seq;

DROP SEQUENCE categories_seq;

DROP SEQUENCE projects_seq;

DROP SEQUENCE activities_seq;

DROP SEQUENCE companies_seq;

drop sequence personroles_seq;





CREATE SEQUENCE employees_seq START WITH 1 increment 1;

CREATE SEQUENCE	workingtimes_seq START WITH 1 increment 1;

CREATE SEQUENCE invoices_seq START WITH 1 increment 1;

CREATE SEQUENCE categories_seq START WITH 1 increment 1;

CREATE SEQUENCE projects_seq START WITH 1 increment 1;

CREATE SEQUENCE activities_seq START WITH 1 increment 1;

CREATE SEQUENCE companies_seq START WITH 1 increment 1;

create sequence personroles_seq start with 1 increment 1;



CREATE TABLE employees

	(employees_id int PRIMARY KEY DEFAULT nextval('employees_seq'),

	abbreviation char(45) NOT NULL,

	firstName char(45) NOT NULL,

	lastName char (45) NOT NULL,

	pass char(20) NOT NULL,

    hourlyRate  numeric(9, 2),

    e_kind varchar (1)

	);

create table companies

(
    companies_id int primary key default nextval('companies_seq'),
    companyName char(45) not null
);

create table projects

(
    projects_id int primary key default nextval('projects_seq'),
    company_id  int,
    projectName char(45) not null,
    description char(45) not null,
    hourlyRate  numeric(9, 2),
    startDate   date,
    endDate     date,
    constraint FK_COMPANIES foreign key (company_id) references companies
);

create table workingTimes

(
    workingTimes_id int primary key default nextval('workingtimes_seq'),
    workingDate date,
    startTime time,
    endTime time,
    timeWorked int,
    employee_id int,
    constraint FK_EMPLOYEE FOREIGN KEY (employee_id) references employees
);

create table categories

(
    categories_id int primary key default nextval('categories_seq'),
    categoryName char(45) not null
);

create table invoices

(
    invoices_id int primary key default nextval('invoices_seq'),
    invoiceDate date,
    totalPrice numeric(9,2),
    project_id int,
    closed boolean,
    constraint FK_PROJECT foreign key (project_id) references projects
);


create table activities
(
    activities_id int primary key default nextval('activities_seq'),
    description char(45),
    employee_id int,
    project_id int,
    category_id int,
    startTime timestamp,
    endTime timestamp,
    timeSpent int,
    constraint FK_PERSONS foreign key (employee_id) references employees,
    constraint FK_PROJECTS foreign key (project_id) references projects,
    constraint FK_CATEGORIES foreign key (category_id) references categories

);


create table personRoles
(

    employee_id int,
    personRole char(45),
    constraint FK_EMPLOYEES foreign key (employee_id) references employees

);


insert into employees values (1, 'JS', 'JAN', 'SMITH', 'js123', null, 'e');
insert into employees values (2, 'PT', 'PETER', 'TAVERNIER', 'pt456', null, 'e');
insert into employees values (3, 'RN', 'RUUD', 'NIEHOF', 'rn123', 500.00, 'c');
insert into employees values (4, 'GVG', 'GERT', 'VAN HEIJKOOP', 'gvd34', 450.00, 'c');
insert into employees values (5, 'ADG', 'ATY', 'DE GROOT', 'art34', null, 'e');
insert into employees values (6, 'MP', 'MARIA', 'PEREZ', '1233', null, 'e');
insert into employees values (7, 'FL', 'FRANCOIS', 'LEVIER', 'FL123', null, 'e');
insert into employees values (8, 'BDB', 'BOB', 'DE BOUWER', 'bobby123', 600.00, 'c');
insert into employees values (9, 'LL', 'LAURA', 'NIETLYNN', 'LL123', 450.00, 'c');
insert into employees values (10, 'CL', 'CHRISTINE', 'LUTZ', 'LUTZ234', 500.00, 'c');

insert into companies values (1, 'ABIS');
insert into companies values (2, 'SMALS');

insert into projects values (1, 2, 'projectnaam', 'projectomschrijving', 2000.00,'2022-08-31', '2022-12-20');
insert into projects values (2, 1, 'abisproject', 'abisprojectomschrijving', 1000.00,'2022-09-14', '2023-01-22');

insert into workingtimes values (1, '2022-12-21', '09:00:00', '14:30:00', 280, 9);
insert into workingtimes values (2, '2022-12-22', '08:00:00', '17:00:00', 480, 10);
insert into workingtimes values (3, '2022-12-22', '08:00:00', '17:00:00', 480, 3);
insert into workingtimes values (4, '2022-12-22', '08:00:00', '17:00:00', 480, 4);

insert into categories values (1, 'Teaching');
insert into categories values (2, 'Administration');
insert into categories values (3, 'Accounting');
insert into categories values (4, 'Sales');
insert into categories values (5, 'Studying');
insert into categories values (6, 'Food prep');

insert into invoices values (1, '2021-01-28', 20000.00, 1, true);
insert into invoices values (2, null, null, 2, false);

insert into activities (description, employee_id,  project_id, category_id, startTime, endTime, timeSpent) values ('uh', 3, 2, 4, '2022-12-22 14:00:00', '2022-12-22  15:00:00', '60');
insert into activities (description, employee_id, project_id, category_id, startTime, endTime, timeSpent) values (null,  4, 2, 6, '2022-12-22 11:00:00', '2022-12-22  12:30:00', '90');
insert into activities ( description, employee_id, project_id, category_id, startTime, endTime, timeSpent) values ('ja', 5, 1, 3, '2022-08-11 13:15:00', '2022-08-11 16:00:00', '165');
insert into activities (description, employee_id, project_id, category_id, startTime, endTime, timeSpent) values (null, 6, 1, 5, '2022-08-10 09:00:00', '2022-08-10  11:45:00', '165');


insert into personRoles values (1, 'Teacher');
insert into personRoles values (8, 'Manager');
insert into personRoles values (9, 'Accountant');







	