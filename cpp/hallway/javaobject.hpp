#pragma once

#include "hallwayexception.hpp"
#include "javamethod.hpp"
#include "javavalue.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaObject : public Object<JavaObject> {
        private:
            jobject obj;

        public:
            JavaObject() = delete;
            JavaObject(const jobject& obj);
            JavaObject(const JavaObject& obj);
            ~JavaObject();

            JavaObject& operator = (const JavaObject& obj) noexcept;
            JavaObject& operator = (const jobject& obj);

            JValue call(const JavaMethod& jm) const;

            operator const jobject&() const noexcept;
            
            const jobject& getJObject() const noexcept;
    };
}