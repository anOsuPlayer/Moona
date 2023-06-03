#pragma once

#include <jni.h>

#include "javapackage.hpp"
#include "javaobject.hpp"
#include "javamethod.hpp"
#include "javafield.hpp"
#include "hallwayexception.hpp"
#include "noclassexception.hpp"
#include "javasignature.hpp"
#include "javavalue.hpp"
#include "jvmexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"
#include "../exceptions/illegalexception.hpp"

namespace moona {

    class JavaPackage;
    class JavaMethod;
    class JavaConstructor;
    class JavaStaticMethod;
    class JavaField;
    class JavaStaticField;
    class JavaObject;

    class JavaClass : public Object<JavaClass> {
        private:
            char* classname;
            jclass clazz;

        public:
            JavaClass() = default;
            JavaClass(jclass clazz);
            explicit JavaClass(const char* fullname);
            explicit JavaClass(const JavaPackage& pack, const char* classname);
            JavaClass(const JavaClass& clazz);
            ~JavaClass();

            operator const jclass&() const noexcept;
            jclass getJClass() const noexcept;

            virtual JavaObject asObject() const noexcept final;

            JavaClass& operator = (const JavaClass& other);
            bool operator == (const JavaClass& other);

            JValue call(const JavaStaticMethod& jsm, const jvalue* args = nullptr) const;

            JValue access(const JavaStaticField& jsf) const;
            void edit(const JavaStaticField& jsf, const jvalue& value);

            JavaMethod getMethod(const char* name, const MethodSignature& ms) const;
            JavaStaticMethod getStaticMethod(const char* name, const MethodSignature& ms) const;

            JavaConstructor getConstructor(const ConstructorSignature& cs = ConstructorSignature::DEFAULT) const;

            JavaField getField(const char* name, const FieldSignature& ms);
            JavaStaticField getStaticField(const char* name, const FieldSignature& ms);

            constexpr bool isArray() const noexcept;

            JavaClass arrayType(size_t level = 1) const;
            JavaClass componentType() const noexcept;

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaClass& other) const noexcept override final;
    };
}