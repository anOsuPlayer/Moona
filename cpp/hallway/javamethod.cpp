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
            throw new NoSuchMethodException();
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

    const jmethodID& JavaMethod::getJMethod() const noexcept {
        return this->method;
    }
    const MethodSignature& JavaMethod::getSignature() const noexcept {
        return this->sign;
    }

    const char* JavaMethod::toString() const noexcept {
        return this->name;
    }

    JavaMethod::operator const char*() const noexcept {
        return this->name;
    }
    JavaMethod::operator const jmethodID&() const noexcept {
        return this->method;
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
            throw new NoSuchMethodException();
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
}