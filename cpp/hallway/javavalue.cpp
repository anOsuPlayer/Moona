#include "javavalue.hpp"

namespace moona {

    JValue::JValue() {
        this->tag = ValueTag::NONE;
    }
    JValue::JValue(const jboolean& z) : z{z} {
        this->tag = ValueTag::BOOLEAN;
    }
    JValue::JValue(const jbyte& b) : b{b} {
        this->tag = ValueTag::BYTE;
    }
    JValue::JValue(const jshort& s) : s{s} {
        this->tag = ValueTag::SHORT;
    }
    JValue::JValue(const jchar& c) : c{c} {
        this->tag = ValueTag::CHAR;
    }
    JValue::JValue(const jint& i) : i{i} {
        this->tag = ValueTag::INT;
    }
    JValue::JValue(const jlong& j) : j{j} {
        this->tag = ValueTag::LONG;
    }
    JValue::JValue(const jfloat& f) : f{f} {
        this->tag = ValueTag::FLOAT;
    }
    JValue::JValue(const jdouble& d) : d{d} {
        this->tag = ValueTag::DOUBLE;
    }
    JValue::JValue(const jobject& o) : o{o} {
        this->tag = ValueTag::OBJECT;
    }
    JValue::JValue(const char* str) : o{Moona::defaultJNIEnv().NewStringUTF(str)} {
        this->tag = ValueTag::STRING;
    }

    JValue::JValue(const JValue& val) {
        *this = val;
    }

    const BadCastException JValue::badcast = BadCastException("Illegal JValue cast.");

    JValue& JValue::operator = (const jboolean& z) noexcept {
        this->tag = ValueTag::BOOLEAN;
        this->z = z;

        return *this;
    }
    JValue& JValue::operator = (const jbyte& b) noexcept {
        this->tag = ValueTag::BYTE;
        this->b = b;

        return *this;
    }
    JValue& JValue::operator = (const jshort& s) noexcept {
        this->tag = ValueTag::SHORT;
        this->s = s;

        return *this;
    }
    JValue& JValue::operator = (const jchar& c) noexcept {
        this->tag = ValueTag::CHAR;
        this->c = c;

        return *this;
    }
    JValue& JValue::operator = (const jint& i) noexcept {
        this->tag = ValueTag::INT;
        this->i = i;

        return *this;
    }
    JValue& JValue::operator = (const jlong& j) noexcept {
        this->tag = ValueTag::LONG;
        this->j = j;

        return *this;
    }
    JValue& JValue::operator = (const jfloat& f) noexcept {
        this->tag = ValueTag::FLOAT;
        this->f = f;

        return *this;
    }
    JValue& JValue::operator = (const jdouble& d) noexcept {
        this->tag = ValueTag::DOUBLE;
        this->d = d;

        return *this;
    }
    JValue& JValue::operator = (const jobject& o) noexcept {
        this->tag = ValueTag::OBJECT;
        this->o = o;

        return *this;
    }
    JValue& JValue::operator = (const char* str) noexcept {
        this->tag = ValueTag::STRING;
        this->o = Moona::defaultJNIEnv().NewStringUTF(str);

        return *this;
    }

    JValue& JValue::operator = (const JValue& val) noexcept {
        this->tag = val.tag;

        switch (val.tag) {
            case ValueTag::BOOLEAN : {
                this->z = val.z;
                break;
            }
            case ValueTag::BYTE : {
                this->b = val.b;
                break;
            }
            case ValueTag::SHORT : {
                this->s = val.s;
                break;
            }
            case ValueTag::CHAR : {
                this->c = val.c;
                break;
            }
            case ValueTag::INT : {
                this->i = val.i;
                break;
            }
            case ValueTag::LONG : {
                this->j = val.j;
                break;
            }
            case ValueTag::FLOAT : {
                this->f = val.f;
                break;
            }
            case ValueTag::DOUBLE : {
                this->d = val.d;
                break;
            }
            case ValueTag::STRING : 
            case ValueTag::OBJECT : {
                this->o = val.o;
                break;
            }
        }

        return *this;
    }

