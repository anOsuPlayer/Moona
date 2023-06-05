#include "cppimpl.hpp"
#include "javastring.hpp"
#include "../base/moonaclass.hpp"

namespace moona {

    JavaImpl jobjectArray Java_test_Test_arr(StaticArgs, jclass clazz) {
        return JavaClass(clazz).getConstructor(ConstructorSignature::DEFAULT).newArray(3);
    }
    
    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(StaticArgs) {
        return __cplusplus;
    }
}