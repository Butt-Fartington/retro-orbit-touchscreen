# Retro Orbit Touchscreen

Current version: **1.2.18-touch** (code 45), minimum API 26, target API 35. Tested on Samsung SM-S916U running Android 16.

A millennium-inspired touchscreen launcher: chrome/glass app icons, orbit wheel, Rajdhani typography, weather, app-grouped notification previews and pages, continuous scrolling, and momentum. Tap to open, hold for options. Swipe notification groups right to dismiss clearable alerts; left for options. Home has Apps/Options; content screens omit the Open button. The app drawer includes an A–Z drag rail with a letter indicator. A floating search orb below the wheel opens the Google app Home page (with search/browser fallbacks). Full controls are in Options → Help.

Optional Orbit Access is a visual transition after Android unlocks while the launcher is visible; it does not replace secure authentication. The separate preview uses Android biometric/device credentials. The original Neon Earthrise wallpaper is embedded at `assets/wallpapers/neon_earthrise.png`; Options → Lock-screen wallpaper can apply it.

Notification access is optional. A disconnected service requests rebinding automatically; cached snapshots keep system reads off drawing. App discovery runs in the background. No Orbit T9 keyboard is bundled in this touchscreen branch.

The main launcher lists and Help scroll; Android dialogs use native list behavior. Broad OEM/API-matrix and complete accessibility audits are still pending.

## Build

Set `ANDROID_HOME` to your Android SDK (platform 35 and build-tools 36.0.0). A JDK, zip and standard shell tools are required. Run `./build.sh`; the signed APK is `build/retro-orbit.apk`.

Supply the existing private signing key through `ORBIT_KEYSTORE`, or place it at `signing/retro-orbit.keystore`. Keys and build products are excluded from Git. The existing personal certificate uses the historical Android Debug name; APK debugging is disabled. Keep the original key to install future updates without clearing app data. The build script retains the existing signing configuration.

Both versions currently use `com.retro.launcher` and the same signing identity. These are independent source repositories, but cannot be installed side by side on one device as separate packages. Use the matching version for the device.

## Assets

Includes the user-supplied Chrome Blue artwork and generated orb graphics. Public redistribution rights for supplied artwork have not been established; no blanket open-source license is granted. Rajdhani font has its included SIL Open Font License in `assets/fonts/OFL.txt`.

`docs/DEVELOPMENT_HISTORY.md` and `VALIDATION.md` contain historical notes; they are not a claim that every old behavior remains current.
