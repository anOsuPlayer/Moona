#include <jni.h>
#include <iostream>
#include <chrono>
#include <ctime>

#ifndef _Included_moonaFramework_util_Benchmark
#define _Included_moonaFramework_util_Benchmark
#ifdef __cplusplus
extern "C" {
#endif

using std::chrono::duration, std::chrono::duration_cast, std::chrono::nanoseconds,
  std::chrono::high_resolution_clock;

jlong getTime(JNIEnv* env, jobject code) {
  // jmethodID id = env->GetMethodID(env->GetObjectClass(code), "code", nullptr);
  auto init = high_resolution_clock::now().time_since_epoch();
  // env->CallVoidMethod(code, id);
  auto end = high_resolution_clock::now().time_since_epoch();
  long duration = duration_cast<nanoseconds>(end - init).count();
  return duration;
}

JNIEXPORT jlong JNICALL Java_moonaFramework_util_Benchmark_time (JNIEnv* env, jclass clazz, jobject code) {
  return getTime(env, code);
}

JNIEXPORT jdouble JNICALL Java_moonaFramework_util_Benchmark_stress (JNIEnv* env, jclass clazz, jobject code, jint iterations) {
  double total = 0;  
  for (int i = 0; i < iterations; i++) {
    total += getTime(env, code);
  }
  return (total / iterations);
}

#ifdef __cplusplus
}
#endif
#endif
