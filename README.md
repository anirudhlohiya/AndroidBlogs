# AndroidBlogs

AndroidBlogs is a mobile application built with Jetpack Compose, designed to display a list of blogs. It leverages Koin for dependency injection, Ktor for network communication, and Room for local data persistence.

## Features

* **Blog Listing:** Displays a list of available blogs with their titles and thumbnail images.
    * The main screen shows a list of blogs, each with a thumbnail image and title.
    * A shimmer effect is displayed while the blogs are loading.
* **Blog Content Display:** Tapping on a blog card navigates to a detailed view showing the full blog content.
    * The detailed blog view includes a back button and the blog's title in the top bar.
    * Blog content is rendered using MarkdownText, supporting rich text formatting.
    * In case of an error loading content, an error message and a refresh button are displayed.
* **Offline Support:** Blogs are cached locally using Room, allowing for offline viewing if data was previously fetched.
* **Splash Screen:** A custom splash screen is implemented for a branded app launch experience.

## Technologies Used

* **Jetpack Compose:** Modern Android UI toolkit for building native UI.
* **Kotlin:** Programming language for Android app development.
* **Koin:** Dependency Injection framework for Kotlin applications.
* **Ktor:** Asynchronous client-side HTTP client for network requests.
* **Room:** Persistence library for local data storage.
* **Coil:** Image loading library for Android.
* **Kotlinx Serialization:** For JSON serialization and deserialization with Ktor.
* **Compose Markdown:** For rendering Markdown content in Jetpack Compose.
* **Jetpack Navigation Compose:** For managing in-app navigation.

## Architecture

The project follows a clean architecture pattern, separating concerns into distinct layers:

* **Presentation Layer:** Handles UI and user interaction using Jetpack Compose (e.g., `BlogListScreen.kt`, `BlogContentScreen.kt`). ViewModels manage UI state and logic (e.g., `BlogListViewModel.kt`, `BlogContentViewModel.kt`).
* **Domain Layer:** Contains core business logic and models, independent of any specific framework (e.g., `Blog.kt`, `BlogRepository.kt`, `Result.kt`).
* **Data Layer:** Responsible for data retrieval and persistence, abstracting the data sources (e.g., `BlogRepositoryImpl.kt`). This layer includes:
    * **Local Data Source:** Room database for caching blog data (e.g., `BlogDao.kt`, `BlogEntity.kt`, `BlogContentEntity.kt`).
    * **Remote Data Source:** Ktor for fetching blog data from a remote server (e.g., `KtorRemoteBlogDataSource.kt`, `BlogDto.kt`).
    * **Mappers:** Convert data between different representations (e.g., `BlogDtoMapper.kt`, `BlogEntityMapper.kt`).

## Screenshots

| Blog List Screen | Blog Content Screen | Interactive Task |
| :--------------: | :---------------: | :----------------: |
| ![Blog List Screen](https://raw.githubusercontent.com/anirudhlohiya/AndroidBlogs/refs/heads/master/images/ss1.jpg) | ![Blog Content Screen](https://raw.githubusercontent.com/anirudhlohiya/AndroidBlogs/refs/heads/master/images/ss2.jpg) | ![Interactive Task](https://raw.githubusercontent.com/anirudhlohiya/AndroidBlogs/refs/heads/master/images/ss3.jpg) |

## Installation and Setup

To run this project locally, follow these steps:

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/anirudhlohiya/AndroidBlogs.git
    ```
2.  **Open in Android Studio:**
    Open the cloned project in Android Studio.
3.  **Sync Gradle:**
    Allow Gradle to sync the project dependencies. Ensure you have an active internet connection for the first sync.
4.  **Run on Device/Emulator:**
    Select an Android device or emulator and run the application.

## Project Structure Highlights

* `app/src/main/java/com/anirudh/androidblogs/`: Main application source code.
    * `data/`: Data layer, including local (Room) and remote (Ktor) data sources, mappers, and repository implementation.
    * `domain/`: Domain layer, containing models, repository interfaces, and utility classes.
    * `di/`: Koin dependency injection modules.
    * `presentation/`: Presentation layer, with UI screens (composables) and ViewModels.
        * `blog_list/`: Components and logic for the blog listing screen.
        * `blog_content/`: Components and logic for the individual blog content screen.
        * `navigation/`: Defines navigation routes and the navigation graph.
        * `common_component/`: Reusable UI components like `ShimmerEffect.kt`.
        * `theme/`: Defines the app's theme, colors, and typography.
* `app/src/main/res/`: Android resources (drawables, layouts, values).
    * `drawable/`: Drawable resources, including `app_logo.png` and `error_image.png`.
    * `mipmap-anydpi-v26/`: Adaptive launcher icons.
    * `values/`: XML files for colors, strings, and themes.
        * `colors.xml`: Defines various color resources used in the app.
        * `strings.xml`: Contains string resources like `app_name`.
        * `themes.xml`: Defines the application themes.
        * `splash.xml`: Configures the splash screen theme and icon.
* `build.gradle.kts` (app level): Manages app-specific dependencies and build configurations.
* `gradle/libs.versions.toml`: Centralized dependency management for the project.
