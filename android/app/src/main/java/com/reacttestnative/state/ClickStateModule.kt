package com.reacttestnative.state

import com.facebook.react.bridge.*
import java.util.concurrent.Callable

import kotlin.collections.HashMap
import kotlin.coroutines.CoroutineContext
import com.facebook.react.modules.core.DeviceEventManagerModule



class ClickStateModule(context: ReactApplicationContext) : ReactContextBaseJavaModule(context) {
    private val constants = hashMapOf<String, Any>("clicks" to 0)

    companion object {
        lateinit var reactContext: ReactApplicationContext
    }

    init {
        reactContext = context
    }

    override fun getName(): String {
        return "ClickStateExample"
    }

    override fun getConstants(): HashMap<String, Any> {
        return constants
    }

    @ReactMethod
    fun whenClicked() {
        val curValAny = constants["clicks"]
        val curVal = if (curValAny is Int) curValAny else 0
        constants["clicks"] = curVal + 1
        Thread.sleep(100)
        val params : WritableMap = Arguments.createMap()
        params.putInt("value", curVal+1)
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit("ValueChange", params)
    }
}