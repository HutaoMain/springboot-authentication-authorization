## Prerequisites

- Java 17
- Maven
- MySQL

## Setup

1. Clone the repository:

   ```sh
   git clone https://github.com/HutaoMain/springboot-jwt-authentication-authorization.git
   cd springboot-authentication-authorization
   ```

## API Endpoints

### Authentication

- **Login**

  - URL: `/api/auth/login`
  - Method: `POST`
  - Request Body:
    ```json
    {
      "email": "user@example.com",
      "password": "password"
    }
    ```
  - Response Body:
    ```json
    {
      "token": "jwt-token",
      "expiresIn": 3600000
    }
    ```

- **Register**
  - URL: `/api/auth/signup`
  - Method: [POST](http://_vscodecontentref_/29)
  - Request Body:
    ```json
    {
      "email": "user@example.com",
      "password": "password",
      "firstName": "First",
      "lastName": "Last"
    }
    ```
  - Response Body:
    ```json
    {
      "id": 1,
      "firstName": "First",
      "lastName": "Last",
      "email": "user@example.com",
      "role": "USER",
      "createdAt": "2023-10-01T00:00:00.000+00:00",
      "updatedAt": "2023-10-01T00:00:00.000+00:00"
    }
    ```

## Security Configuration

The security configuration is defined in [SecurityConfiguration.java](http://_vscodecontentref_/30). It includes:

- Disabling CSRF protection.
- Allowing unauthenticated access to `/api/auth/**` endpoints.
- Requiring authentication for all other endpoints.
- Configuring JWT authentication filter.
- Configuring CORS settings.

## JWT Service

The [JwtService](http://_vscodecontentref_/31) class provides methods for generating and validating JWT tokens. It uses the [io.jsonwebtoken](http://_vscodecontentref_/32) library.

## User Service

The [UserService](http://_vscodecontentref_/33) class provides methods for loading user details from the database. It implements the [UserDetailsService](http://_vscodecontentref_/34) interface.

## Authentication Service

The [AuthenticationService](http://_vscodecontentref_/35) class provides methods for user registration and login. It uses the [UserService](http://_vscodecontentref_/36) and [JwtService](http://_vscodecontentref_/37) classes.
