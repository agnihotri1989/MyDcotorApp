# MyDoctorApp
An Android app for managing doctor appointments and health records.

## Features
- Book appointments
- Upload prescriptions
- Track health stats

## SmartAgent crash reporting

The app includes the local `smartagent-android-sdk` project as a library. It
installs the uncaught-exception handler at app startup and queues a crash in
Room before WorkManager sends it to the ingestion API.

1. Add the following to `local.properties` (the file is ignored by Git):

   ```properties
   SMARTAGENT_ENDPOINT_URL=http://<YOUR_COMPUTER_LAN_IP>:8000/v1/crashes
   SMARTAGENT_API_SECRET=<same value as SMARTAGENT_API_SECRET on the server>
   ```

   Do not use `localhost` for a physical Android device: it points to the
   phone. For an emulator, use `http://10.0.2.2:8000/v1/crashes` instead.

2. Start the ingestion service from `service-ingestion` so it accepts network
   connections:

   ```bash
   SMARTAGENT_API_SECRET=<your-secret> uvicorn main:app --host 0.0.0.0 --port 8000
   ```

3. Install the debug APK and trigger an uncaught Kotlin/Java exception. Check
   the service logs for `Received crash report`. HTTP is permitted only in the
   debug manifest for local testing; use an HTTPS endpoint for release builds.

## Screenshots
<p align="center">
  <img src="screenshots/1.png" width="220" />
  <img src="screenshots/2.png" width="220" />
  <img src="screenshots/3.png" width="220" />
</p>
