package rtnmonero.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import rtnmonero.NativeMoneroSpec;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public class NativeMonero extends NativeMoneroSpec {

    public static final String NAME = "NativeMonero";

    public NativeMonero(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public void answerTheUltimateQuestion(String input, Promise promise) {
        promise.resolve(42.0);
    }

    @NonNull
    @Override
    public String getName() {
        return NAME;
    }
}
