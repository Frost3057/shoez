# Shoez

A modern e-commerce platform for shoes built with Spring Boot and Kotlin.

## 🚀 Technologies

- **Backend Framework**: Spring Boot 3.4.6
- **Language**: Kotlin 1.9.25
- **Database**: MongoDB (with Reactive support)
- **Security**: Spring Security with JWT authentication
- **Java Version**: 21

## 🛠️ Features

- RESTful API architecture
- Reactive programming support
- MongoDB integration with reactive capabilities
- JWT-based authentication
- Input validation
- Secure password handling
- Coroutine support for asynchronous operations

## 📋 Prerequisites

- JDK 21
- MongoDB
- Gradle

## 🔧 Getting Started

1. Clone the repository:
   ```bash
   git clone [repository-url]
   cd shoez
   ```

2. Configure MongoDB:
   - Ensure MongoDB is running on your system
   - Update the application properties with your MongoDB connection details

3. Build the project:
   ```bash
   ./gradlew build
   ```

4. Run the application:
   ```bash
   ./gradlew bootRun
   ```

## 🏗️ Project Structure

```
shoez/
├── src/
│   ├── main/
│   │   ├── kotlin/    # Kotlin source files
│   │   └── resources/ # Configuration files
│   └── test/          # Test files
├── build.gradle.kts   # Project dependencies and configuration
└── settings.gradle.kts
```

## 🔐 Security

The application uses Spring Security with JWT (JSON Web Token) for authentication and authorization. Make sure to:

- Set up proper JWT secret keys
- Configure security settings in application properties
- Use HTTPS in production

## 🧪 Testing

Run tests using:
```bash
./gradlew test
```

