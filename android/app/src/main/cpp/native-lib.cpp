//
// Created by imac on 2019-10-11.
//

#include <chrono>
#include <thread>
#include <jni.h>
#include <iostream>

int delay = 0, cppValue = 0;

extern "C"
JNIEXPORT void JNICALL Java_com_reacttestnative_state_ClickStateModule_cInit(JNIEnv * env, jobject obj) {
//    std::this_thread::sleep_for(std::chrono::milliseconds(100));
    jclass clickState = env->GetObjectClass(obj);
//    env->CallVoidMethod(obj, env->GetMethodID(clickState, "incrementClick", "()V"));
    jint val = env->CallIntMethod(obj, env->GetMethodID(clickState, "getDelay", "()I"));
    delay = (int)val;
    val = env->CallIntMethod(obj, env->GetMethodID(clickState, "getCppValue", "()I"));
    cppValue = (int)val;
}

extern "C"
JNIEXPORT void JNICALL Java_com_reacttestnative_state_ClickStateModule_cDelayChanged(JNIEnv * env, jobject obj) {
    jclass clickState = env->GetObjectClass(obj);
    jint val = env->CallIntMethod(obj, env->GetMethodID(clickState, "getDelay", "()I"));
    delay = (int)val;
}

extern "C"
JNIEXPORT void JNICALL Java_com_reacttestnative_state_ClickStateModule_cCppValueChanged(JNIEnv * env, jobject obj) {
    jclass clickState = env->GetObjectClass(obj);
    jint val = env->CallIntMethod(obj, env->GetMethodID(clickState, "getCppValue", "()I"));
    cppValue = (int)val;
}

extern "C"
JNIEXPORT void JNICALL Java_com_reacttestnative_state_ClickStateModule_cStartChangingCppValue(JNIEnv * env, jobject obj) {
    timer_start(100, updateCppValue, env, obj);
}

void timer_start(unsigned int interval, std::function<void(JNIEnv*, jobject)> func, JNIEnv *env, jobject obj)
{
    std::thread([func, interval]() {
        while (true)
        {
            func(env, obj);
            std::this_thread::sleep_for(std::chrono::milliseconds(interval));
        }
    }).detach();
}


void updateCppValue(JNIEnv * env, jobject obj) {
    jclass clickState = env->GetObjectClass(obj);
    env->CallVoidMethod(obj, env->GetMethodID(clickState, "getCppValue", "(I)V"), (jint)(cppValue+1));
}