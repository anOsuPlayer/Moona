#include "javaobject.hpp"

namespace moona {

    JavaObject::JavaObject(const jobject& obj) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        this->obj = Moona::defaultJNIEnv().NewGlobalRef(obj);
    }
    JavaObject::JavaObject(const JavaObject& obj) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        this->obj = obj.getJObject();
    }

    JavaObject::~JavaObject() {
        Moona::defaultJNIEnv().DeleteGlobalRef(this->obj);
    }

    JavaObject& JavaObject::operator = (const JavaObject& other) noexcept {
        this->obj = Moona::defaultJNIEnv().NewGlobalRef(other.getJObject());

        return *this;
    }
    JavaObject& JavaObject::operator = (const jobject& other) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        
        this->obj = Moona::defaultJNIEnv().NewGlobalRef(other);
        return *this;
    }

    JValue JavaObject::call(const JavaMethod& jm, const jvalue* args) const {
        const PureSignature ret = jm.getSignature().returnType();
        JValue r;

        switch (ret[0]) {
            case 'Z' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallBooleanMethodA(this->obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallBooleanMethod(this->obj, jm.getJMethod());
                }
                break;
            }
            case 'B' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallByteMethodA(this->obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallByteMethod(this->obj, jm.getJMethod());
                }
                break;
            }
            case 'S' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallShortMethodA(this->obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallShortMethod(this->obj, jm.getJMethod());
                }
                break;
            }
            case 'C' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallCharMethodA(this->obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallCharMethod(this->obj, jm.getJMethod());
                }
                break;
            }
            case 'I' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallIntMethodA(this->obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallIntMethod(this->obj, jm.getJMethod());
                }
                break;
            }
            case 'J' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallLongMethodA(this->obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallLongMethod(this->obj, jm.getJMethod());
                }
                break;
            }
            case 'F' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallFloatMethodA(this->obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallFloatMethod(this->obj, jm.getJMethod());
                }
                break;
            }
            case 'D' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallDoubleMethodA(this->obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallDoubleMethod(this->obj, jm.getJMethod());
                }
                break;
            }
            case 'V' : {
                if (args != nullptr) {
                    Moona::defaultJNIEnv().CallVoidMethodA(this->obj, jm.getJMethod(), args);
                }
                else {
                    Moona::defaultJNIEnv().CallVoidMethod(this->obj, jm.getJMethod());
                }
                break;
            }
            default : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallObjectMethodA(this->obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallObjectMethod(this->obj, jm.getJMethod());
                }
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            throw JVMException();
        }
        return r;
    }

    JValue JavaObject::access(const JavaField& jf) const {
        return JValue();
    }
    void JavaObject::edit(const JavaField& jf, const jvalue& value) {
        
    }

    JavaClass JavaObject::getClass() const {
        if (this->getJObject() == nullptr) {
            throw NullPointerException("Unable to call .getClass() method on a null JavaObject.");
        }
        return JavaClass(Moona::defaultJNIEnv().GetObjectClass(this->obj));
    }

    JavaObject::operator const jobject&() const noexcept {
        return this->obj;
    }
    const jobject& JavaObject::getJObject() const noexcept {
        return this->obj;
    }

    const char* JavaObject::toString() const noexcept {
        jclass clazz = Moona::defaultJNIEnv().GetObjectClass(this->obj);
        jmethodID toString = Moona::defaultJNIEnv().GetMethodID(clazz, "toString", MethodSignature::TO_STRING);

        JavaString str = (jstring) Moona::defaultJNIEnv().CallObjectMethod(this->obj, toString);
        char* res = new char[str.length()]; strcpy(res, str);
        Moona::defaultJNIEnv().DeleteLocalRef(clazz);

        return res;
    }
    bool JavaObject::equals(const JavaObject& obj) const noexcept {
        return this->equals(obj.getJObject());
    }

    bool JavaObject::equals(const jobject& obj) const noexcept {
        jclass clazz = Moona::defaultJNIEnv().GetObjectClass(this->obj);
        jmethodID equals = Moona::defaultJNIEnv().GetMethodID(clazz, "equals", MethodSignature::EQUALS);

        jboolean res = Moona::defaultJNIEnv().CallBooleanMethod(this->obj, equals, obj);
        bool eq = (res == 1) ? true : false;
        Moona::defaultJNIEnv().DeleteLocalRef(clazz);

        return eq;
    }
    bool JavaObject::sameType(const jobject& obj) const noexcept {
        return Moona::defaultJNIEnv().GetObjectClass(this->obj) == Moona::defaultJNIEnv().GetObjectClass(obj);
    }
}