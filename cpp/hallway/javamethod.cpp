#include "javamethod.hpp"

namespace moona {

    JavaMethod::JavaMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign) {
        this->clazz = &clazz; this->sign = &sign;

        this->method = Moona::getMoonaJVM().getJNIEnv().GetMethodID(clazz.getJClass(), name, sign.getSignature());
    }

    JavaStaticMethod::JavaStaticMethod(const char* name, const JavaClass& clazz, const MethodSignature& sign) {
        this->clazz = &clazz; this->sign = &sign;

        this->method = Moona::getMoonaJVM().getJNIEnv().GetStaticMethodID(clazz.getJClass(), name, sign.getSignature());
    }
}