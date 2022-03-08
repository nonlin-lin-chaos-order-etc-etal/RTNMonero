# Playground run
- Nightly build: 20220307-2008

## Setup:
* Mac M1 Pro, 32 GB RAM
* Monterey 12.2.1
* rbenv with ruby 2.7.0 (If you have trouble installing it, try with `RUBY_CFLAGS=“-w” rbenv install 2.7.0`)

## Steps (From most recent to least recent command)

### [Access the Native Module in JS]()
Commands:
1. Open `App.js` file
1. Delete the file content
1. Paste the following snippet:
    ```js
    import React from 'react';
    import type {Node} from 'react';
    import {
    SafeAreaView,
    StatusBar,
    Button,
    useColorScheme,
    NativeModules,
    } from "react-native";

    const { CalendarModule } = NativeModules;

    const App = () => {
    const isDarkMode = useColorScheme() === 'dark';

    const backgroundStyle = {
        backgroundColor: isDarkMode ? 'black' : 'white',
    };

    const onPress = () => {
        CalendarModule.createCalendarEvent('testName', 'testLocation');
    };

    return (
        <SafeAreaView style={backgroundStyle}>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
            <Button
                title="Click to invoke your native module!"
                color="#841584"
                onPress={onPress}/>
            </SafeAreaView>
        );
    };

    export default App;
    ```
1. Run `cd .. && npx react-native run-ios`
1. Observe the app launching
1. Select the Simulator and press `cmd+D`
1. Select `Debug on Chrome`
1. Press `cmd+opt+I` to open the developer tools
1. Select the simulator and refresh the App (`cmd+R` twice. The first time it will start recording the Simulator. Stop the recording and delete it).
1. Tap on the button and observe the `Pretending to create an event testName at testLocation` message in the Chrome console.

### [Export native method to JS (Native Side)]()
Commands:
1. Open `RCTCalendarModule.m`
1. Add the import:
    ```obj-c
    #import <React/RCTLog.h>
    ```
1. Add the code:
    ```obj-c
    RCT_EXPORT_METHOD(createCalendarEvent:(NSString *)name location:(NSString *)location)
    {
        RCTLogInfo(@"Pretending to create an event %@ at %@", name, location);
    }
    ```
1. `cmd+B` to build it
1. `cmd+R` to run it



### [create RCTCalendar Native Module (Native side)]()
Commands:
1. `cd ios && open AwesomeApp.xcworkspace`
1. Select the `AwesomeApp` folder
1. `cmd+N` to create a new file
1. Choose `New Header File`
1. Call it `RCTCalendarModule`
1. Paste the code:
   ```obj-c
   #import <React/RCTBridgeModule.h>
   @interface RCTCalendarModule : NSObject <RCTBridgeModule>
   @end
   ```
1. `cmd+N` to create a new file
1. Choose `New Objective-C File`
1. Call it `RCTCalendarModule`
1. Set membership to both `AwesomeApp` and `AwesomeAppTests`
1. Paste the code
    ```obj-c
    #import "RCTCalendarModule.h"

    @implementation RCTCalendarModule

    // To export a module named RCTCalendarModule
    RCT_EXPORT_MODULE();

    @end
    ```
1. `cmd+B` to build it
1. `cmd+R` to run it

**Note:**

We can create a Template `react-native-objc-migration` with this project to create a pipeline to test the migration in CI.

### [react-native init](4c38d7e)
Commands:
1. `npx react-native init AwesomeApp --version 0.0.0-20220307-2008-ae3d4f700` When asked to install cocoapods, choose `1 (yes with gem)`.
1. `cd AwesomeApp`
1. `npx react-native start`
1. `npx react-native run-ios`