package com.reacttestnative

import android.app.Application
import android.content.Context
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.soloader.SoLoader
import java.lang.reflect.InvocationTargetException
import com.facebook.react.shell.MainReactPackage
import com.reacttestnative.state.DelayStatePackage


class MainApplication : Application(), ReactApplication {

  private val mReactNativeHost = object : ReactNativeHost(this) {
    override fun getUseDeveloperSupport(): Boolean {
      return BuildConfig.DEBUG
    }

    override fun getPackages(): List<ReactPackage> {
      return listOf(
              MainReactPackage(),
              DelayStatePackage()
      )
    }

    override fun getJSMainModuleName(): String {
      return "index"
    }
  }

  override fun getReactNativeHost(): ReactNativeHost {
    return mReactNativeHost
  }

  override fun onCreate() {
    super.onCreate()
    SoLoader.init(this, /* native exopackage */ false)
    initializeFlipper(this) // Remove this line if you don't want Flipper enabled
  }

  private fun initializeFlipper(context: Context) {
    if (BuildConfig.DEBUG) {
      try {
        val aClass = Class.forName("com.facebook.flipper.ReactNativeFlipper")
        aClass.getMethod("initializeFlipper", Context::class.java).invoke(null, context)
      } catch (e: ClassNotFoundException) {
        e.printStackTrace()
      } catch (e: NoSuchMethodException) {
        e.printStackTrace()
      } catch (e: IllegalAccessException) {
        e.printStackTrace()
      } catch (e: InvocationTargetException) {
        e.printStackTrace()
      }

    }
  }
}
