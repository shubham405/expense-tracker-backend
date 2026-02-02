# Expense Tracker – Backend

Spring Boot backend for a personal expense tracker application.

The backend is designed with production-like concerns in mind, such as data correctness, idempotent APIs, and safe handling of retries.

---

## ✨ Features

- Create a new expense with amount, category, description, and date
- Idempotent `POST /expenses` using `Idempotency-Key`
- List expenses via `GET /expenses`
- Optional filtering by category (case-insensitive)
- Optional sorting by date (newest first)
- Persistent storage using a file-based H2 database
- Correct money handling using `BigDecimal`
- Health check endpoint via Spring Boot Actuator

---

## 🧱 Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 (file-based, persistent)

---

## 🔁 Idempotency & Reliability

To handle retries caused by unreliable networks or browser refreshes:

- `POST /expenses` requires an `Idempotency-Key` header
- If the same request is retried with the same key, the existing expense is returned
- This prevents duplicate records even under repeated submissions

---

## 🗄️ Persistence Choice

A file-based H2 database is used:
- Simple to run locally
- Persistent across application restarts
- No external database dependency

For a production system, this could be replaced with a managed relational database and schema migrations.

---

## ▶️ Running the Application

### Prerequisites
- Java 17+

### Run locally
```bash
./mvnw spring-boot:run
