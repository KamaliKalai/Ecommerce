

---

# 🛍️ E-Commerce Web Application

## Introduction

This is a **Spring Boot E-Commerce Web Application** that allows users to browse products, register, log in, and make purchases. Admins can manage products and view all customer orders.

---

## Features

### User Features

* Register and log in
* View products
* Purchase products
* View order history

### Admin Features

* Admin login
* Add / Edit / Delete products
* View all user orders

---

## Technologies Used

| Layer      | Technology              |
| ---------- | ----------------------- |
| Frontend   | Thymeleaf, HTML, CSS    |
| Backend    | Spring Boot (MVC + JPA) |
| Database   | MySQL                   |
| Build Tool | Maven                   |
| Server     | Tomcat (embedded)       |
| IDE        | Eclipse IDE             |

---

## Workflow

### User Workflow

* Register or log in
* Browse available products
* Purchase products
* View order history

### Admin Workflow

* Log in as admin
* Add, edit, or delete products
* View all user orders

---

## Spring Boot Initialization

* Go to [https://start.spring.io](https://start.spring.io)
* Project: Maven, Language: Java
* Group: `com.example`, Artifact: `ecommerce`, Name: `E-Commerce`
* Packaging: Jar, Java Version: 17
* Add Dependencies: Spring Web, Spring Data JPA, MySQL Driver, Thymeleaf, Spring Boot DevTools
* Generate → Download → Import into Eclipse as **Existing Maven Project**

---

## Project Structure

```
com/example/ecommerce
├── controller
│   ├── AdminController.java
│   ├── AuthController.java
│   └── UserController.java
├── dao
│   ├── OrderRepo.java
│   ├── ProductRepo.java
│   └── UserRepo.java
├── model
│   ├── Order.java
│   ├── Product.java
│   └── User.java
├── service
│   ├── OrderService.java
│   ├── ProductService.java
│   └── UserService.java
├── EcommerceApplication.java
└── resources
    ├── static/css
    │   ├── style.css
    │   └── welcome.css
    ├── templates
    │   ├── User_dashboard.html
    │   ├── admin_add_product.html
    │   ├── admin_dashboard.html
    │   ├── admin_login.html
    │   ├── admin_view_orders.html
    │   ├── admin_view_products.html
    │   ├── confirm_buy.html
    │   ├── login.html
    │   ├── register.html
    │   ├── user_orders.html
    │   └── welcome.html
    └── application.properties
test/java/com/example/ecommerce
.gitignore
.gitattributes
mvnw
mvnw.cmd
pom.xml
```

---

## Database Setup

```sql
CREATE DATABASE ecommerce_db;
INSERT INTO users (username, password, role) VALUES ('admin', 'admin123', 'ADMIN');
```

---

## Application Properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=Algoritz@123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.thymeleaf.cache=false
server.port=8080
```

---

## Default Admin Login

* **Username:** admin
* **Password:** admin123

---

## Run the Application

* Open terminal → Navigate to project folder

```bash
mvn spring-boot:run
```

* Open browser: [http://localhost:8080/](http://localhost:8080/)

---

## User Login Workflow

* Login at `/login`
* Browse products, buy items, view orders

---

## Admin Login Workflow

* Login at `/admin/login`
* Add/Edit/Delete products, view all orders

---




