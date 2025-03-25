# ProdManager

**ProdManager** is a backend application for managing products and categories, built with Spring Boot, PostgreSQL, and ModelMapper.

## 🚀 Features
- CRUD operations for categories and products
- Validation using Jakarta Validation
- Data persistence with PostgreSQL and JPA
- DTO mapping with ModelMapper
- Spring Boot integration

## 🛠 Technologies Used
- **Java 21**
- **Spring Boot 3.4.4**
- **Spring Data JPA**
- **PostgreSQL**
- **ModelMapper**
- **Lombok**

## 🏗 Installation

### 1️⃣ Clone the repository
```sh
git clone https://github.com/maharyfenitra/ProdManager-Core.git
```

### 2️⃣ Configure the database
Edit the `application.properties` file and update your PostgreSQL credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/prodmanager
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3️⃣ Build and run the application
```sh
mvn spring-boot:run
```

## 📡 API Endpoints

### 📂 Category
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/categories` | Create a new category |
| `GET` | `/categories` | Retrieve all categories |
| `GET` | `/categories/{id}` | Retrieve a category by ID |
| `PUT` | `/categories/{id}` | Update an existing category |
| `DELETE` | `/categories/{id}` | Delete a category |

### 📦 Product
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/products` | Create a new product |
| `GET` | `/products` | Retrieve all products |
| `GET` | `/products/{id}` | Retrieve a product by ID |
| `PUT` | `/products/{id}` | Update an existing product |
| `DELETE` | `/products/{id}` | Delete a product |

## 📝 License
This project is licensed under the **MIT License**.
