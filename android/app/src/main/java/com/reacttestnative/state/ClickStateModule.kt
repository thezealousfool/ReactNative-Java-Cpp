package com.reacttestnative.state

import com.facebook.react.bridge.*

import kotlin.collections.HashMap
import com.facebook.react.modules.core.DeviceEventManagerModule
import java.util.*

class ClickStateModule(context: ReactApplicationContext) : ReactContextBaseJavaModule(context) {
    private val constants = hashMapOf<String, Any>("clicks" to 0)
    private var startT = Date().time

    companion object {
        lateinit var reactContext: ReactApplicationContext
    }

    init {
        System.loadLibrary("native-lib")
        reactContext = context
    }

    override fun getName(): String {
        return "ClickStateExample"
    }

    override fun getConstants(): HashMap<String, Any> {
        return constants
    }

    private external fun doSomething()

    @ReactMethod
    fun whenClicked() {
        startT = Date().time
        doSomething()
    }

    fun incrementClick() {
        val curValAny = constants["clicks"]
        val curVal = if (curValAny is Int) curValAny else 0
        constants["clicks"] = curVal + 1
        val dur = Date().time - startT
        val params : WritableMap = Arguments.createMap()
        params.putInt("value", curVal+1)
        params.putInt("javadur", dur.toInt())
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                .emit("ValueChange", params)
    }
}