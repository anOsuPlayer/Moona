#pragma once

#include <jni.h>

#include "javaarray.hpp"
#include "javaobject.hpp"
#include "../base/object.hpp"
#include "../exceptions/illegalexception.hpp"
#include "../exceptions/indexexception.hpp"

namespace moona {

    class JavaString : public Object<JavaString> {
        private:
            char* text;

        public:
            JavaString();
            JavaString(const char* str, size_t init = 0, size_t end = 0);
            JavaString(JavaCharArray& charr, size_t init = 0, size_t end = 0);
            JavaString(jstring str, size_t init = 0, size_t end = 0);
            JavaString(const JavaString& str, size_t init = 0, size_t end = 0);
            virtual ~JavaString();

            JavaString& operator = (const JavaString& str) noexcept;
            JavaString& operator = (jstring str) noexcept;

            bool operator == (const JavaString& str) const noexcept;
            bool operator == (jstring str) const noexcept;

            char& operator [] (size_t index);

            friend std::ostream& operator << (std::ostream& os, const JavaString& str) noexcept {
                os << str.text;
                return os;
            }

            size_t length() const noexcept;

            JavaCharArray toCharArray() const noexcept;

            operator jstring() const noexcept;
            jstring getJString() const noexcept;

            virtual JavaObject asObject() const noexcept final;

            operator const char*() const noexcept;

            virtual const char* toString() const noexcept override final;
            virtual bool equals(const JavaString& str) const noexcept override final;
    };
}