## Project Structure

This project is organized into several packages, each responsible for a specific aspect of the application’s functionality. Below is an overview of each package and its primary files.

### Packages Overview

- **`activity`**: Contains the main activity that initializes and manages navigation within the app.
- **`data`**: Holds data models that represent key entities within the application, such as `Asset`, `Portfolio`, and `User`.
- **`ui`**: Contains UI components, grouped into sub-packages:
  - **`pages`**: Organized into `authentication` and `main` sub-packages, which contain the various screen Composables used in the app.
  - **`theme`**: Defines the app’s color scheme, typography, and other theming attributes.
- **`viewmodel`**: Contains `ViewModel` classes that manage the app’s data and handle business logic. Currently includes `AuthenticationViewModel`.

### Detailed Package and File Descriptions

#### 1. `activity`
- **MainActivity.kt**: The entry point of the application. This activity manages navigation throughout the app using Jetpack Compose’s navigation components. It initializes the start screen based on the user's login status and provides a single point for handling app-wide navigation quirks.

#### 2. `data`
- **Asset.kt**: Defines the `Asset` data model, which represents individual assets in the app (e.g., stocks or other investments).
- **Portfolio.kt**: Defines the `Portfolio` data model, representing a collection of assets associated with a user.
- **User.kt**: Represents the `User` data model, holding details specific to a user of the app.

#### 3. `ui`
- **pages**
  - **`authentication`**: Contains Composables related to user authentication, such as login and registration screens.
    - **LandingPage.kt**: The welcome screen that introduces the app to the user.
    - **SignInPage.kt**: The login screen for users to enter their credentials and sign in.
    - **SignUpPage.kt**: The registration screen where new users can create an account.
    - **PassportRecovery.kt**: Screen for users to initiate the password recovery process.
    - **NewPassword.kt**: Screen where users can set a new password after recovery.

  - **`main`**: Contains the primary screens displayed after user login.
    - **CreateMainPage.kt**: The main screen of the app where users can view summaries or key information.
    - **PortfolioBuildingPage.kt**: Screen for building and managing the user’s asset portfolio.
    - **UserPortfolio.kt**: Displays detailed information about the user's portfolio, including individual assets.

- **theme**
  - **Color.kt**: Defines the color scheme used throughout the app.
  - **Theme.kt**: Configures the app’s theme, applying colors, typography, and shape styles to the UI.
  - **Type.kt**: Contains the typography settings used across various screens in the app.

#### 4. `viewmodel`
- **AuthenticationViewModel.kt**: Handles the business logic for user authentication, including sign-up, sign-in, and Google Sign-In interactions with Firebase. It centralizes authentication-related operations, keeping the UI and business logic separate.

---
