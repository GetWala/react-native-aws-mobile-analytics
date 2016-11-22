
# -react-native-aws-mobile-analytics

## Getting started

`$ npm install -react-native-aws-mobile-analytics --save`

### Mostly automatic installation

`$ react-native link -react-native-aws-mobile-analytics`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `-react-native-aws-mobile-analytics` and add `ReactNativeAwsMobileAnalytics.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libReactNativeAwsMobileAnalytics.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.ReactNativeAwsMobileAnalyticsPackage;` to the imports at the top of the file
  - Add `new ReactNativeAwsMobileAnalyticsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':-react-native-aws-mobile-analytics'
  	project(':-react-native-aws-mobile-analytics').projectDir = new File(rootProject.projectDir, 	'../node_modules/-react-native-aws-mobile-analytics/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':-react-native-aws-mobile-analytics')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `ReactNativeAwsMobileAnalytics.sln` in `node_modules/-react-native-aws-mobile-analytics/windows/ReactNativeAwsMobileAnalytics.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Cl.Json.ReactNativeAwsMobileAnalytics;` to the usings at the top of the file
  - Add `new ReactNativeAwsMobileAnalyticsPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import ReactNativeAwsMobileAnalytics from '-react-native-aws-mobile-analytics';

// TODO: What do with the module?
ReactNativeAwsMobileAnalytics;
```
  