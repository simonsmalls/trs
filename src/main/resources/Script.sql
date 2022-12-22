--DROP TABLE employees CASCADE;

--DROP TABLE workingtimes CASCADE;

--DROP TABLE invoices CASCADE;

--DROP TABLE consultants CASCADE;

--DROP TABLE categories CASCADE;

--DROP TABLE projects CASCADE;

--DROP TABLE activities CASCADE;

--DROP TABLE companies CASCADE;

--drop table personroles cascade;
--drop table roles cascade;


DROP SEQUENCE employees_seq;

DROP SEQUENCE workingtimes_seq;

DROP SEQUENCE invoices_seq;

--DROP SEQUENCE categories_seq;

--DROP SEQUENCE projects_seq;

--DROP SEQUENCE activities_seq;

--DROP SEQUENCE companies_seq;

--drop sequence personroles_seq;

--drop sequences roles_seq;



CREATE SEQUENCE employees_seq START WITH 1 increment 1;

CREATE SEQUENCE	workingtimes_seq START WITH 1 increment 1;

CREATE SEQUENCE invoices_seq START WITH 1 increment 1;

CREATE SEQUENCE categories_seq START WITH 1 increment 1;

CREATE SEQUENCE projects_seq START WITH 1 increment 1;

CREATE SEQUENCE activities_seq START WITH 1 increment 1;

CREATE SEQUENCE companies_seq START WITH 1 increment 1;

create sequence personroles_seq start with 1 increment 1;

create sequence roles_seq start with 1 increment 1;



CREATE TABLE employees

	(employees_id int PRIMARY KEY DEFAULT nextval('employees_seq'),

	abbreviation char(45) NOT NULL,

	firstName char(45) NOT NULL,

	lastName char (45) NOT NULL,

	pass char(20) NOT NULL

	);

create table workingTimes

(
    workingTimes_id int primary key default nextval('workingtimes_seq'),
    workingDate date,
    startTime time,
    endTime time,
    timeWorked time,
    employee_id int,
    constraint FK_EMPLOYEE (employees_id) references employees,
);


create table invoices

(
    invoices_id int primary key default nextval('invoices_seq'),
    invoiceDate date,
    totalPrice numeric(9,2),
    project_id int,
    closed boolean,
);

create table categories

(
    categories_id int primary key default nextval('categories_seq'),
    categoryName char(45) not null,
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
);

create table activities
(
    activities_id int primary key default nextval('activities_seq'),
    description char(45),
    person_id int,
    project_id int,
    category_id int,

)






	