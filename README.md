# ShortcutDemo
If your app targets Android 7.1 (API level 25) or higher, you can define shortcuts to specific actions in your app. These shortcuts can be displayed in a supported launcher. Shortcuts let your users quickly start common or recommended tasks within your app. 

You can publish two different types of shortcuts for your app: 
1) Static shortcuts are defined in a resource file that is packaged into an APK. Therefore, you must wait until you update your entire app to change the details of these static shortcuts.

2) Dynamic shortcuts are published at runtime using the [ShortcutManager](https://developer.android.com/reference/android/content/pm/ShortcutManager.html) API. During runtime, your app can publish, update, and remove its dynamic shortcuts.

## Using Static Shortcuts

1) Add a **<meta-data>** element to this activity that references the resource file where the app's shortcuts are defined: 

```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="com.example.myapplication">
  <application ... >
    <activity android:name="Main">
      <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
      </intent-filter>
      **<meta-data android:name="android.app.shortcuts"
                 android:resource="@xml/shortcuts" />**
    </activity>
  </application>
</manifest>
```

2) Create a new resource file: **res/xml/shortcuts.xml.**
   In this new resource file, add a **<shortcuts>** root element, which contains a list of **<shortcut>** elements.
   Each **<shortcut>** element, in turn, contains information about a static shortcut, including its icon, its description labels, and the intents that it launches within the app: 
   
```
   <shortcuts xmlns:android="http://schemas.android.com/apk/res/android">
      <shortcut
        android:shortcutId="compose"
        android:enabled="true"
        android:icon="@drawable/compose_icon"
        android:shortcutShortLabel="@string/compose_shortcut_short_label1"
        android:shortcutLongLabel="@string/compose_shortcut_long_label1"
        android:shortcutDisabledMessage="@string/compose_disabled_message1">
        <intent
          android:action="android.intent.action.VIEW"
          android:targetPackage="com.example.myapplication"
          android:targetClass="com.example.myapplication.ComposeActivity" />
        <!-- If your shortcut is associated with multiple intents, include them
             here. The last intent in the list determines what the user sees when
             they launch this shortcut. -->
        <categories android:name="android.shortcut.conversation" />
      </shortcut>
      <!-- Specify more shortcuts here. -->
   </shortcuts>
```        
  

## Using Dynamic Shortcuts

The [ShortcutManager](https://developer.android.com/reference/android/content/pm/ShortcutManager.html) API allows you to complete the following operations on dynamic shortcuts:
**Publish** : Use [ setDynamicShortcuts() ](https://developer.android.com/reference/android/content/pm/ShortcutManager.html#setDynamicShortcuts(java.util.List%3Candroid.content.pm.ShortcutInfo%3E)) to redefine the entire list of dynamic shortcuts, or use addDynamicShortcuts() to augment an existing list of dynamic shortcuts.
**Update** : Use the [ updateShortcuts() ](https://developer.android.com/reference/android/content/pm/ShortcutManager.html#updateShortcuts(java.util.List%3Candroid.content.pm.ShortcutInfo%3E)) method.
**Remove** : Remove a set of dynamic shortcuts using [ removeDynamicShortcuts() ](https://developer.android.com/reference/android/content/pm/ShortcutManager.html#removeDynamicShortcuts(java.util.List%3Cjava.lang.String%3E)), or remove all dynamic shortcuts using [ removeAllDynamicShortcuts() ](https://developer.android.com/reference/android/content/pm/ShortcutManager.html#removeAllDynamicShortcuts()).

An example of creating a dynamic shortcut and associating it with your app appears in the following code snippet: 

```
ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "id1")
    .setShortLabel("Web site")
    .setLongLabel("Open the web site")
    .setIcon(Icon.createWithResource(context, R.drawable.icon_website))
    .setIntent(new Intent(Intent.ACTION_VIEW,
                   Uri.parse("https://www.mysite.example.com/")))
    .build();

shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut));

```
