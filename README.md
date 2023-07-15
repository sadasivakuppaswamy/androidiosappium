# androidiosappium
This will have common functionality of android and iOS automation using appium
# Mobile automation testing framework (Android and iOS) - supports testng tests

*Single code base framework to test android and iOS app using appium. 
## Prequisites:

1) Install [Java JDK8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
   and [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
2) Install [NodeJS](https://nodejs.org/en/download/)
3) Install [Android studio](https://developer.android.com/studio)
4) Install Appium Server using npm (CLI) command `npm install -g appium`. 
Appium server version 1.22.1
```
Command to check the installed appium version: `appium --version`
```
5) Install Appium plugin required and use plugin while statrting server
```
    # To install an officially supported plugin
      appium plugin install images
      appium server --use-plugins=device-farm,images
```

6) Install uiautomator2 driver
```
   appium driver install uiautomator2
```
7) Add below Android SDK path in the environment variable

```
    - ANDROID_HOME = <path to Sdk folder>
    - %ANDROID_HOME%\tools
    - %ANDROID_HOME%\tools\bin
    - %ANDROID_HOME%\platform-tools
```

6) Install [Appium desktop](https://github.com/appium/appium-desktop/releases/)
7) Install [Appium Inspector](https://github.com/appium/appium-inspector/releases)

##  Quick Start - Appium set up on MAC (Android):

1) Install Homebrew
2) Install [NodeJS](https://nodejs.org/en/download/)
3) Install [Java JDK8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
   and [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
4) Install Appium server using npm (CLI) or Appium desktop client
5) Install [Android studio](https://developer.android.com/studio)
6) Install [Appium Inspector](https://github.com/appium/appium-inspector/releases)
7) Set JAVA_HOME and ANDROID_HOME environment variables

##  Appium Doctor to verify the installations

1) Install appium-doctor using command `npm install -g appium-doctor`
2) To view the list of available options `appium-doctor --help`

```
To check Android set up `appium-doctor --android`
To check ios set up `appium-doctor --ios`
```

##  Creating Android Virtual Device (Emulator) from Android Studio:

1) Open Android Studio.
2) Click on Tools -> AVD Manager -> Create Virtual Device -> Select the device and OS version -> Finish.
3) Once Virtual device is created, click on Launch this AVD in the emulator.
4) Command to view the list of devices attached `adb devices`

##  Android Real Device Set up:

1) Connect Android real device to the machine(Desktop/Laptop)
2) Turn on the developer options in android mobile
3) Enable USB debugging
4) Run command `adb devices` in cmd prompt to check whether the device is recognised

##  Mirror android/ios device to your desktop

1) Download [Vysor](https://www.vysor.io/)

##  Start Android Emulator from Command line

1) Open command prompt, go to `<sdk emulator path>`

```
Command to stard AVD: `emulator -avd <avd_name>`
Command to stop/kill AVD: `adb -e emu kill`
```

##  Pushing the App (.apk file) to Android Emulator:

1) Copy the .apk file and paste it in the path - `<path to sdk platform-tools>`
2) Open the cmd terminal from the directory where APK file is placed and enter command `adb install <apk filename>`

##  Android - Finding appPackage and appActivity:

If the app is already installed on your device then we can make use of appPackage and appActivity to launch the app

<b> Option 1 : </b>
1) Open the app on the device, for which appPackage and appActivity is required.
2) Open powershell and enter command `adb shell dumpsys window | grep -E 'mCurrentFocus|mFocusedApp'`
NOTE: This command may not work for newer Android OS (10 or 11). In that case, use command:
   `adb shell "dumpsys activity activities | grep mResumedActivity"`

<b> Option 2 : </b>
Install <b> APK info </b> app to retrieve appPackage and appActivity for the app installed in your device

##  Inspecting Elements

### uiautomatorviewer

1) Go to the path - `<path to sdk folder>\tools\bin\`
2) click on `uiautomatorviewer`
3) On the UI Automator Viewer, click on Device Screenshot (uiautomator dump). Ui automator will capture the screenshot
   of current open screen in the device.

### Appium Inspector

1) Start the Appium Server and connect with Real device/Emulator.
2) Open Appium Inspector app and provide the appium server details and Desired Capabilities.
3) Click on Start session which will start the appium inspector with layout shown below.
   ![appium inspector](https://github.com/sadasivakuppaswamy/androidiosappium/assets/10988106/dd3ae360-060a-4d83-ab13-c909d3a08fe1)
##  Running tests through Maven

:point_right: Run test using command `mvn clean test -Dos=Android -Denv=qa`


##  Running tests through testng xml

 Create or Select the required testng xml -> Right click and select Run

##  Extent reports are added and can be accessed under target folder
![extent report](https://github.com/sadasivakuppaswamy/androidiosappium/assets/10988106/7445a00a-e4b0-4c3d-90d2-9dbc42030645)
