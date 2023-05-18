#pragma once

#include "hallwayexception.hpp"
#include "javamethod.hpp"
#include "javavalue.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaMethod;

    class JavaObject : public Object<JavaObject> {
        protected:
            JavaObject() = default;

            jobject obj;

        public:
            JavaObject(const jobject& obj);
            JavaObject(const JavaObject& obj);
            virtual ~JavaObject();

            virtual JavaObject& operator = (const JavaObject& obj) noexcept;
            virtual JavaObject& operator = (const jobject& obj);

            JValue call(const JavaMethod& jm, const jvalue* args = nullptr) const;

            JavaClass getClass() const;

            operator const jobject&() const noexcept;
            
            const jobject& getJObject() const noexcept;
    };
}