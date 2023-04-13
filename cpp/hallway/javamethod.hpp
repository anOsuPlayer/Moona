#pragma once

#include <jni.h>

#include "hallwayexception.hpp"
#include "nomethodexception.hpp"
#include "javaclass.hpp"
#include "javamethodsignature.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaMethod : public Object<JavaMethod> {
        protected:
            const char* name;
            MethodSignature sign;
            JavaClass clazz;
            jmethodID method;

        public:
            JavaMethod() = default;
            explicit JavaMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign);
            JavaMethod(const JavaMethod& meth);
            virtual ~JavaMethod() = default;
    };

    class JavaStaticMethod : public Object<JavaStaticMethod>, public JavaMethod {
        public:
            JavaStaticMethod() = default;
            explicit JavaStaticMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign);
            JavaStaticMethod(const JavaStaticMethod& meth);
            virtual ~JavaStaticMethod() = default;
    };
}