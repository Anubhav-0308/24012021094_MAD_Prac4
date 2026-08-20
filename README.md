# ⏰ Alarm Reminder App

https://github.com/user-attachments/assets/8e382eec-bd93-4fcf-afcc-63c430e7fa28

A simple and user-friendly **Android Alarm Application** built using **Kotlin**. This application allows users to select a time, create an exact alarm, and play an alarm sound when the selected time is reached.

## 📱 Features

* ⏰ Create an alarm using a **Time Picker**
* 🕒 Display the selected alarm time
* 🔔 Schedule an exact alarm using **AlarmManager**
* 📢 Receive the alarm event using **BroadcastReceiver**
* 🎵 Play alarm sound using **MediaPlayer**
* ❌ Cancel an active alarm
* 📱 Simple and attractive user interface
* 🔄 Uses a **Service** to manage alarm sound

## 🛠️ Technologies Used

* **Kotlin**
* **Android Studio**
* **XML**
* **AlarmManager**
* **BroadcastReceiver**
* **Service**
* **MediaPlayer**
* **PendingIntent**
* **Material CardView**

## 📂 Project Structure

```text
app/
│
├── java/com/example/a24012021094_anubhav_prac4/
│   ├── MainActivity.kt
│   ├── AlarmBroadcastReceiver.kt
│   └── AlarmService.kt
│
└── res/
    ├── drawable/
    │   ├── img.png
    │   └── img1.png
    │
    ├── raw/
    │   └── alarm.mp3
    │
    └── layout/
        └── activity_main.xml
```

## ⚙️ How It Works

### 1. Create Alarm

The user clicks the **Create Alarm** button.

```text
Create Alarm
      ↓
TimePickerDialog Opens
      ↓
User Selects Time
      ↓
AlarmManager Schedules Alarm
      ↓
BroadcastReceiver Receives Broadcast
      ↓
AlarmService Starts
      ↓
Alarm Sound Plays 🔊
```

### 2. Cancel Alarm

The user can cancel the scheduled alarm using the **Cancel Alarm** button.

```text
Cancel Alarm
      ↓
AlarmManager Cancels Pending Alarm
      ↓
STOP Broadcast Sent
      ↓
AlarmService Stops
      ↓
Alarm Sound Stops 🔇
```

## 🧩 Components Used

### 📌 MainActivity

`MainActivity` is responsible for:

* Displaying the application interface
* Opening the `TimePickerDialog`
* Getting the selected alarm time
* Scheduling the alarm using `AlarmManager`
* Cancelling the alarm

### 📌 AlarmBroadcastReceiver

`AlarmBroadcastReceiver` receives the broadcast when the alarm time is reached.

It checks the action value:

* `START_VAL` → Starts the alarm service
* `STOP_VAL` → Stops the alarm service

### 📌 AlarmService

`AlarmService` is responsible for playing the alarm sound.

It uses:

```kotlin
MediaPlayer.create(this, R.raw.alarm)
```

The sound starts when the alarm service starts.

## 🔐 Required Permissions

For scheduling exact alarms on supported Android versions:

```xml
<uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM" />
```

## 🚀 Installation

1. Clone this repository:

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

2. Open the project in **Android Studio**.

3. Allow Gradle to sync.

4. Make sure the alarm audio file is available inside:

```text
app/src/main/res/raw/alarm.mp3
```

5. Run the application on an Android device or emulator.

## 🎯 Learning Concepts

This project demonstrates the use of:

* Android Services
* Broadcast Receivers
* AlarmManager
* PendingIntent
* TimePickerDialog
* MediaPlayer
* Kotlin Android Development
* Android Permissions

## 📸 Application Screens

The application has two main states:

### 🕐 Create Alarm Screen

Users can select and create an alarm time.

### 🔔 Alarm Created Screen

After creating an alarm, the selected alarm time is displayed along with a **Cancel Alarm** button.

## 👨‍💻 Author

**Anubhav Kanthariya**

B.Tech Information Technology Student
Ganpat University

## ⭐ Support

If you like this project, please consider giving the repository a **Star ⭐**!
