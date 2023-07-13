#include "cppimpl.hpp"
#include "javastring.hpp"
#include "twinobject.hpp"

namespace moona {
    
    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(StaticArgs) {
        return __cplusplus;
    }

    JavaImpl jobject Java_test_Test_generate(StaticArgs, jobject obj) {
        TwinObject<double, double>* to = TwinObject<double, double>::of(obj);
        std::cout << *reinterpret_cast<double*>(reinterpret_cast<char*>(to)+sizeof(TwinObject<double, double>));
        return nullptr;
    }
}