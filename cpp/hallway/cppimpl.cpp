#include "cppimpl.hpp"
#include "javastring.hpp"
#include "twinobject.hpp"

namespace moona {
    
    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(StaticArgs) {
        return __cplusplus;
    }

    JavaImpl jobject Java_test_Test_generate(StaticArgs, jobject obj) {
        TwinObject<double, double, int> to(obj);
        std::cout << to.atIndex<double>(0) << " " << to.atIndex<double>(1) << "\n";
        return to;
    }
}