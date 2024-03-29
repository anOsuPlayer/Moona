#pragma once

#include <jni.h>

#define JavaImpl extern "C" JNIEXPORT JNICALL

#define DefaultArgs JNIEnv* env, jobject thisObj
#define StaticArgs JNIEnv* env, jclass thisClazz

static inline const jboolean jtrue(1);
static inline const jboolean jfalse(0);