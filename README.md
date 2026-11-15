# DeloitteMarket - Full-Stack E-Commerce Application

A full-stack web application built for a working student portfolio. It features a modern, responsive user interface, a Java Spring Boot backend, an H2 in-memory SQL database, and automated Playwright End-to-End (E2E) tests.

## 🚀 Features
- **User Authentication:** Login and registration with username uniqueness validation.
- **Dynamic Catalog:** Fetches 8 products from the H2 database on startup.
- **Search & Filter:** Real-time search by name/description and category filtering.
- **Shopping Cart:** Add items, update quantities, and view dynamic totals.
- **Checkout:** Saves order details (username, total amount, item count) directly to the database.
- **Global Error Handling:** A `@RestControllerAdvice` catches exceptions and returns clean, standardized JSON error responses (e.g., 401 Unauthorized, 409 Conflict).

## 🛠 Tech Stack
- **Backend:** Java 17, Spring Boot 3, Spring Data JPA, Hibernate
- **Database:** H2 (In-Memory)
- **Frontend:** HTML5, Vanilla JavaScript, Tailwind CSS, FontAwesome
- **Testing:** Playwright (Java) + JUnit 5

## ⚙️ How to Run Locally
1. Clone the repository:
   ```bash
   git clone https://github.com/SKYSCRAP/deloitte-market-app.git