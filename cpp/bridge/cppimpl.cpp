#include "cppimpl.hpp"
#include <stdlib.h>

namespace moona {

    JavaImpl jlong Java_moonaframework_bridge_Cplusplus_version(JNIEnv* env, jclass clazz) {
        return __cplusplus;
    }
}