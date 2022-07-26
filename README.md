# THE RESTAURANT

<div style="display: inline-block">
  <img align="center" alt="Java-Logo" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg">
  <img align="center" alt="Spring-Logo" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg">
  <img align="center" alt="Postgres-Logo" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original.svg">
</div><br>

The Restaurant is a simple restaurant web system created for both customers and employees. It was made using Java, Spring MVC, PostgreSQL and JSP.

## Pre-Configuration

This project persists the relevant data using PostgreSQL. Thus, to successfully build it, you have to create the restaurant database and the tables.

Assuming that you have PostgreSQL properly installed and configured on your machine, simple run the following commands in your terminal or in a database management system of your choice.

**OBS**: CPF is the unique identifier of brazilian citizens. It's like the SSN used in the United States.

```sql
CREATE DATABASE restaurant;

CREATE TABLE app_user(
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
	cpf CHAR(11) UNIQUE NOT NULL, 
	name VARCHAR(64) NOT NULL, 
	phone_number VARCHAR(20) NOT NULL, 
	email VARCHAR(255) NOT NULL UNIQUE, 
	password VARCHAR(64) NOT NULL, 
	type VARCHAR(32) NOT NULL CHECK (type IN ('admin', 'customer'))
);

CREATE TABLE Dish(
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
	name VARCHAR(64) NOT NULL, 
	description TEXT NOT NULL, 
	price DECIMAL(12,2) NOT NULL
);

CREATE TABLE Payment_method(
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
	name VARCHAR(255) NOT NULL
);

CREATE TABLE app_order(
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
	id_user INT REFERENCES app_user, 
	id_dish INT REFERENCES Dish, 
	id_payment INT REFERENCES Payment_method, 
	date_time TIMESTAMP NOT NULL, 
	price DECIMAL(12,2) NOT NULL, 
	note TEXT NOT NULL
);
``` 

**OBS**: After that, you have to configure the database connection on your IDE. If you're using Intellij IDEA, just [follow these steps](https://www.jetbrains.com/help/idea/configuring-database-connections.html). If you're using Eclipse or Netbeans, [follow these ones](https://www.enterprisedb.com/postgres-tutorials/how-connect-postgres-database-using-eclipse-and-netbeans).

## Creating An Administrator
In this application, we have two types of users: customers and administrators. To perform reserved operations, such as registering dishes and payment methods, it is necessary that we create an administrator user. In the example below, we are creating one called admin (very creative), but you can name it whatever you want.

```sql
INSERT INTO app_user(cpf, name, phone_number, email, password, type)  
VALUES('12345678910', 'Administrator', '11223344556', 'admin@hotmail.com', 'admin', 'admin');
```

And, that's pretty much it. After follow these steps, you're ready to run the project and enjoy it!

