#pragma once

#include <jni.h>

#include "hallwayexception.hpp"
#include "nomethodexception.hpp"
#include "javaclass.hpp"
#include "javasignature.hpp"
#include "javavalue.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaClass;

    class JavaMethod : public Object<JavaMethod> {
        protected:
            const char* name;
            MethodSignature sign;
            jmethodID method;

        public:
            JavaMethod() = default;
            explicit JavaMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign);
            JavaMethod(const JavaMethod& meth);
            virtual ~JavaMethod() = default;
            
            operator const char*() const noexcept;
            operator const jmethodID&() const noexcept;

            virtual JavaMethod& operator = (const JavaMethod& other) noexcept;
            virtual bool operator == (const JavaMethod& other) const noexcept;

            const jmethodID& getJMethod() const noexcept;
            const MethodSignature& getSignature() const noexcept;

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaMethod& other) const noexcept override final;
    };

    class JavaStaticMethod : public Object<JavaStaticMethod>, public JavaMethod {
        public:
            JavaStaticMethod() = default;
            explicit JavaStaticMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign);
            JavaStaticMethod(const JavaStaticMethod& meth);
            virtual ~JavaStaticMethod() = default;
    };
}