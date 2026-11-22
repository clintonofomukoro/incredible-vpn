# INCREDIBLE VPN — Android starter project

This is a **starter project**, not a finished VPN product.

## Included
- Android application skeleton using Kotlin and Jetpack Compose
- Package name: `com.incrediblevpn.app`
- Initial home-screen visual prototype based on the supplied design
- Firebase Auth and Firestore dependencies prepared

## Required Firebase setup
1. In Firebase Console, register the Android app with package name `com.incrediblevpn.app`.
2. Download `google-services.json`.
3. Place it in this project's `app/` folder (same level as `app/build.gradle.kts`).
4. In Firebase Console, enable Authentication providers and create Firestore only after setting secure rules.

Do not upload `google-services.json` to public repositories. Never put service-account keys or server secrets in the Android app.

## Important limitations
- The connect button is a visual demo. **It does not create a VPN tunnel.**
- No WireGuard server, VPN key provisioning, Android VpnService, kill switch, IP verification, speed measurement, subscriptions, payments, ads, or founder wallet is implemented yet.
- Firebase dependencies are included, but authentication and database operations are not yet wired.
- The five-digit PIN requirement must be implemented with strong server-side protections; do not store a PIN as plaintext or use it as the only security control.
- Do not claim earnings from installs, registrations, connections, or mobile-data use unless a real sponsor, advertiser, referral partner, or paying customer has paid for that activity.
- Android direct APK distribution requires a signed APK and user approval to install. iOS distribution is different.

## Build requirements
Use Android Studio or a compatible Android Gradle environment with JDK 17 and Android SDK 35. A mobile IDE may not support every Gradle plugin or dependency; if it fails, a compatible build environment will be needed.
