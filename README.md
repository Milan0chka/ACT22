
# Android Application - README

## Overview

This Android application provides an organized structure and functionality for users. The project is built using Kotlin and follows standard Android development practices, ensuring maintainability and scalability.

### Key Features
- **Main Activity**: The app launches with `MainActivity`, which is the entry point of the application.
- **Backup Support**: The app includes backup and restore functionality via `dataExtractionRules` and `fullBackupContent`.
- **Custom Theme**: The application uses a custom theme defined as `Theme.ACT22`.
- **Icons**: The app has custom launcher icons defined in the `mipmap` directory (`ic_launcher`, `ic_launcher_round`).

## Project Structure

- **build.gradle.kts**: Project build configuration using Kotlin script.
- **proguard-rules.pro**: ProGuard configuration file for code shrinking, obfuscation, and optimization.
- **src/main**:
  - **AndroidManifest.xml**: Describes essential information about the app, such as permissions, services, and activities.
  - **java**: Contains the source code for activities, fragments, and other components.
  - **res**: Includes all the resources like layouts, images, strings, and themes used in the app.

## Key Kotlin Files

1. **MainActivity.kt**: Sets up the main activity of the app, initializing the navigation controller and configuring the app's primary layout.
2. **Routes.kt**: Defines navigation routes for various screens.
3. **MainPage.kt**: Contains the composables and logic for rendering the main content page.
4. **MainPageUI.kt**: Handles the user interface elements and interactions for the main page.
5. **LogInUI.kt**: Manages the UI for the login screen where users can input credentials to log into the app.
6. **SignInPage.kt**: Provides functionality for handling sign-in operations.
7. **SignUpPage.kt**: Contains the logic and UI for the user registration process.
8. **StartPage.kt**: Serves as the initial page displayed to the user, setting the tone for the app.
9. **Color.kt**: Defines the custom colors used throughout the app's theme.
10. **Theme.kt**: Establishes the app's theme, including light and dark modes.
11. **Type.kt**: Contains typography settings used across the app's user interface.

## Getting Started

### Prerequisites
- Android Studio Dolphin or higher.
- Minimum SDK version: 21 (Android 5.0, Lollipop)
- Target SDK version: 31 (Android 12)

### Installation

1. Clone the repository or download the project zip file.
2. Open the project in Android Studio.
3. Sync the Gradle files by clicking "Sync Now" when prompted.
4. Run the app on an emulator or connected Android device.

### Build and Run

- To build the project, navigate to **Build > Make Project** or press `Ctrl + F9`.
- To run the project, use the **Run** button or `Shift + F10`.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
