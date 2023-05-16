#pragma once

#include <jni.h>

#include "javapackage.hpp"
#include "javaobject.hpp"
#include "javamethod.hpp"
#include "hallwayexception.hpp"
#include "noclassexception.hpp"
#include "javasignature.hpp"
#include "javavalue.hpp"
#include "jvmexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaPackage;
    class JavaMethod;
    class JavaConstructor;
    class JavaStaticMethod;
    class JavaObject;

    class JavaClass : public Object<JavaClass> {
        private:
            char* classname;
            jclass clazz;

            static const JavaMethod CLASS_TO_STRING;

        public:
            JavaClass() = default;
            explicit JavaClass(const char* fullname);
            explicit JavaClass(const JavaPackage& pack, const char* classname);
            JavaClass(const JavaClass& clazz);
            ~JavaClass();

            JavaClass& operator = (const JavaClass& other);
            bool operator == (const JavaClass& other);

            JValue call(const JavaStaticMethod& jsm, const jvalue* args = nullptr) const;

            const jclass& getJClass() const noexcept;

            JavaMethod getMethod(const char* name, const MethodSignature& ms) const;
            JavaStaticMethod getStaticMethod(const char* name, const MethodSignature& ms) const;

            JavaConstructor getConstructor(const ConstructorSignature& cs = ConstructorSignature::DEFAULT) const;

            JavaClass arrayType() const noexcept;
            JavaClass componentType() const noexcept;

            operator const jclass&() const noexcept;
            operator const char*() const noexcept;

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaClass& other) const noexcept override final;
    };
}