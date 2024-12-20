# 🏪 Inventory Management System

A robust Spring Boot REST API for managing inventory, products, and suppliers. This system provides comprehensive inventory management capabilities including stock tracking, barcode-based lookups, and automated reporting.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📑 Table of Contents
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Getting Started](#-getting-started)
- [API Documentation](#-api-documentation)
- [Project Structure](#-project-structure)
- [Configuration](#-configuration)
- [Docker Support](#-docker-support)
- [Testing](#-testing)
- [Contributing](#-contributing)

## ✨ Features

### Product Management
- Create, read, update, and delete products
- Track product inventory levels
- Barcode-based product lookup system
- Automated low stock alerts

### Supplier Management
- Complete CRUD operations for suppliers
- Supplier contact information management
- Supplier relationship tracking

### Inventory Control
- Real-time stock level monitoring
- Automated low stock notifications
- Stock update functionality
- Minimum stock level configuration

### Reporting System
- Weekly inventory status reports
- Stock level alerts and notifications
- Custom report generation

## 🛠 Technology Stack

- **Backend Framework:** Spring Boot 3.0
- **Language:** Java 17
- **Build Tool:** Maven
- **API Documentation:** Swagger/OpenAPI
- **Logging:** SLF4J & Logback
- **Containerization:** Docker
- **Testing:** JUnit 5, Mockito

## 🚀 Getting Started

### Prerequisites
- JDK 17 or higher
- Maven 3.6+
- Docker (optional)

### Local Development Setup

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/inventory-management.git
cd inventory-management
```

2. **Build the application**
```bash
mvn clean install
```

3. **Run the application**
```bash
mvn spring:boot run
```

The application will be available at `http://localhost:8080`

## 📚 API Documentation

### Product Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/inventory/products` | Create a new product |
| GET | `/api/inventory/products` | Get all products |
| GET | `/api/inventory/products/{id}` | Get product by ID |
| GET | `/api/inventory/products/barcode/{barcode}` | Get product by barcode |
| PUT | `/api/inventory/products/{id}` | Update product |
| DELETE | `/api/inventory/products/{id}` | Delete product |
| PUT | `/api/inventory/products/{id}/stock` | Update stock level |

### Supplier Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/inventory/suppliers` | Create a new supplier |
| GET | `/api/inventory/suppliers` | Get all suppliers |
| GET | `/api/inventory/suppliers/{id}` | Get supplier by ID |
| PUT | `/api/inventory/suppliers/{id}` | Update supplier |
| DELETE | `/api/inventory/suppliers/{id}` | Delete supplier |

### Report Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/inventory/report` | Generate inventory report |

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/inventory/
│   │       ├── controller/
│   │       │   ├── HealthController.java
│   │       │   └── InventoryController.java
│   │       ├── model/
│   │       │   ├── Product.java
│   │       │   └── Supplier.java
│   │       ├── service/
│   │       │   └── InventoryService.java
│   │       └── exception/
│   │           ├── ErrorResponse.java
│   │           ├── GlobalExceptionHandler.java
│   │           └── ResourceNotFoundException.java
│   └── resources/
│       └── application.properties
```

## ⚙️ Configuration

### Application Properties
```properties
server.port=${PORT:8080}
server.error.include-message=always
server.error.include-binding-errors=always
server.error.include-stacktrace=always
logging.level.root=INFO
logging.level.com.inventory=DEBUG
```


### Running the Container
```bash
docker run -p 8080:8080 inventory-management
```

## 🧪 Testing

### Running Tests
```bash
mvn test
```

### Example Test Cases
- Product CRUD operations
- Supplier management
- Stock level alerts
- Report generation

### Manual Testing Guide
A comprehensive testing guide is available in `testing.txt` that includes curl commands for testing:
- CRUD operations for Products and Suppliers
- Stock level monitoring and alerts
- Barcode-based product lookup
- Weekly report generation

Example from testing.txt:
```bash
# Create Product:
curl -X POST http://localhost:8080/api/inventory/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "MacBook Pro M3",
    "barcode": "MAC2024",
    "price": 1999.99,
    "minStockLevel": 5,
    "currentStock": 10
  }'
```

For the complete set of test commands, please refer to `testing.txt` in the project root.

