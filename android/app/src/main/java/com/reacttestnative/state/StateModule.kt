package com.reacttestnative.state

import com.facebook.react.bridge.*

import kotlin.collections.HashMap

class StateModule(context: ReactApplicationContext) : ReactContextBaseJavaModule(context) {
    private val constants : HashMap<String, Any> = hashMapOf("clicks" to 0)

    companion object {
        lateinit var reactContext: ReactApplicationContext
    }

    init {
        reactContext = context
    }

    override fun getName(): String {
        return "StateExample"
    }

    override fun getConstants(): HashMap<String, Any> {
        return constants
    }

    @ReactMethod
    fun whenClicked(callback: Callback) {
        val curValAny = constants["clicks"]
        val curVal = if (curValAny is Int) curValAny else 0
        constants["clicks"] = curVal + 1
        callback.invoke(curVal+1)
    }
}