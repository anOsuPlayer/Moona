#include "cppimpl.hpp"
#include "javastring.hpp"
#include "../base/moonaclass.hpp"

namespace moona {

    JavaImpl jboolean Java_test_Test_eq(StaticArgs, jobjectArray arr, jobjectArray arr2) {
        JavaObjectArray a1 = arr, a2 = arr2;
        return a1.equals(a2);
    }
    
    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(StaticArgs) {
        return __cplusplus;
    }
}