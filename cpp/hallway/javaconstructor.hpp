#pragma once

#include <jni.h>

#include "javamethod.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaConstructor : public Object<JavaConstructor>, public JavaMethod {
        public:
            JavaConstructor() = default;
            explicit JavaConstructor(const JavaClass& clazz, const ConstructorSignature& cs);
            explicit JavaConstructor(const JavaClass& clazz);
            JavaConstructor(const JavaConstructor& con);
            ~JavaConstructor() = default;
    };
}