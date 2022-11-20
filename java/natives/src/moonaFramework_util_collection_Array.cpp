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

JNIEXPORT jint JNICALL Java_moonaFramework_util_collection_Array_generate (JNIEnv* env, jobject thisObj, jint length) {
  arrays = (jobject**) realloc(arrays, ++totalArrays * sizeof(jobject*));
  arrays[totalArrays - 1] = (jobject*) calloc(length, sizeof(jobject));
  fill(arrays[totalArrays-1], arrays[totalArrays-1]+(length*sizeof(jobject)), nullptr);
  return totalArrays-1;
}

JNIEXPORT void JNICALL Java_moonaFramework_util_collection_Array_set (JNIEnv* env, jobject thisObj, jobject newObj, jint at, jint arr) {
  arrays[arr][at] = env->NewGlobalRef(newObj); 
}

JNIEXPORT jobject JNICALL Java_moonaFramework_util_collection_Array_get (JNIEnv* env, jobject thisObj, jint at, jint arr) {
  return arrays[arr][at];
}

JNIEXPORT void JNICALL Java_moonaFramework_util_collection_Array_clearAll (JNIEnv* env, jobject thisObj) {
  free(arrays);
}

#ifdef __cplusplus
}
#endif
#endif
