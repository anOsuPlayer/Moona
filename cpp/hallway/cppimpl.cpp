#include "cppimpl.hpp"

namespace moona {

    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(DefaultArgs) {
        return __cplusplus;
    }
}