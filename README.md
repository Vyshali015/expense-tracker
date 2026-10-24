# Expense Tracker

A Spring Boot REST API with a simple web dashboard to record and monitor income and expenses.

## Features
- Add, view, update, delete transactions (income / expense)
- Categorize transactions (default categories are created on first run)
- Reports: income/expense/balance summary, expenses by category, monthly expenses
- Built-in dashboard at `/`

## Tech Stack
Java 17, Spring Boot 3, Spring Data JPA (Hibernate), REST API, MySQL

## Setup
1. Install JDK 17+, Maven, MySQL
2. Set your MySQL password in `src/main/resources/application.properties`
3. Run: `mvn spring-boot:run`
4. Open http://localhost:8081

The database `expense_db` and tables are created automatically.

## REST API
| Method | Endpoint | Description |
|---|---|---|
| GET | /api/transactions | List all transactions |
| POST | /api/transactions | Add a transaction |
| PUT | /api/transactions/{id} | Update a transaction |
| DELETE | /api/transactions/{id} | Delete a transaction |
| GET | /api/categories | List categories |
| POST | /api/categories | Add a category |
| DELETE | /api/categories/{id} | Delete a category |
| GET | /api/reports/summary | Total income, expense, balance |
| GET | /api/reports/by-category | Expenses grouped by category |
| GET | /api/reports/monthly?year=2026 | Expenses grouped by month |

Example request body for `POST /api/transactions`:
```json
{
  "title": "Lunch",
  "amount": 250.50,
  "type": "EXPENSE",
  "date": "2026-10-10",
  "category": { "id": 1 }
}
```

## Author
YOUR NAME
