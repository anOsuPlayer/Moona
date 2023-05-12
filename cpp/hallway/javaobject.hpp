#pragma once

#include "hallwayexception.hpp"
#include "javamethod.hpp"
#include "javavalue.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaMethod;
    class JavaClass;

    class JavaObject : public Object<JavaObject> {
        private:
            const JavaClass* clazz;
            jobject obj;

        public:
            JavaObject() = delete;
            JavaObject(const jobject& obj);
            JavaObject(const JavaObject& obj);
            ~JavaObject();

            JavaObject& operator = (const JavaObject& obj) noexcept;
            JavaObject& operator = (const jobject& obj);

            JValue call(const JavaMethod& jm, const jvalue* args = nullptr) const;

            operator const jobject&() const noexcept;
            
            const jobject& getJObject() const noexcept;
    };
}