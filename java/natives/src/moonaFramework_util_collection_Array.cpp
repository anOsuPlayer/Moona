#include <jni.h>
#include <iostream>

#ifndef _Included_moonaFramework_util_collection_Array
#define _Included_moonaFramework_util_collection_Array
#ifdef __cplusplus
extern "C" {
#endif

jobject** arrays = (jobject**) malloc(0);
int totalArrays = 0;

JNIEXPORT void JNICALL Java_moonaFramework_util_collection_Array_generate (JNIEnv* env, jobject newObj, jint length) {
  arrays = (jobject**) realloc(arrays, ++totalArrays * sizeof(jobject*));
  arrays[totalArrays - 1] = (jobject*) calloc(length, sizeof(jobject));
}

#ifdef __cplusplus
}
#endif
#endif
