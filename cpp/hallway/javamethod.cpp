#include "javamethod.hpp"

namespace moona {

    JavaMethod::JavaMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        if (name == nullptr) {
            throw NullPointerException("Unable to find a JavaMethod from a nullptr.");
        }
        
        this->clazz = &clazz;
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

        this->clazz = meth.clazz;
        this->name = meth.name;
        this->sign = meth.sign;
        this->method = meth.method;
    }

    JavaMethod& JavaMethod::operator = (const JavaMethod& other) noexcept {
        this->clazz = other.clazz;
        this->name = other.name;
        this->sign = other.sign;
        this->method = other.method;

        return *this;
    }
    bool JavaMethod::operator == (const JavaMethod& other) const noexcept {
        return ((strcmp(this->name, other.name) == 0) && this->sign == other.sign);
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
    JValue JavaMethod::callOn(jobject obj, const jvalue* args) const {
        return JavaObject::_call(obj, *this, args);
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

        this->clazz = &clazz;
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
        
        this->clazz = meth.clazz;
        this->name = meth.name;
        this->sign = meth.sign;
        this->method = meth.method;
    }

    JValue JavaStaticMethod::callOn(const JavaObject& obj, const jvalue* args) const {
        throw UnsupportedOperationException("Unable to call JavaStaticMethod on a JavaObject.");
    }
    JValue JavaStaticMethod::callOn(jobject obj, const jvalue* args) const {
        throw UnsupportedOperationException("Unable to call JavaStaticMethod on a JavaObject.");
    }

    JValue JavaStaticMethod::call(const jvalue* args) const {
        return this->clazz->call(*this, args);
    }

    JavaConstructor::JavaConstructor(const JavaClass& clazz, const ConstructorSignature& cs) : JavaMethod("<init>", clazz, cs) {
    }

    JavaConstructor::JavaConstructor(const JavaClass& clazz) : JavaMethod("<init>", clazz, ConstructorSignature::DEFAULT) {
    }

    JavaConstructor::JavaConstructor(const JavaConstructor& con) : JavaMethod(con) {
    }

    JavaObject JavaConstructor::newInstance(const jvalue* args) const {
        JavaObject obj = (args == nullptr) ? Moona::defaultJNIEnv().NewObject(this->clazz->getJClass(), this->method)
            : Moona::defaultJNIEnv().NewObjectA(this->clazz->getJClass(), this->method, args);
        
        if (obj == nullptr) {
            throw JVMException("Object creation failed.");
        }
        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            Moona::defaultJNIEnv().ExceptionDescribe();
            throw JVMException();
        }

        return obj;
    }
    JavaObjectArray JavaConstructor::newArray(size_t length) const {
        JavaObjectArray arr = Moona::defaultJNIEnv().NewObjectArray(length, this->clazz->clazz, nullptr);

        if (arr == nullptr) {
            throw JVMException("Array creation failed.");
        }
        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            Moona::defaultJNIEnv().ExceptionDescribe();
            throw JVMException();
        }

        return arr;
    }
}