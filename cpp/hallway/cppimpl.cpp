#include "cppimpl.hpp"
#include "javastring.hpp"
#include "../base/moonaclass.hpp"

namespace moona {

    JavaImpl void Java_test_Test_arr(StaticArgs, jobjectArray arr) {
        JavaObjectArray a = arr;
        std::cout << a.toString();
    }
    
    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(StaticArgs) {
        return __cplusplus;
    }
}