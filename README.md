# Social Network Application Development

This repository contains the source code and documentation for a social network application developed using Android Studio, Java, and Firebase. The application aims to provide a platform for users to connect, share, and communicate seamlessly.

## Features

- **User Authentication**: Secure sign-up and login functionality using Firebase Authentication.
- **User Profiles**: Create and customize user profiles with profile pictures, bios, and other personal information.
- **Feed**: A central feed where users can post updates, images, and videos, and interact with others' posts through likes and comments.
- **Real-Time Chat**: Instant messaging feature allowing users to communicate in real-time.
- **Friend System**: Ability to add, accept, and manage friends.

## Technologies Used

- **Android Studio**: Integrated Development Environment (IDE) for Android development.
- **Java**: Programming language used for developing the Android application.
- **Firebase**: Backend services including Authentication, Firestore for database, Storage for file uploads, and Cloud Messaging for notifications.

## Getting Started

### Prerequisites

- Android Studio installed on your development machine.
- A Firebase account with a new project set up.

### Installation

1. **Clone the repository**:

   ```sh
   git clone https://github.com/Joshua-Yel/AppDevScoialNetwork.git
   cd AppdevFinals
   ```

2. **Open the project in Android Studio**:

   - Launch Android Studio.
   - Open the cloned project directory.

3. **Set up Firebase using Firebase Assistant**:

   1. **Open Firebase Assistant**:

      - Go to `Tools` > `Firebase` to open the Firebase Assistant pane on the right side of Android Studio.

   2. **Connect to Firebase**:

      - In the Firebase Assistant, select the feature you want to add (e.g., Authentication, Firestore, etc.).
      - Click on `Connect to Firebase`.
      - Sign in to your Firebase account if prompted.
      - Select your Firebase project or create a new one and click `Connect`.

   3. **Add Firebase Authentication**:

      - In the Firebase Assistant, under `Authentication`, click on `Email and Password Authentication`.
      - Click on `Add Firebase Authentication to your app`.
      - Follow the prompts to accept the changes.

   4. **Add Firestore Database**:

      - In the Firebase Assistant, under `Firestore`, click on `Set up Firestore`.
      - Click on `Add Cloud Firestore to your app`.
      - Follow the prompts to accept the changes.

   5. **Add Firebase Storage**:

      - In the Firebase Assistant, under `Storage`, click on `Upload and download files with Cloud Storage`.
      - Click on `Add Cloud Storage to your app`.
      - Follow the prompts to accept the changes.

   6. **Add Firebase Cloud Messaging**:

      - In the Firebase Assistant, under `Cloud Messaging`, click on `Set up Firebase Cloud Messaging`.
      - Click on `Add FCM to your app`.
      - Follow the prompts to accept the changes.

   7. **Sync Project with Gradle Files**:
      - After adding the required Firebase services, sync your project with Gradle files by clicking on `Sync Now` in the notification bar.

4. **Build and run the app**:
   - Build and run the app on an emulator or physical device by clicking on the green play button or using `Shift + F10`.

## Contributing

We welcome contributions to improve this project! To contribute:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature-name`
3. Make your changes and commit them: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a pull request.

## Contact

For any questions or inquiries, please contact [Jutribiana@gmail.com].
