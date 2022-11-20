#include <jni.h>
#include <iostream>
#include <algorithm>

#ifndef _Included_moonaFramework_util_collection_Array
#define _Included_moonaFramework_util_collection_Array
#ifdef __cplusplus
extern "C" {
#endif

using std::fill, std::malloc, std::calloc, std::realloc, std::free,
  std::cout, std::endl;

jobject** arrays = (jobject**) malloc(0);
int totalArrays = 0;

JNIEXPORT void JNICALL Java_moonaFramework_util_collection_Array_generate (JNIEnv* env, jobject thisObj, jint length) {
  arrays = (jobject**) realloc(arrays, ++totalArrays * sizeof(jobject*));
  arrays[totalArrays - 1] = (jobject*) malloc(length * sizeof(jobject));
}

JNIEXPORT void JNICALL Java_moonaFramework_util_collection_Array_set (JNIEnv* env, jobject thisObj, jobject newObj, jint at, jint arr) {
  env->DeleteGlobalRef(arrays[arr][at]);
  arrays[arr][at] = env->NewGlobalRef(newObj); 
}

JNIEXPORT jobject JNICALL Java_moonaFramework_util_collection_Array_get (JNIEnv* env, jobject thisObj, jint at, jint arr) {
  return arrays[arr][at];
}

JNIEXPORT void JNICALL Java_moonaFramework_util_collection_Array_erase (JNIEnv* env, jobject thisObj, jint arr) {
  memmove(arrays[arr], arrays[arr+1], totalArrays-arr);
  arrays = (jobject**) realloc(arrays, --totalArrays * sizeof(jobject*));
}

JNIEXPORT void JNICALL Java_moonaFramework_util_collection_Array_clearAll (JNIEnv* env, jobject thisObj) {
  free(arrays);
}

#ifdef __cplusplus
}
#endif
#endif
