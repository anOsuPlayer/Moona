#pragma once

#include "hallwayexception.hpp"
#include "javamethod.hpp"
#include "javafield.hpp"
#include "javavalue.hpp"
#include "javastring.hpp"
#include "jvmexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/notation.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaObject;

    class EffectiveJObject : public Object<EffectiveJObject> {
        protected:
            EffectiveJObject() = default;
        
        public:
            ~EffectiveJObject() = default;

            virtual JavaObject asObject() const noexcept abstract;
    };

    class JavaMethod;
    class JavaField;

    class JavaObject : public Object<JavaObject>, public EffectiveJObject {
        protected:
            jobject obj;

            JavaObject() = default;

            static JValue _call(jobject obj, const JavaMethod& jm, const jvalue* args = nullptr);

            static JValue _access(jobject obj, const JavaField& jf);
            static void _edit(jobject obj, const JavaField& jf, const jvalue& value);

        public:
            JavaObject(const jobject& obj);
            JavaObject(const JavaObject& obj);
            virtual ~JavaObject() = default;

            operator const jobject&() const noexcept;
            jobject getJObject() const noexcept;

            virtual JavaObject asObject() const noexcept override final;

            virtual JavaObject& operator = (const JavaObject& obj) noexcept;
            virtual JavaObject& operator = (const jobject& obj);

            JValue call(const JavaMethod& jm, const jvalue* args = nullptr) const;

            JValue access(const JavaField& jf) const;
            void edit(const JavaField& jf, const jvalue& value);

            JavaClass getClass() const;

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaObject& obj) const noexcept override final;

            bool equals(const jobject& obj) const noexcept;
            bool sameType(const jobject& obj) const noexcept;

            bool isInstanceof(const jclass& clazz) const noexcept;

        friend class JavaMethod;
        friend class JavaField;
    };

    class JavaObjectArray : public Object<JavaObjectArray> {
        protected:
            jobjectArray arr;
    };
}