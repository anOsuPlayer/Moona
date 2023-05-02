#pragma once

#include <jni.h>
#include <iostream>

#include "javanotation.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"
#include "../exceptions/castexception.hpp"

namespace moona {

    enum ValueTag {
        NONE = -1,
        BOOLEAN = 0,
        BYTE = 1,
        SHORT = 2,
        CHAR = 3,
        INT = 4,
        LONG = 5,
        FLOAT = 6,
        DOUBLE = 7,
        OBJECT = -2
    };

    class JValue : public Object<JValue> {
        private:
            union {
                jboolean z;
                jbyte b;
                jshort s;
                jchar c;
                jint i;
                jlong j;
                jfloat f;
                jdouble d;
                jobject o;
            };
            ValueTag tag;

            static const BadCastException badcast;

            template <typename T> static const T castNumber(const JValue& val) {
                switch(val.tag) {
                    case ValueTag::BYTE : {
                        return static_cast<T>(val.b);
                    }
                    case ValueTag::SHORT : {
                        return static_cast<T>(val.s);
                    }
                    case ValueTag::CHAR : {
                        return static_cast<T>(val.c);
                    }
                    case ValueTag::INT : {
                        return static_cast<T>(val.i);
                    }
                    case ValueTag::LONG : {
                        return static_cast<T>(val.j);
                    }
                    case ValueTag::FLOAT : {
                        return static_cast<T>(val.f);
                    }
                    case ValueTag::DOUBLE : {
                        return static_cast<T>(val.d);
                    }
                    default : {
                        throw JValue::badcast;
                    }
                }
            }

        public:
            JValue();
            JValue(const jboolean& z);
            JValue(const jbyte& b);
            JValue(const jshort& s);
            JValue(const jchar& c);
            JValue(const jint& i);
            JValue(const jlong& j);
            JValue(const jfloat& f);
            JValue(const jdouble& d);
            JValue(const jobject& o);
            JValue(const JValue& val);
            ~JValue() = default;

            JValue& operator = (const jboolean& z) noexcept;
            JValue& operator = (const jbyte& b) noexcept;
            JValue& operator = (const jshort& s) noexcept;
            JValue& operator = (const jchar& c) noexcept;
            JValue& operator = (const jint& i) noexcept;
            JValue& operator = (const jlong& j) noexcept;
            JValue& operator = (const jfloat& f) noexcept;
            JValue& operator = (const jdouble& d) noexcept;
            JValue& operator = (const jobject& o) noexcept;

            JValue& operator = (const JValue& val) noexcept;

            bool operator == (const JValue& val) const noexcept;

            void setValue(const jboolean& z) noexcept;
            void setValue(const jbyte& b) noexcept;
            void setValue(const jshort& s) noexcept;
            void setValue(const jchar& c) noexcept;
            void setValue(const jint& i) noexcept;
            void setValue(const jlong& j) noexcept;
            void setValue(const jfloat& f) noexcept;
            void setValue(const jdouble& d) noexcept;
            void setValue(const jobject& o) noexcept;

            operator const jboolean() const;
            operator const jbyte() const;
            operator const jshort() const;
            operator const jchar() const;
            operator const jint() const;
            operator const jlong() const;
            operator const jfloat() const;
            operator const jdouble() const;
            operator const jobject() const;

            friend std::ostream& operator << (std::ostream& os, const JValue& val) noexcept {
                switch (val.tag) {
                    case ValueTag::BOOLEAN : {
                        os << std::boolalpha << val.z << std::noboolalpha;
                        break;
                    }
                    case ValueTag::BYTE : {
                        os << val.b;
                        break;
                    }
                    case ValueTag::SHORT : {
                        os << val.s;
                        break;
                    }
                    case ValueTag::CHAR : {
                        os << val.c;
                        break;
                    }
                    case ValueTag::INT : {
                        os << val.i;
                        break;
                    }
                    case ValueTag::LONG : {
                        os << val.j;
                        break;
                    }
                    case ValueTag::FLOAT : {
                        os << val.f;
                        break;
                    }
                    case ValueTag::DOUBLE : {
                        os << val.d;
                        break;
                    }
                    case ValueTag::OBJECT : {
                        os << val.o;
                        break;
                    }
                    case ValueTag::NONE : {
                        os << "Empty JValue";
                        break;
                    }
                }
                return os;
            }
    };
}