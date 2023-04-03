#pragma once

#define JavaImpl extern "C" JNIEXPORT JNICALL

#define DefaultArgs JNIEnv* env, jobject thisObj
#define StaticArgs JNIEnv* env, jclass clazz