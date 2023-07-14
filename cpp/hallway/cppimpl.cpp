#include "cppimpl.hpp"
#include "javastring.hpp"
#include "twinobject.hpp"

namespace moona {
    
    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(StaticArgs) {
        return __cplusplus;
    }

    JavaImpl jobject Java_test_Test_generate(StaticArgs, jobject obj) {
        TwinObject<double, double> to(obj);
        std::cout << to.atDistance<double>(0) << "\n";
        // jfieldID id = env->GetFieldID(env->GetObjectClass(obj), "x", "D");
        // std::cout << env->GetDoubleField(obj, id);
        return nullptr;
    }
}