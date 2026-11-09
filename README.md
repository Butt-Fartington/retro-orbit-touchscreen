# Retro Orbit Touchscreen

Current version: **1.2.26-touch** (code 53), minimum API 26, target API 35. Tested on Samsung SM-S916U running Android 16.

A millennium-inspired touchscreen launcher: chrome/glass app icons, orbit wheel, Rajdhani typography, weather, app-grouped notification previews and pages, continuous scrolling, and momentum. Tap to open, hold for options. Swipe notification groups right to dismiss clearable alerts; left for options. Home has Apps/Options; content screens omit the Open button. A Recently used row retains the last three successful launches through Retro Orbit (no usage-access permission). The app drawer includes an A–Z drag rail with a letter indicator. A floating search orb below the wheel opens the Google app Home page (with search/browser fallbacks). Full controls are in Options → Help.

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

Cellular status follows the default data SIM and Android signal/display callbacks. Options → Cellular status requests optional READ_PHONE_STATE permission. 5G/5G+ labels use carrier display overrides; they describe Android’s network branding rather than measured throughput. No call-log or phone-number permission is requested.

## App search and communication icons (1.2.23)

The Applications screen has a native search field that filters app names, ignoring case. Clear it to restore Recently used and the full alphabetical list. Result taps and long-press options refer to the original installed app. No-result state exposes no unrelated app actions. Distinct metallic symbols and colors identify Gmail, Outlook, Samsung Email, WhatsApp, Telegram, Signal, Discord, Messenger, Google Messages and Samsung Messages. Explicit per-app icon overrides still take priority.

Validation: signed build passed; Android 16 emulator search filtering, result launch into Clock, and empty results were checked. Physical Samsung validation of this revision is pending.

In 1.2.24, app search sits below the Applications header and above Recently used. Tapping the weather panel opens a Google seven-day forecast search for the configured city; holding it or choosing Weather in Options opens configuration. Forecast may open in a browser depending on installed Google app handlers. Layout and forecast link were checked on the physical Samsung.

## Glass oval controls (1.2.25)

Footer controls and page headings now share thin chrome-outline elliptical glass styling, with Michroma lettering. Native footer controls retain 56dp touch height despite slimmer visible artwork. Michroma is included under its SIL Open Font License at assets/fonts/MICHROMA-OFL.txt; source https://github.com/google/fonts/tree/main/ofl/michroma . Physical Samsung home, app and settings layouts were inspected after installation.

Cobalt Glass Orbit is a bundled generated wallpaper, selectable under Options → Wallpaper. It is the default on new installs and is selected once when this wallpaper update first runs; later wallpaper choices are preserved. The home background overlay is lighter for this wallpaper to retain its glass and horizon details. Other wallpapers remain available.
