#include "cppimpl.hpp"
#include "javastring.hpp"
#include "twinobject.hpp"

namespace moona {
    
    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(StaticArgs) {
        return __cplusplus;
    }

    JavaImpl void Java_test_Test_generate(StaticArgs, jobject obj) {
        TwinObject<double, double> to(obj);
        std::cout << to.atSafeDistance<double>(0) << " " << to.atSafeDistance<double>(8);
    }
}