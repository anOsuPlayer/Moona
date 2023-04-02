#pragma once

#ifndef MOONA_JAVA_NOTATION
    #define MOONA_JAVA_NOTATION

    #define JavaImpl extern "C" JNIEXPORT JNICALL

    #define DefaultArgs JNIEnv* env, jobject thisObj
    #define StaticArgs JNIEnv* env, jclass clazz

#endif