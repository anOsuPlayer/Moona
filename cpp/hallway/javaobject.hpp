#pragma once

#include "hallwayexception.hpp"
#include "javamethod.hpp"
#include "javafield.hpp"
#include "javavalue.hpp"
#include "javastring.hpp"
#include "jvmexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaMethod;
    class JavaField;

    class JavaObject : public Object<JavaObject> {
        protected:
            jobject obj;

            JavaObject() = default;

        public:
            JavaObject(const jobject& obj);
            JavaObject(const JavaObject& obj);
            virtual ~JavaObject() = default;

            operator const jobject&() const noexcept;
            jobject getJObject() const noexcept;

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
    };
}