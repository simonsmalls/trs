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

 abbreviation varchar(45) NOT NULL,

 firstName varchar(45) NOT NULL,

 lastName varchar (45) NOT NULL,

 pass varchar(100) NOT NULL,

 hourlyRate  numeric(9, 2),

 e_kind varchar (1)

);

create table companies

(
    companies_id int primary key default nextval('companies_seq'),
    companyName varchar(45) not null
);

create table projects

(
    projects_id int primary key default nextval('projects_seq'),
    company_id  int,
    projectName varchar(45) not null,
    description varchar(45) not null,
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
    categoryName varchar(45) not null
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
    description varchar(45),
    employee_id int,
    project_id int,
    category_id int,
    startDate date,
    startTime time,
    endTime time,
    timeSpent int,
    constraint FK_PERSONS foreign key (employee_id) references employees,
    constraint FK_PROJECTS foreign key (project_id) references projects,
    constraint FK_CATEGORIES foreign key (category_id) references categories

);


create table personRoles
(
    employee_id int,
    personRole varchar(45),
    constraint FK_EMPLOYEES foreign key (employee_id) references employees

);


insert into employees (abbreviation, firstName, lastName, pass, hourlyRate, e_kind) values ('SH', 'Simon', 'Hazevoets', 'sh123', null, 'e');
insert into employees (abbreviation, firstName, lastName, pass, hourlyRate, e_kind) values ('JH', 'Jana', 'Heitkemper', 'jh123', null, 'e');
insert into employees (abbreviation, firstName, lastName, pass, hourlyRate, e_kind) values ('JV', 'Jens', 'Verheyden', 'jv123', 100.00, 'c');
insert into employees (abbreviation, firstName, lastName, pass, hourlyRate, e_kind) values ('KW', 'Kim', 'Wauters', 'kw123', 200.00, 'c');
insert into employees (abbreviation, firstName, lastName, pass, hourlyRate, e_kind) values ('CN', 'Claudia', 'Negrila', 'cn123', null, 'e');
insert into employees (abbreviation, firstName, lastName, pass, hourlyRate, e_kind) values ('MVH', 'Marcel', 'Van Hasselt', 'mvh123', null, 'e');
insert into employees (abbreviation, firstName, lastName, pass, hourlyRate, e_kind) values ('ES', 'Esben', 'Six', 'es123', null, 'e');
insert into employees (abbreviation, firstName, lastName, pass, hourlyRate, e_kind) values ('QL', 'Quentin', 'Locht', 'ql123', 156.0, 'c');
insert into employees (abbreviation, firstName, lastName, pass, hourlyRate, e_kind) values ('admin', 'admin', 'admin', 'admin', null, 'e');


insert into companies (companyName)values ('Smals');
insert into companies (companyName)values ('KBC');
insert into companies (companyName)values ('AB inbev');
insert into companies (companyName)values ('Colruyt Group');
insert into companies (companyName)values ('Belfius');
insert into companies (companyName)values ('ING');
insert into companies (companyName)values ('Cegeka');
insert into companies (companyName)values ('PWC');


insert into projects (company_id, projectName, description, hourlyRate, startDate, endDate) values (1, 'Totale opleiding', 'projectomschrijving', 200.00,'2023-01-01', '2023-4-28');
insert into projects (company_id, projectName, description, hourlyRate, startDate, endDate) values (2, 'Python', 'abisprojectomschrijving', 100.00,'2022-10-14', '2023-02-22');
insert into projects (company_id, projectName, description, hourlyRate, startDate, endDate) values (3, 'UML', 'abisprojectomschrijving', 150.00,'2022-09-14', '2023-01-22');
insert into projects (company_id, projectName, description, hourlyRate, startDate, endDate) values (2, 'Java', 'abisprojectomschrijving', 100.00,'2022-09-14', '2023-01-22');
insert into projects (company_id, projectName, description, hourlyRate, startDate, endDate) values (2, 'Javascript', 'abisprojectomschrijving', 100.00,'2022-09-14', '2023-01-22');
insert into projects (company_id, projectName, description, hourlyRate, startDate, endDate) values (3, 'Postgress', 'abisprojectomschrijving', 125.00,'2022-09-14', '2023-01-22');
insert into projects (company_id, projectName, description, hourlyRate, startDate, endDate) values (1, 'SQL', 'abisprojectomschrijving', 295.00,'2022-09-14', '2023-01-22');

insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2022-12-21', '08:00:00', '9:00:00', 60, 3);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2022-12-22', '09:00:00', '17:00:00', 480, 3);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2022-12-23', '09:00:00', '17:00:00', 480, 3);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2022-12-24', '08:00:00', '10:00:00', 120, 3);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2022-12-24', '10:00:00', '15:00:00', 300, 3);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2022-12-25', '08:00:00', '17:00:00', 540, 3);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2022-12-25', '18:00:00', '19:00:00', 60, 3);

insert into workingtimes (workingDate, startTime, timeWorked, employee_id) values ('2022-12-23', '08:00:00', 0, 4);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2022-12-22', '09:00:00', '15:00:00', 360, 4);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2022-12-10', '09:00:00', '15:00:00', 360, 4);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2022-12-10', '15:00:00', '16:26:00', 86, 4);

-- January
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2023-01-09', '08:00:00', '17:00:00', 540, 4);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2023-01-08', '08:00:00', '14:00:00', 360, 4);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2023-01-05', '08:00:00', '08:05:00', 5, 4);


insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2023-01-05', '08:00:00', null, 0, 8);
insert into workingtimes (workingDate, startTime, endTime, timeWorked, employee_id) values ('2023-01-11', '08:00:00', null, 0, 8);


insert into categories (categoryName) values ('Les geven');
insert into categories (categoryName) values ('Cursus voorbereiding');
insert into categories (categoryName) values ('Vergadering');
insert into categories (categoryName) values ('Administratie');
insert into categories (categoryName) values ('Accounting');
insert into categories (categoryName) values ('Sales');
insert into categories (categoryName) values ('Studeren');
insert into categories (categoryName) values ('Voorbereiding eten');
insert into categories (categoryName) values ('Coderen');

insert into invoices  (invoiceDate, totalPrice, project_id, closed) VALUES ('2022-12-28', 15000.00, 1, true);
insert into invoices  (invoiceDate, totalPrice, project_id, closed) VALUES ('2022-11-28', 20000.00, 1, true);
insert into invoices  (invoiceDate, totalPrice, project_id, closed) VALUES ('2022-10-28', 18000.00, 1, true);
insert into invoices  (invoiceDate, totalPrice, project_id, closed) VALUES ('2022-9-28', 17000.00, 1, true);
insert into invoices  (invoiceDate, totalPrice, project_id, closed) VALUES ('2022-8-28', 10000.00, 1, true);

insert into activities (description, employee_id,  project_id, category_id, startDate, startTime, endTime, timeSpent) values ('uh', 3, 2, 4, '2022-12-22', '14:00:00', '15:00:00', '60');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null,  4, 2, 6, '2022-12-22', '11:00:00',  '12:30:00', '90');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values ('ja', 5, 1, 3, '2022-08-11', '13:15:00', '16:00:00', '165');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 6, 1, 5, '2022-08-10', '09:00:00',  '11:45:00', '165');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 1, 1, 5, '2023-12-26', '09:00:00',  '11:45:00', '165');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 1, 1, 5, '2023-12-26', '12:00:00', '13:45:00', '165');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 1, 1, 5, '2023-12-26', '14:00:00',  '15:45:00', '165');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 1, 2, 5, '2022-12-27', '14:00:00',  '15:45:00', '167');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 5, 1, 2, '2022-12-27', '14:00:00',  '15:45:00', '165');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 1, 1, 1, '2023-01-11', '14:00:00',  '15:00:00', '60');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 2, 1, 1, '2023-01-11', '14:00:00',  '15:00:00', '60');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 3, 1, 1, '2023-01-11', '14:00:00',  '15:00:00', '60');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 4, 1, 1, '2023-01-11', '14:00:00',  '15:00:00', '60');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 5, 1, 1, '2023-01-11', '14:00:00',  '15:00:00', '60');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 6, 1, 1, '2023-01-11', '14:00:00',  '15:00:00', '60');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 2, 1, 5, '2023-01-11', '9:00:00',  '11:00:00', '120');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 2, 1, 5, '2023-01-11', '11:00:00',  '13:00:00', '120');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 2, 7, 7, '2023-01-16', '11:00:00',  '13:00:00', '120');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 2, 7, 7, '2023-01-16', '9:00:00',  '11:00:00', '120');
insert into activities (description, employee_id, project_id, category_id, startDate, startTime, endTime, timeSpent) values (null, 2, 7, 7, '2023-01-16', '14:00:00',  '16:00:00', '120');

insert into personRoles values (1, 'Teacher');

insert into personRoles values (1, 'Manager');
insert into personRoles values (5, 'Manager');
insert into personRoles values (9, 'Manager');
insert into personRoles values (1, 'Accountant');
insert into personRoles values (3, 'Accountant');
insert into personRoles values (9, 'Accountant');






