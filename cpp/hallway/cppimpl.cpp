#include "cppimpl.hpp"
#include <stdlib.h>
#include <iostream>

namespace moona {

    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(JNIEnv* env, jclass clazz) {
        return __cplusplus;
    }
}