#pragma once

#include <jni.h>

#include "hallwayexception.hpp"
#include "javaclass.hpp"
#include "javamethodsignature.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaMethod : public Object<JavaMethod> {
        protected:
            JavaMethod() = default;

            const char* name;
            const MethodSignature* sign;
            const JavaClass* clazz;
            jmethodID method;

        public:
            explicit JavaMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign);
            virtual ~JavaMethod() = default;
    };

    class JavaStaticMethod : public Object<JavaStaticMethod>, public JavaMethod {
        protected:
            JavaStaticMethod() = default;
            
        public:
            explicit JavaStaticMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign);
            virtual ~JavaStaticMethod() = default;
    };
}