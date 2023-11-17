# Product API

## Structure Project

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── aocfazz
│   │           └── productAPI
│   │               ├── config
│   │               │   └── AuthFilter.java
│   │               ├── controller
│   │               │   └── GreetingController.java
│   │               ├── exception
│   │               ├── model
│   │               ├── payload
│   │               │   ├── req
│   │               │   └── res
│   │               ├── repository
│   │               ├── service
│   │               └── ProductApiApplication.java
│   └── resources
│       ├── static
│       ├── templates
│       └── application.properties
└── test
```

## How to Run
`.\mvnw spring-boot:run`

## API 

### Transactions:
- **Get All Transactions:**
  - **GET** `/transactions`
- **Get a Specific Transaction:**
  - **GET** `/transactions/{transactionID}`
- **Create a New Transaction:**
  - **POST** `/transactions`
- **Update a Transaction:**
  - **PUT** `/transactions/{transactionID}`
- **Delete a Transaction:**
  - **DELETE** `/transactions/{transactionID}`

### Orders:

- **Get All Orders:**
  - **GET** `/orders`
- **Get Orders for a Specific Transaction:**
  - **GET** `/transactions/{transactionID}/orders`
- **Get Details of a Specific Order:**
  - **GET** `/orders/{orderID}`
- **Create a New Order:**
  - **POST** `/transactions/{transactionID}/orders`
- **Update an Order:**
  - **PUT** `/orders/{orderID}`
- **Delete an Order:**
  - **DELETE** `/orders/{orderID}`

### Products:

- **Get All Products:**
  - **GET** `/products`
- **Get Details of a Specific Product:**
  - **GET** `/products/{productID}`
- **Create a New Product:**
  - **POST** `/products`
- **Update a Product:**
  - **PUT** `/products/{productID}`
- **Delete a Product:**
  - **DELETE** `/products/{productID}`

### Customer/User:

- **Get All Customers/Users:**
  - **GET** `/customers`
- **Get Details of a Specific Customer/User:**
  - **GET** `/customers/{userID}`
- **Create a New Customer/User:**
  - **POST** `/customers`
- **Login Customer/User:**
  - **POST** `/customers/login`
- **Update a Customer/User:**
  - **PUT** `/customers/{userID}`
- **Delete a Customer/User:**
  - **DELETE** `/customers/{userID}`

## Sample Dummy Data

### Transactions
```
| TransactionID | Date                | TotalAmount | Status | CustomerUserID |
| ------------- | ------------------- | ----------- | ------ | -------------- |
| 201           | 2023-11-15 10:30:00 | 1200.00     | Paid   | 1              |
| 202           | 2023-11-15 11:45:00 | 500.00      | Paid   | 2              |
```

### Orders
```
| OrderID | TransactionID | ProductID | Quantity | Price  |
| ------- | ------------- | --------- | -------- | ------ |
| 301     | 201           | 101       | 1        | 800.00 |
| 302     | 201           | 103       | 2        | 200.00 |
| 303     | 202           | 102       | 1        | 400.00 |
```

### Products
```
| ProductID | Name       | Description                | Price  | Quantity |
| --------- | ---------- | -------------------------- | ------ | -------- |
| 101       | Laptop     | High-performance laptop    | 800.00 | 50       |
| 102       | Smartphone | Latest smartphone model    | 400.00 | 100      |
| 103       | Headphones | Noise-canceling headphones | 100.00 | 200      |
```

### Customer/Users
```
| UserID | FirstName | LastName | Email                  | Address                | Username   | Password            |
| ------ | --------- | -------- | ---------------------- | ---------------------- | ---------- | ------------------- |
| 1      | John      | Doe      | john.doe@example.com   | 123 Main St, Cityville | john_doe   | hashed_password_123 |
| 2      | Jane      | Smith    | jane.smith@example.com | 456 Oak St, Townsville | jane_smith | hashed_password_456 |
```