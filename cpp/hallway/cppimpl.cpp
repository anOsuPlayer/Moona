#include "cppimpl.hpp"
#include "javastring.hpp"
#include "../base/moonaclass.hpp"

namespace moona {

    JavaImpl jstring Java_test_Test_edit(DefaultArgs, jstring str) {
        JavaString s = str;
        for (size_t i = 0; i < s.length(); i++) {
            s[i] = (s[i] >= 'a') ? (s[i] - 32) : (s[i] + 32);
        }

        return s;
    }

    JavaImpl jlong Java_moonaframework_hallway_Cpp_version(DefaultArgs) {
        return __cplusplus;
    }
}