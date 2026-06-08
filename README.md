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

---

## ☸️ Kubernetes Deployment

You can deploy the entire application to a Kubernetes cluster (e.g., Docker Desktop, Minikube, or k3s).

### 1. Build Docker Images
First, build the images locally so the cluster can access them:

```bash
# Build Backend
docker build -t finkixp-backend:latest ./backend

# Build Frontend
docker build -t finkixp-frontend:latest ./frontend
```

### 2. Apply Manifests
Deploy the components in the following order:

```bash
# Create Namespace
kubectl apply -f k8s/namespace.yaml

# Apply Configs and Secrets
kubectl apply -f k8s/config-secrets.yaml

# Deploy Database
kubectl apply -f k8s/db-statefulset.yaml

# Deploy Backend
kubectl apply -f k8s/backend-deployment.yaml

# Deploy Frontend
kubectl apply -f k8s/frontend-deployment.yaml

# Apply Ingress (Optional)
kubectl apply -f k8s/ingress.yaml
```

### 3. Accessing the Application

#### Option A: Localhost (LoadBalancer)
If you are using Docker Desktop, the frontend is exposed on:
* **Frontend:** [http://localhost:3000](http://localhost:3000)

#### Option B: Ingress (Custom Domain)
If you have an Ingress Controller installed, add this to your `/etc/hosts` file:
```text
127.0.0.1 finkixp.local
```
Then access via: [http://finkixp.local](http://finkixp.local)

#### Option C: Port Forwarding
```bash
# Access Frontend
kubectl port-forward svc/finkixp-frontend 3000:3000 -n finkixp-ns

# Access Backend API
kubectl port-forward svc/finkixp-backend 8085:8085 -n finkixp-ns
```

### 🔍 Useful Commands
* **Check Status:** `kubectl get pods -n finkixp-ns`
* **View Logs:** `kubectl logs -f deployment/finkixp-backend -n finkixp-ns`
* **Delete Everything:** `kubectl delete ns finkixp-ns`
