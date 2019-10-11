package com.reacttestnative.state

import com.facebook.react.bridge.*

import kotlin.collections.HashMap
import com.facebook.react.modules.core.DeviceEventManagerModule
import java.util.*

class DelayStateModule(context: ReactApplicationContext) : ReactContextBaseJavaModule(context) {
    private val constants = hashMapOf<String, Any>("cppValue" to 0, "delay" to 100)
    private var startT = Date().time

    companion object {
        lateinit var reactContext: ReactApplicationContext
    }

    init {
        System.loadLibrary("native-lib")
        reactContext = context
        cInit()
    }

    override fun getName(): String {
        return "DelayStateExample"
    }

    override fun getConstants(): HashMap<String, Any> {
        return constants
    }

    fun getDelay() : Int {
        val curValAny = constants["delay"]
        return if (curValAny is Int) curValAny else 0
    }

    fun getCppValue() : Int {
        val curValAny = constants["cppValue"]
        return if (curValAny is Int) curValAny else 0
    }

    private external fun cDelayChanged()
    private external fun cCppValueChanged()
    private external fun cInit()
    private external fun cStartChangingCppValue()

    @ReactMethod
    fun whenClicked() {
        startT = Date().time
        cStartChangingCppValue()
    }

    @ReactMethod
    fun setDelay(delay: Int) {
        constants["delay"] = delay
        delayChanged()
    }

    fun delayChanged() {
        val delay = getDelay()
        val params : WritableMap = Arguments.createMap()
        params.putInt("value", delay)
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                .emit("DelayChanged", params)
        cDelayChanged()
    }

    fun cppValueChanged() {
        cCppValueChanged()
        val cppValue = getCppValue()
        val params : WritableMap = Arguments.createMap()
        params.putInt("value", cppValue)
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                .emit("CppValueChange", params)
    }

    fun setCppValue(value: Int) {
        constants["cppValue"] = value
        cppValueChanged()
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