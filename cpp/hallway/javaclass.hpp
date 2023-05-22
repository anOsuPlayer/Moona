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

            static const JavaMethod CLASS_TO_STRING;

        public:
            JavaClass() = default;
            JavaClass(jclass clazz);
            explicit JavaClass(const char* fullname);
            explicit JavaClass(const JavaPackage& pack, const char* classname);
            JavaClass(const JavaClass& clazz);
            ~JavaClass();

            operator const jclass&() const noexcept;
            const jclass& getJClass() const noexcept;

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

            JavaClass arrayType() const noexcept;
            JavaClass componentType() const noexcept;

            friend std::ostream& operator << (std::ostream& os, const JavaClass& clazz) noexcept {
                os << clazz.classname;
                return os;
            }

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaClass& other) const noexcept override final;
    };
}