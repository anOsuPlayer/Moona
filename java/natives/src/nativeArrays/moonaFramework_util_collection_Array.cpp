#include <jni.h>
#include <iostream>

using std::malloc, std::realloc, std::cout, std::endl;

#ifndef _Included_moonaframework_util_collection_Array
#define _Included_moonaframework_util_collection_Array
#ifdef __cplusplus
extern "C" {
#endif

jobject** arrays = (jobject**) malloc(0);
int totalArrays = 0;

JNIEXPORT void JNICALL Java_moonaframework_util_collection_Array_generate (JNIEnv* env, jobject thisObj, jint newLength) {
  arrays = (jobject**) realloc(arrays, ++totalArrays * sizeof(jobject*));
  arrays[totalArrays - 1] = (jobject*) calloc(newLength, sizeof(jobject));
}

JNIEXPORT void JNICALL Java_moonaframework_util_collection_Array_set (JNIEnv* env, jobject thisObj, jobject newObj, jint at, jint arr, jint length) {
  if (at > length) {
    length = at;
    arrays[arr] = (jobject*) realloc(arrays[arr], length * sizeof(jobject));
  }
  if (arrays[arr][at] != nullptr) { env->DeleteGlobalRef(arrays[arr][at]); }
  arrays[arr][at] = env->NewGlobalRef(newObj);
}

JNIEXPORT jobject JNICALL Java_moonaframework_util_collection_Array_get (JNIEnv* env, jobject thisObj, jint at, jint arr, jint length) {
  if (at >= length) {
    return nullptr;
  }
  return arrays[arr][at];
}

JNIEXPORT void JNICALL Java_moonaframework_util_collection_Array_clearAll (JNIEnv* env, jobject thisObj) {
  free(arrays);
}

#ifdef __cplusplus
}
#endif
#endif
