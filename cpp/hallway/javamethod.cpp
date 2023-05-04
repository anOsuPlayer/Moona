#include "javamethod.hpp"

namespace moona {

    JavaMethod::JavaMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        if (name == nullptr) {
            throw NullPointerException("Unable to find a JavaMethod from a nullptr.");
        }
        
        this->name = name;
        this->sign = sign;
        this->method = Moona::defaultJNIEnv().GetMethodID(clazz.getJClass(), name, sign.getSignature());

        if (this->method == nullptr) {
            throw NoSuchMethodException();
        }
    }
    JavaMethod::JavaMethod(const JavaMethod& meth) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->name = meth.name;
        this->sign = meth.sign;
        this->method = meth.method;
    }

    JavaMethod& JavaMethod::operator = (const JavaMethod& other) noexcept {
        this->name = other.name;
        this->sign = other.sign;
        this->method = other.method;

        return *this;
    }
    bool JavaMethod::operator == (const JavaMethod& other) const noexcept {
        return ((strcmp(this->name, other.name) == 0) && this->sign == other.sign);
    }

    JavaMethod::operator const char*() const noexcept {
        return this->name;
    }
    JavaMethod::operator const jmethodID&() const noexcept {
        return this->method;
    }

    const jmethodID& JavaMethod::getJMethod() const noexcept {
        return this->method;
    }
    const MethodSignature& JavaMethod::getSignature() const noexcept {
        return this->sign;
    }

    JValue JavaMethod::callOn(const JavaObject& obj, const jvalue* args) const {
        return obj.call(*this, args);
    }

    const char* JavaMethod::toString() const noexcept {
        return this->name;
    }
    bool JavaMethod::equals(const JavaMethod& other) const noexcept {
        return ((strcmp(this->name, other.name) == 0) && this->sign == other.sign);
    }

    JavaStaticMethod::JavaStaticMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        if (name == nullptr) {
            throw NullPointerException("Unable to find a JavaMethod from a nullptr.");
        }

        this->name = name;
        this->sign = sign;
        this->method = Moona::defaultJNIEnv().GetStaticMethodID(clazz.getJClass(), name, sign.getSignature());

        if (this->method == nullptr) {
            throw NoSuchMethodException();
        }
    }
    JavaStaticMethod::JavaStaticMethod(const JavaStaticMethod& meth) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        
        this->name = meth.name;
        this->sign = meth.sign;
        this->method = meth.method;
    }

    JValue JavaStaticMethod::callOn(const JavaObject& obj, const jvalue* args) const {
        throw UnsupportedOperationException("Unable to call JavaStaticMethod on a JavaObject.");
    }
    JValue JavaStaticMethod::callOn(const JavaClass& clazz, const jvalue* args) const {
        return clazz.call(*this, args);
    }
}