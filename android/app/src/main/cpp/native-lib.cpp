//
// Created by imac on 2019-10-11.
//

#include <chrono>
#include <thread>
#include <jni.h>
#include <iostream>

extern "C"
JNIEXPORT void JNICALL Java_com_reacttestnative_state_ClickStateModule_doSomething(JNIEnv * env, jobject obj) {
    std::this_thread::sleep_for(std::chrono::milliseconds(100));
    jclass clickState = env->GetObjectClass(obj);
    env->CallVoidMethod(obj, env->GetMethodID(clickState, "incrementClick", "()V"));
//    if (clickState != nullptr) {
//        jmethodID mid = env->GetMethodID(clickState, "whenClicked", "()V");
//        if (mid != nullptr) {
//            env->CallVoidMethod(clickState, mid);
//        }
//        std::cout << "Yaaaay" << std::endl;
//    }
}
