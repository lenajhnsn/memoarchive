# The Memo Archive App

## Overview
The Memo Archive is a Java-based full-stack application designed to create digital timelines of personal memories. Users can add photos, stories, and (future support for) voice memos to their timelines. The app architecture allows for future timeline collaboration by enabling users to invite others to contribute to shared timelines.

## Key Features

- **Memory Capture:** Users can add photos and text-based memories, with planned support for voice memos.
- **Authentication & Security:** Secure login with JWT authentication.

### Planned Enhancements
While currently at an MVP stage, the application is architected to support the following features:
- **Collaboration:** Allows controlled access for others to contribute to a user's timeline.
- **Search and Tagging:** Users can filter memories based on date, tags, or contributors.

## Tech Stack
- **Languages:** Java, JavaScript (ES6+), SQL, HTML5, CSS
- **Frameworks/Libraries:** Spring Boot, Vue.js, JWT for authentication
- **Database:** PostgreSQL
- **Tools & APIs:** Git, RESTful APIs, Maven, Swagger for API documentation

## Setup and Installation

### Prerequisites
Ensure you have the following installed:
- Java 17 or later
- PostgreSQL database
- Git
- Maven
- Node.js and npm

## Backend Setup
### Clone the Repository
1. git clone [https://github.com/your-username/memo-archive.git](https://github.com/lenajhnsn/memoarchive.git)
2. cd memoarchive/server

### Configure the Database
##### File Path: memoarchive/server/src/main/resources/application.properties
1. Create a new PostgreSQL database named `memoarchive`
2. Update `application.properties` with your database credentials

>Example application.properties  
>spring.datasource.url=jdbc:postgresql://localhost:5432/memoarchive  
>spring.datasource.username=your_db_user  
>spring.datasource.password=your_db_password  
>spring.jpa.hibernate.ddl-auto=update  

### Run Database Scripts (Schema + Mock Data)
***Replace YOUR_DB_USER with your actual PostgreSQL username***
##### File Paths: memoarchive/server/database/memoarchive.sql and memoarchive/server/database/MemoArchiveMockData.sql
1. psql -U YOUR_DB_USER -d memoarchive -f database/memoarchive.sql
2. psql -U YOUR_DB_USER -d memoarchive -f database/MemoArchiveMockData.sql

### Build and Run the Server
1. mvn clean install
2. mvn spring-boot:run

### The backend API should now be running at:
http://localhost:8080

## Frontend Setup
### Navigate to the client folder
cd ../client

### Install Dependencies
npm install

### Start Development Server
npm run dev

### The application will be available at:
http://127.0.0.1:5173/

### Default User Credentials
To test with preloaded data, use the following login credentials:
- Username: lenajhnsn
- Password: password

### File Paths for Frontend
- Main Entry Point: memoarchive/client/src/App.vue

### API Documentation
Swagger documentation is available when the server is running at:
http://localhost:8080/swagger-ui/index.html

### Screenshots
<img width="1348" alt="MemoArchive Screenshots" src="https://github.com/user-attachments/assets/d3971c27-313e-4132-942d-28b6daa26883" />
