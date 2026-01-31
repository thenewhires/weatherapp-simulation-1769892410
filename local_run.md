# Running the WeatherApp Locally

## Prerequisites

*   Java Development Kit (JDK) 17 or higher
*   Node.js and npm
*   An OpenWeatherMap API key (sign up at [https://openweathermap.org/](https://openweathermap.org/))

## Backend (Java Spring)

1.  Navigate to the `backend` directory.
2.  Set the `OPEN_WEATHER_API_KEY` environment variable in your shell or IDE's run configuration.  This is important.  You must pass your OpenWeather API key to the Spring Boot backend
3.  Build the project using Maven:

    ```bash
    mvn clean install
    ```
4.  Run the Spring Boot application:

    ```bash
    mvn spring-boot:run
    ```

    The backend should start on port 8080.

## Frontend (React)

1.  Navigate to the `frontend` directory.
2.  Install dependencies:

    ```bash
    npm install
    ```
3.  Start the React development server:

    ```bash
    npm start
    ```

    The frontend should start on port 3000.

## Accessing the Application

Open your web browser and navigate to `http://localhost:3000`.

**Important Note:** You need to set the `OPEN_WEATHER_API_KEY` environment variable for the backend to function correctly.