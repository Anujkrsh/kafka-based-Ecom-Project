# Kafka-based E-commerce Microservices

A distributed e-commerce system built with Spring Boot and Apache Kafka demonstrating event-driven architecture.

⚠️ **Work in Progress** - This project is actively being developed and enhanced.

## Tech Stack

### Backend
- **Language**: Java 21
- **Framework**: Spring Boot 3.5.10
- **Message Broker**: Apache Kafka
- **Build Tool**: Maven

### Databases
- **PostgreSQL**: Order Service, Payment Service
- **MongoDB**: Notification Service

### Key Dependencies
- Spring Kafka
- Spring Data JPA
- Spring Data MongoDB
- Jackson (JSON processing)
- Lombok
- HikariCP (Connection pooling)

## Architecture

```
Order Service → Kafka → Payment Service → Kafka → Notification Service
                  ↓                              ↗
              Notification Service
```

## Services

### Order Service
- **Port**: 8080
- **Database**: PostgreSQL
- **Function**: Creates orders and publishes `order-created` events

### Payment Service  
- **Port**: 8089
- **Database**: PostgreSQL
- **Function**: Processes payments (80% success rate) and publishes payment events

### Notification Service
- **Port**: 8081
- **Database**: MongoDB
- **Function**: Receives order events and payment events, sends notifications

## Kafka Topics

- `order-created` - Order creation events
- `payment-success` - Successful payment events
- `payment-failed` - Failed payment events
- `*.DLT` - Dead Letter Topics for failed message processing

## Key Features

- **Event-Driven Architecture**: Services communicate via Kafka events
- **Dead Letter Queue**: Failed messages sent to DLT for manual inspection
- **Retry Mechanism**: 3 retry attempts with 1-second delay
- **Database per Service**: PostgreSQL for Order/Payment, MongoDB for Notifications
- **Generic Kafka Consumer**: Single consumer configuration handles multiple event types

## Prerequisites

- Java 21
- Apache Kafka
- PostgreSQL
- MongoDB
- Maven

## Quick Start

1. Start Kafka and databases
2. Run services:
   ```bash
   mvn spring-boot:run
   ```
3. Create an order via Order Service REST API
4. Monitor logs to see event flow

## Event Flow

1. Order created → `order-created` topic
2. **Both** Payment Service and Notification Service consume order events
3. Payment Service processes payment → `payment-success/failed` topic  
4. Notification Service consumes payment events and sends notifications