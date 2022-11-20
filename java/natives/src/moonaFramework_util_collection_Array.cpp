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
  arrays[totalArrays - 1] = (jobject*) calloc(length, sizeof(jobject));
  fill(arrays[totalArrays-1], arrays[totalArrays-1]+length, nullptr);
}

JNIEXPORT void JNICALL Java_moonaFramework_util_collection_Array_erase (JNIEnv* env, jobject thisObj, jint arr) {
  free(arrays[arr]);
  memmove(arrays[arr], arrays[arr+1], totalArrays-arr);
}

#ifdef __cplusplus
}
#endif
#endif
