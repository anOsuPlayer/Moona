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
            JavaClass* clazz;
            FieldSignature sign;
            jfieldID field;

        public:
            JavaField() = default;
            explicit JavaField(const char* name, JavaClass& clazz, const FieldSignature& sign);
            JavaField(const JavaField& meth);
            virtual ~JavaField() = default;
            
            const jfieldID& getJField() const noexcept;
            operator const jfieldID&() const noexcept;

            virtual JavaField& operator = (const JavaField& other) noexcept;
            virtual bool operator == (const JavaField& other) const noexcept;

            const FieldSignature& getSignature() const noexcept;

            virtual JValue accessOn(const JavaObject& obj) const;
            virtual void editOn(JavaObject& obj, const jvalue& value);

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaField& other) const noexcept override final;
    };

    class JavaStaticField : public Object<JavaStaticField>, public JavaField {
        public:
            JavaStaticField() = default;
            explicit JavaStaticField(const char* name, JavaClass& clazz, const FieldSignature& sign);
            JavaStaticField(const JavaStaticField& meth);
            virtual ~JavaStaticField() = default;

            virtual JValue accessOn(const JavaObject& obj) const override final;
            virtual void editOn(JavaObject& obj, const jvalue& value) override final;

            JValue access() const;
            void edit(const jvalue& value) const;
    };
}