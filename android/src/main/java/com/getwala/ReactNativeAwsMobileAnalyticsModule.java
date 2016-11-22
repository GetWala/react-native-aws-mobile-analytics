
package com.getwala;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.InitializationException;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.MobileAnalyticsManager;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.monetization.CustomMonetizationEventBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.mobileanalytics.model.Event;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;

public class ReactNativeAwsMobileAnalyticsModule extends ReactContextBaseJavaModule {

    private static MobileAnalyticsManager analytics;
    private final ReactApplicationContext reactContext;

    public ReactNativeAwsMobileAnalyticsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ReactNativeAwsMobileAnalytics";
    }

    @ReactMethod
    public void initializeMobileAnalytics(String appId, String identityPoolId, String region, Promise promise) {
        try {
            CognitoCachingCredentialsProvider cognitoProvider = new CognitoCachingCredentialsProvider(reactContext.getApplicationContext(), identityPoolId, Regions.fromName(region));
            analytics = MobileAnalyticsManager.getOrCreateInstance(reactContext.getApplicationContext(), appId, Regions.fromName(region), cognitoProvider);
            promise.resolve(true);
        } catch (InitializationException ie) {
            promise.reject(ie);
        }
    }

    @ReactMethod
    public void pauseSession(Promise promise) {
        if (analytics != null) {
            analytics.getSessionClient().pauseSession();
            analytics.getEventClient().submitEvents();
            promise.resolve(true);
        } else {
            promise.reject(new Exception("ReactNativeAwsMobileAnalyticsModule should be initialized first"));
        }
    }

    @ReactMethod
    public void resumeSession(Promise promise) {
        if (analytics != null) {
            analytics.getSessionClient().resumeSession();
            promise.resolve(true);
        } else {
            promise.reject(new Exception("ReactNativeAwsMobileAnalyticsModule should be initialized first"));
        }
    }

    @ReactMethod
    public void submitEvents(Promise promise){
        if(analytics != null){
            analytics.getEventClient().submitEvents();
            promise.resolve(true);
        } else {
            promise.reject(new Exception("ReactNativeAwsMobileAnalyticsModule should be initialized first"));
        }
    }

    @ReactMethod
    public void recordMonetizationEvent(ReadableMap monetizationEvent, Promise promise) {
        if(analytics != null){
            EventClient eventClient = analytics.getEventClient();
            CustomMonetizationEventBuilder builder = CustomMonetizationEventBuilder.create(eventClient);
            AnalyticsEvent event = builder.withStore(monetizationEvent.getString("store"))
                    .withCurrency(monetizationEvent.getString("currency"))
                    .withFormattedItemPrice(monetizationEvent.getString("formattedPrice"))
                    .withItemPrice(monetizationEvent.getDouble("itemPrice"))
                    .withProductId(monetizationEvent.getString("productId"))
                    .withQuantity(monetizationEvent.getDouble("quantity"))
                    .withTransactionId(monetizationEvent.getString("transactionId")).build();
            eventClient.recordEvent(event);
            promise.resolve(true);
        }else{
            promise.reject(new Exception("ReactNativeAwsMobileAnalyticsModule should be initialized first"));
        }
    }

    @ReactMethod
    public void recordCustomEvent(String eventType, ReadableMap attributes, ReadableMap metrics, Promise promise) {
        if(analytics != null){
            EventClient eventClient = analytics.getEventClient();
            AnalyticsEvent customEvent = eventClient.createEvent(eventType);
            ReadableMapKeySetIterator attributeIterator = attributes.keySetIterator();
            while(attributeIterator.hasNextKey()){
                String key = attributeIterator.nextKey();
                customEvent = customEvent.withAttribute(key, attributes.getString(key));
            }
            ReadableMapKeySetIterator metricIterator = metrics.keySetIterator();
            while(metricIterator.hasNextKey()){
                String key = metricIterator.nextKey();
                customEvent = customEvent.withMetric(key, metrics.getDouble(key));
            }
            eventClient.recordEvent(customEvent);
            promise.resolve(true);
        }else{
            promise.reject(new Exception("ReactNativeAwsMobileAnalyticsModule should be initialized first"));
        }
    }
}