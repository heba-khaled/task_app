# User Data Management App

## Task Overview

This application is designed to manage user data. It consists of two primary screens:

### 1. Input Screen (First Screen)
**Functionality:**  
This screen allows the user to enter their information and save it to a local database.

### 2. Display Screen (Second Screen)
**Functionality:**  
This screen retrieves and displays the saved user data from the database.

## Features

- **Input Data Validation:**  
  All fields in the input screen are validated before data is saved.
  
- **Data Persistence:**  
  All user data is saved into a local database using Room for storage.
  
- **Display of Saved Data:**  
  A list of all saved users is presented on the display screen.

## Technical Details

- **Framework:** Android Native Framework
- **Programming Language:** Kotlin
- **Database:** Room Database
- **Dependency Injection:** Dagger Hilt

  ## Architecture Overview

The application follows a clean architecture pattern with three main layers:

### 1. Data Layer
- Utilizes **Room** for persistence requests.
- Handles data retrieval and storage in the local database.

### 2. Domain Layer
- Contains **business logic**, models, and **repository interfaces**.
- Defines the operations and interactions that will be performed on the data.

### 3. Presentation Layer
- Handles **UI** components (e.g., activities, fragments).
- The **ViewModel** layer is responsible for managing the UI state and interacting with the domain layer.


## Installation

To run the application locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/heba-khaled/task_app.git
