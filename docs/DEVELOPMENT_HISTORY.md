# Retro Orbit Touch 1.2 — Chrome Blue

A touchscreen adaptation of the Kyocera launcher with chrome frames, orbital graphics, pixel typography and mechanical circular icons. Minimum Android 8/API 26; target API 35. This release was installed and checked on a Samsung SM-S916U running Android 16. The Kyocera project is unchanged; this separate touchscreen branch does not include its bundled Orbit T9 keyboard.

## New in 1.2

Home now has Apps and Options soft keys. Open apps is removed; use Android’s own Recents navigation. Live status icons replace the launcher title, and recent notification previews fill the freed space. Screen directions have moved to Options → Help. Pixel typography extends through menus and dialogs, with cyan pressed states. The launch effect retains its final frame during app handoff.

## Install and set as Home

Install Retro-Orbit-Touch-1.2.apk on the intended Android device. Open Retro Orbit, then Settings → Default home app and select Retro Orbit. No accessibility permission is needed for the wheel, app drawer, settings, gestures or ordinary app launching. Android's normal Back/Home/Recents navigation remains visible/available on touch devices. Switch back to another home app in Android settings whenever needed.

## Touch controls

- Tap any wheel icon to launch it, including the orb launch effect if enabled.
- Drag vertically on the wheel to rotate it; swipe left from home for Apps and right for Notifications.
- Hold a wheel or app-grid icon for open/favorite/icon/system app-info options.
- Tap the chrome orb to choose/restore its photo or preview/configure the launch effect.
- Tap the clock/date for device time settings, weather for city/units, notifications for the full list.
- Swipe the app grid to change pages, or tap the left/right halves of its page indicator.
- Swipe settings, favorites, order and notification lists to change pages. Tap a row to activate it.
- Tap a reorder entry for Move up / Move down / Remove controls.
- Hold an alert for Open / Dismiss options. Ongoing system alerts cannot be dismissed.
- Native bottom controls expose Apps/Options on home and Open/Options/Back in content screens.

## Device adaptation

Canvas geometry uses uniform scaling with a bounded, centered panel on wide screens. No non-uniform stretching of circles. Screen insets and native navigation remain available. Tablet/wide layouts intentionally preserve a portrait dashboard in the center instead of stretching across the entire width. Very short screens show three wheel icons and fewer list rows. Native toolbar buttons are 56dp tall and list paging keeps useful tap sizes. Keypad mode remains for devices reporting no touchscreen, with existing physical-key controls, but this modern-target branch has not been physically regression-tested on KYF42; keep its finalized 1.0 build for daily use.

Kyocera cursor controls are offered only for Kyocera keypad mode. Touch devices don't need them. The optional accessibility shortcut for Open apps can invoke native Android Recents; otherwise use the phone's own Recents button/gesture. It is never presented as a list of guaranteed-running processes.

Virtual accessibility nodes expose wheel/app/list items and activation/long-press/paging actions. Native toolbar and dialogs use normal Android accessibility. UI hierarchy inspection verified that nodes are discoverable; a full TalkBack/Switch Access usability audit has not been performed. Current canvas text does not fully track Android's largest font-size settings.

## Weather, alerts and pictures

Weather requires a configured city and internet, with cached readings and offline states. Open-Meteo/GeoNames attribution is retained. No GPS permission requested. Notification access is optional and stays on device. Custom orb images use the system document picker, bounded decoding, EXIF rotation handling and private local storage. No broad media permission or image upload.

## Build and signing

Requires JDK, Android SDK platform 35, build-tools 36.0.0 and zip. No downloaded app libraries. Set ANDROID_HOME if needed. Provide the original signing/retro-orbit.keystore (or ORBIT_KEYSTORE pointing to it) and run ./build.sh; output build/retro-orbit.apk. The separate private signing backup from 1.0 remains valid. Source archive excludes signing keys and build outputs. Debugging is disabled in the APK; the retained personal signing certificate is named Android Debug to maintain update compatibility.

## Validation

- Build and APK signature verification passed.
- Installed and launched on API 36.1 emulator, target API 35.
- Inspected 1080×2400/420dpi, 720×1280/320dpi, 1280×720/320dpi, 1200×1920/160dpi and 1920×1200/160dpi layouts.
- Long-press app menu displayed; favorite add persisted through process restart.
- Swipe paging exposed the next app-grid page.
- Touch tapping a wheel icon successfully ran the launch sequence and opened Clock.
- UI hierarchy exposed virtual app, wheel and settings nodes plus native toolbar controls.
- Automated smoke check passed for toolbar target size, settings navigation, unsupported-control filtering and list swipe paging.
- 200,100 carousel arithmetic checks passed.
- No AndroidRuntime errors observed during smoke tests.

Not verified: other physical touchscreen/OEM behavior, Android 8–15 runtime matrix, store distribution, full accessibility certification, performance/battery measurements or post-reboot default-home behavior on other manufacturers. The 1.2 screenshots were captured on the physical Samsung. Its existing default Home app was left unchanged.

The included visual assets derive from the user's Chrome Blue concept pack and generated orb artwork. The supplied pack described personal user use; public/commercial redistribution licensing has not been established.

Pixelify Sans font: https://github.com/google/fonts/tree/main/ofl/pixelifysans . Distributed under the included SIL Open Font License at assets/fonts/OFL.txt.

## Typography update 1.2.2

Rajdhani SemiBold replaces Pixelify Sans with a cleaner early-2000s technical style. Standard and contextual ligatures are disabled, and light positive tracking keeps letters distinct. Rajdhani is distributed under the included SIL Open Font License. Source: https://github.com/google/fonts/tree/main/ofl/rajdhani .
