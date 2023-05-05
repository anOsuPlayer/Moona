#pragma once

#include "hallwayexception.hpp"
#include "nofieldexception.hpp"
#include "javaclass.hpp"
#include "javasignature.hpp"
#include "javavalue.hpp"
#include "javaobject.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"
#include "../exceptions/unsupportedexception.hpp"

namespace moona {

    class JavaClass;
    class JavaObject;

    class JavaField : public Object<JavaField> {
        protected:
            const char* name;
            const JavaClass* clazz;
            FieldSignature sign;
            jfieldID field;

        public:
            JavaField() = default;
            explicit JavaField(const char* name, const JavaClass& clazz, const FieldSignature& sign);
            JavaField(const JavaField& meth);
            virtual ~JavaField() = default;
            
            operator const char*() const noexcept;
            operator const jfieldID&() const noexcept;

            virtual JavaField& operator = (const JavaField& other) noexcept;
            virtual bool operator == (const JavaField& other) const noexcept;

            const jfieldID& getJField() const noexcept;
            const FieldSignature& getSignature() const noexcept;

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaField& other) const noexcept override final;
    };

    class JavaStaticField : public Object<JavaStaticField>, public JavaField {
        public:
            JavaStaticField() = default;
            explicit JavaStaticField(const char* name, const JavaClass& clazz, const FieldSignature& sign);
            JavaStaticField(const JavaStaticField& meth);
            virtual ~JavaStaticField() = default;
    };
}