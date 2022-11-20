#include <jni.h>

#ifndef _Included_moonaFramework_util_Benchmark
#define _Included_moonaFramework_util_Benchmark
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jlong JNICALL Java_moonaFramework_util_Benchmark_time
  (JNIEnv*, jclass, jobject);

JNIEXPORT jdouble JNICALL Java_moonaFramework_util_Benchmark_stress
  (JNIEnv*, jclass, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif
