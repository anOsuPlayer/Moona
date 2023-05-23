#include "cppimpl.hpp"
#include "javastring.hpp"
#include "../base/moonaclass.hpp"

namespace moona {

    JavaImpl jobject Java_test_Test_edit(DefaultArgs, jobject o) {
        JavaMethod m = JavaClass("moonaframework.util.condition.Condition").getMethod("reverse", MethodSignature::VOID_METHOD);
        m.callOn(o);

        return o;
    }

    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(DefaultArgs) {
        return __cplusplus;
    }
}