    bool JValue::operator == (const JValue& val) const noexcept {
        if (this->tag <= 0 || val.tag <= 0) {
            switch (this->tag) {
                case ValueTag::STRING : {
                    const char* chars1 = Moona::defaultJNIEnv().GetStringUTFChars((jstring) this->o, 0);
                    const char* chars2 = Moona::defaultJNIEnv().GetStringUTFChars((jstring) val.o, 0);
                    bool comp = (strcmp(chars1, chars2) == 1) ? true : false;

                    Moona::defaultJNIEnv().ReleaseStringUTFChars((jstring) this->o, chars1);
                    Moona::defaultJNIEnv().ReleaseStringUTFChars((jstring) val.o, chars2);
                    return comp;
                }
                case ValueTag::OBJECT : {
                    return (val.tag == ValueTag::OBJECT) && this->o == val.o;
                }
                case ValueTag::BOOLEAN : {
                    return (val.tag == ValueTag::BOOLEAN) && this->z == val.z;
                }
                case ValueTag::NONE : {
                    return val.tag == ValueTag::NONE;
                }
            }
        }
        else {
            double first, second;

            switch (this->tag) {
                case ValueTag::BYTE : {
                    first = this->b;
                }
                case ValueTag::CHAR : {
                    first = this->c;
                }
                case ValueTag::SHORT : {
                    first = this->s;
                }
                case ValueTag::INT : {
                    first = this->i;
                }
                case ValueTag::LONG : {
                    first = this->j;
                }
                case ValueTag::FLOAT : {
                    first = this->f;
                }
                case ValueTag::DOUBLE : {
                    first = this->d;
                }
            }
            switch (val.tag) {
                case ValueTag::BYTE : {
                    second = val.b;
                }
                case ValueTag::CHAR : {
                    second = val.c;
                }
                case ValueTag::SHORT : {
                    second = val.s;
                }
                case ValueTag::INT : {
                    second = val.i;
                }
                case ValueTag::LONG : {
                    second = val.j;
                }
                case ValueTag::FLOAT : {
                    second = val.f;
                }
                case ValueTag::DOUBLE : {
                    second = val.d;
                }
            }

            return first == second;
        }

        return false;
    }

    void JValue::setValue(const jboolean& z) noexcept {
        this->tag = ValueTag::BOOLEAN;
        this->z = z;
    }
    void JValue::setValue(const jbyte& b) noexcept {
        this->tag = ValueTag::BYTE;
        this->b = b;
    }
    void JValue::setValue(const jshort& s) noexcept {
        this->tag = ValueTag::SHORT;
        this->s = s;
    }
    void JValue::setValue(const jchar& c) noexcept {
        this->tag = ValueTag::CHAR;
        this->c = c;
    }
    void JValue::setValue(const jint& i) noexcept {
        this->tag = ValueTag::INT;
        this->i = i;
    }
    void JValue::setValue(const jlong& j) noexcept {
        this->tag = ValueTag::LONG;
        this->j = j;
    }
    void JValue::setValue(const jfloat& f) noexcept {
        this->tag = ValueTag::FLOAT;
        this->f = f;
    }
    void JValue::setValue(const jdouble& d) noexcept {
        this->tag = ValueTag::DOUBLE;
        this->d = d;
    }
    void JValue::setValue(const jobject& o) noexcept {
        this->tag = ValueTag::OBJECT;
        this->o = o;
    }
    void JValue::setValue(const char* str) noexcept {
        this->tag = ValueTag::STRING;
        this->o = Moona::defaultJNIEnv().NewStringUTF(str);
    }

    JValue::operator const jboolean() const {
        if (this->tag != ValueTag::BOOLEAN) {
            throw JValue::badcast;
        }
        return this->z;
    }
    JValue::operator const jbyte() const {
        return JValue::castNumber<jbyte>(*this);
    }
    JValue::operator const jshort() const {
        return JValue::castNumber<jshort>(*this);
    }
    JValue::operator const jchar() const {
        return JValue::castNumber<jchar>(*this);
    }
    JValue::operator const jint() const {
        return JValue::castNumber<jint>(*this);
    }
    JValue::operator const jlong() const {
        return JValue::castNumber<jlong>(*this);
    }
    JValue::operator const jfloat() const {
        return JValue::castNumber<jfloat>(*this);
    }
    JValue::operator const jdouble() const {
        return JValue::castNumber<jdouble>(*this);
    }
    JValue::operator const jobject() const {
        if (this->tag != ValueTag::OBJECT) {
            throw JValue::badcast;
        }
        return this->o;
    }

    JValue::operator const jvalue() const {
        jvalue val;

        switch (this->tag) {
            case ValueTag::BOOLEAN : {
                val.z = this->z;
                break;
            }
            case ValueTag::BYTE : {
                val.b = this->b;
                break;
            }
            case ValueTag::CHAR : {
                val.c = this->c;
                break;
            }
            case ValueTag::SHORT : {
                val.s = this->s;
                break;
            }
            case ValueTag::INT : {
                val.i = this->i;
                break;
            }
            case ValueTag::LONG : {
                val.j = this->j;
                break;
            }
            case ValueTag::FLOAT : {
                val.f = this->f;
                break;
            }
            case ValueTag::DOUBLE : {
                val.d = this->d;
                break;
            }
            case ValueTag::STRING :
            case ValueTag::OBJECT : {
                val.l = this->o;
                break;
            }
        }

        return val;
    }
}