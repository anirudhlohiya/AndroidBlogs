# IT643- Assignment 1

## 1. Application Architecture: Clean Architecture

* **Object:** Clean Architecture (layered design).
* **Context:** The foundational structure organizing the entire application's codebase for maintainability and separation of concerns.
* **Information Important as per the Context:**
    * **Layers:** Divides the app into Presentation, Domain, and Data layers to isolate business rules from UI and data details.
    * **Dependency Rule:** Enforces a one-way dependency flow (inward), making core logic independent and highly testable.

## 2. User Interface: Jetpack Compose

* **Object:** Jetpack Compose (declarative UI toolkit).
* **Context:** Building the entire user interface of the AndroidBlogs application.
* **Information Important as per the Context:**
    * **Declarative Paradigm:** UI is described by *what it should look like* based on state, rather than *how to change it*, simplifying UI development.
    * **State-Driven:** UI automatically updates when underlying data (`State`) changes, ensuring reactivity and efficiency.

## 3. Data Management: Repository Pattern with Offline-First

* **Object:** `BlogRepository` (interface and implementation) leveraging remote (Ktor) and local (Room) data sources.
* **Context:** Handling all data operations for blogs, providing content to the UI regardless of network availability.
* **Information Important as per the Context:**
    * **Unified Access:** Provides a single, clean API for the domain layer to access data, abstracting where data comes from.
    * **Offline Support:** Prioritizes fetching from the network but gracefully falls back to displaying cached data from the Room database if the network is unavailable.

## 4. Dependency Injection: Koin

* **Object:** Koin (dependency injection framework).
* **Context:** Managing and providing instances of various components and services throughout the application.
* **Information Important as per the Context:**
    * **Decoupling:** Reduces hardcoded dependencies, making components more modular and easier to test in isolation.
    * **Simplified Setup:** Utilizes concise modules (`KoinMainModule`) to define how dependencies like ViewModels, repositories, and network clients are created and provided.
