# INCREDIBLE VPN

Android app preview and Founder web dashboard.

## Build
The GitHub Actions workflow builds a **debug APK** and uploads it as a short-lived workflow artifact. This is a test app, not a production VPN.

## Founder dashboard security
- Founder sign-in uses Firebase Authentication, not a hardcoded website password.
- A signed-in user is granted Founder UI access only when Firestore document `users/{uid}` contains `role: "founder"`.
- This client check is supplementary. Apply the Firestore rules below so authorization is enforced by Firebase on every read; never rely on hiding a web page.
- Remove the previous shared password from circulation and use Firebase password reset to establish a new private credential.
- Configure the Founder user document manually in Firebase Console, with the UID of the one Founder account.

Example Firestore rules (replace the project-wide rules only after reviewing your existing app data model):
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    function signedIn() { return request.auth != null; }
    function isFounder() {
      return signedIn() &&
        get(/databases/$(database)/documents/users/$(request.auth.uid)).data.role == 'founder';
    }
    match /users/{userId} {
      allow read: if signedIn() && (request.auth.uid == userId || isFounder());
      allow create: if signedIn() && request.auth.uid == userId;
      allow update: if signedIn() && (request.auth.uid == userId || isFounder());
      allow delete: if isFounder();
    }
    match /servers/{serverId} {
      allow read: if signedIn();
      allow write: if isFounder();
    }
    match /{document=**} {
      allow read, write: if false;
    }
  }
}
```
IMPORTANT: The rules above are a template. Review existing collections and test user self-update fields; do not allow users to set or edit their own `role`.

## Current limitations
- APK button is a visual demo. No Android VpnService, WireGuard tunnel, VPN server, key provisioning, kill switch, or traffic routing is implemented.
- Authentication for the Founder page requires Firebase Authentication to be enabled and the Founder UID/role document to be provisioned.
- The Founder dashboard reports account data only. Payments, earnings, and withdrawals are not real until a payment provider and trusted server-side verification are integrated.
- Do not publish Firebase service-account keys or private server credentials.
