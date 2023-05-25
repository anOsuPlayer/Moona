#include "cppimpl.hpp"
#include "javastring.hpp"
#include "../base/moonaclass.hpp"

namespace moona {

    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(DefaultArgs) {
        return __cplusplus;
    }
}