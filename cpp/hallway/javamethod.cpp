#include "javamethod.hpp"

namespace moona {

    JavaMethod::JavaMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->clazz = clazz; this->sign = sign;
        this->method = Moona::getMoonaJVM().getJNIEnv().GetMethodID(clazz.getJClass(), name, sign.getSignature());

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
        this->clazz = meth.clazz;
        this->method = meth.method;
    }

    JavaStaticMethod::JavaStaticMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->clazz = clazz; this->sign = sign;
        this->method = Moona::getMoonaJVM().getJNIEnv().GetStaticMethodID(clazz.getJClass(), name, sign.getSignature());

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
        this->clazz = meth.clazz;
        this->method = meth.method;
    }
}