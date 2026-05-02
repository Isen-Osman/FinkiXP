# FinkiXP - Gamified Learning Platform

FinkiXP is a modern web application designed to enhance the learning experience through gamification. The platform allows for subject and task management while tracking user progress via an interactive leaderboard.

## 🚀 Quick Start with Docker

The entire ecosystem (Backend, Frontend, and Database) can be launched with a single command.

### Prerequisites
* [Docker](https://www.docker.com/get-started) installed
* [Docker Compose](https://docs.docker.com/compose/install/) installed

### Launch Command
Run the following command from the root directory of the project:

```bash
docker-compose up -d --build
```

Once the containers are running, you can access the services at:
* **Frontend:** [http://localhost:3001](http://localhost:3001)
* **Backend API:** [http://localhost:8080](http://localhost:8080)
* **Swagger UI Documentation:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🛠 Tech Stack

### Backend
* **Java 17** with **Spring Boot 3.5.4**
* **Spring Data JPA** for persistence
* **Spring Security** with **JWT** for authentication
* **PostgreSQL** as the primary database
* **Lombok** for clean, concise code

### Frontend
* **React 19** powered by **Vite**
* **TailwindCSS 4** for modern styling
* **React Router 7** for seamless navigation
* **Axios** for API communication

---

## 🏗 Docker Architecture

The project utilizes three main services:
1.  **`finkixp-db`**: A PostgreSQL 15 database instance exposed on port `2345`.
2.  **`finkixp-backend`**: The Spring Boot application, configured with a healthcheck to ensure the database is ready before initialization.
3.  **`finkixp-frontend`**: The React application served via Nginx, optimized for Single Page Application (SPA) routing.

---

## ⚙️ Local Development (Manual)

If you prefer to run the services individually:

### Backend
```bash
cd backend
./mvnw spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm run dev
```
