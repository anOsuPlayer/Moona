#pragma once

#include <jni.h>

#include "javapackage.hpp"
#include "javaobject.hpp"
#include "javamethod.hpp"
#include "hallwayexception.hpp"
#include "noclassexception.hpp"
#include "javasignature.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaPackage;
    class JavaMethod;
    class JavaStaticMethod;
    class JavaObject;

    class JavaClass : public Object<JavaClass> {
        private:
            char* classname;
            const JavaPackage* pack;
            jclass clazz;

        public:
            JavaClass() = default;
            explicit JavaClass(const JavaPackage& pack, const char* classname);
            JavaClass(const JavaClass& clazz);
            ~JavaClass();

            JavaClass& operator = (const JavaClass& other);
            bool operator == (const JavaClass& other);

            const jclass& getJClass() const noexcept;
            const JavaPackage& getPackage() const noexcept;

            JavaMethod getMethod(const char* name, const MethodSignature& ms) const;
            JavaStaticMethod getStaticMethod(const char* name, const MethodSignature& ms) const;

            JavaObject newInstance() const;

            operator const jclass&() const noexcept;
            operator const char*() const noexcept;

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaClass& other) const noexcept override final;
    };
}