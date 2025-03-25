<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ProdManager - README</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 40px;
            max-width: 800px;
        }
        h1, h2 {
            color: #2c3e50;
        }
        code {
            background: #f4f4f4;
            padding: 2px 6px;
            border-radius: 4px;
        }
        pre {
            background: #f4f4f4;
            padding: 10px;
            border-radius: 4px;
            overflow-x: auto;
        }
    </style>
</head>
<body>
    <h1>ProdManager</h1>
    <p><strong>ProdManager</strong> is a backend application for managing products and categories, built with Spring Boot, PostgreSQL, and ModelMapper.</p>

    <h2>Features</h2>
    <ul>
        <li>CRUD operations for categories and products</li>
        <li>Validation using Jakarta Validation</li>
        <li>Data persistence with PostgreSQL and JPA</li>
        <li>DTO mapping with ModelMapper</li>
        <li>Spring Boot integration</li>
    </ul>

    <h2>Technologies Used</h2>
    <ul>
        <li>Java 21</li>
        <li>Spring Boot 3.4.4</li>
        <li>Spring Data JPA</li>
        <li>PostgreSQL</li>
        <li>ModelMapper</li>
        <li>Lombok</li>
    </ul>

    <h2>Installation</h2>
    <p>To run the project, follow these steps:</p>

    <h3>1. Clone the repository</h3>
    <pre><code>git clone https://github.com/your-repository/prodmanager.git</code></pre>

    <h3>2. Configure the database</h3>
    <p>Update <code>application.properties</code> with your PostgreSQL credentials:</p>
    <pre><code>spring.datasource.url=jdbc:postgresql://localhost:5432/prodmanager
spring.datasource.username=your_username
spring.datasource.password=your_password</code></pre>

    <h3>3. Build and run the application</h3>
    <pre><code>mvn spring-boot:run</code></pre>

    <h2>API Endpoints</h2>
    <h3>Category</h3>
    <ul>
        <li><code>POST /categories</code> - Create a new category</li>
        <li><code>GET /categories</code> - Retrieve all categories</li>
        <li><code>GET /categories/{id}</code> - Retrieve a category by ID</li>
        <li><code>PUT /categories/{id}</code> - Update an existing category</li>
        <li><code>DELETE /categories/{id}</code> - Delete a category</li>
    </ul>

    <h3>Product</h3>
    <ul>
        <li><code>POST /products</code> - Create a new product</li>
        <li><code>GET /products</code> - Retrieve all products</li>
        <li><code>GET /products/{id}</code> - Retrieve a product by ID</li>
        <li><code>PUT /products/{id}</code> - Update an existing product</li>
        <li><code>DELETE /products/{id}</code> - Delete a product</li>
    </ul>

    <h2>License</h2>
    <p>This project is licensed under the MIT License.</p>
</body>
</html>
