#include <jni.h>

#ifndef _Included_moonaFramework_util_collection_Array
#define _Included_moonaFramework_util_collection_Array
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_moonaFramework_util_collection_Array_generate
  (JNIEnv*, jobject, jint);

JNIEXPORT void JNICALL Java_moonaFramework_util_collection_Array_set
  (JNIEnv*, jobject, jobject, jint, jint);

JNIEXPORT jobject JNICALL Java_moonaFramework_util_collection_Array_get
  (JNIEnv*, jobject, jint, jint);

JNIEXPORT void JNICALL Java_moonaFramework_util_collection_Array_clearAll
  (JNIEnv*, jobject);

#ifdef __cplusplus
}
#endif
#endif
