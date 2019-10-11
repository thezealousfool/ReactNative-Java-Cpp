//
// Created by imac on 2019-10-11.
//

#include <chrono>
#include <thread>
#include <jni.h>
#include <iostream>

int delay = 0, cppValue = 0;

extern "C"
JNIEXPORT void JNICALL Java_com_reacttestnative_state_ClickStateModule_init(JNIEnv * env, jobject obj) {
//    std::this_thread::sleep_for(std::chrono::milliseconds(100));
    jclass clickState = env->GetObjectClass(obj);
//    env->CallVoidMethod(obj, env->GetMethodID(clickState, "incrementClick", "()V"));
    jint val = env->CallIntMethod(obj, env->GetMethodID(clickState, "getDelay", "()I"));
    delay = (int)val;
    val = env->CallIntMethod(obj, env->GetMethodID(clickState, "getCppValue", "()I"));
    cppValue = (int)val;
}

extern "C"
JNIEXPORT void JNICALL Java_com_reacttestnative_state_ClickStateModule_cDelayChanged(JNIEnv * env, jobject obj, jint arg) {

}