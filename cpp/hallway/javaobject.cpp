#include "javaobject.hpp"

namespace moona {

    JavaObject::JavaObject(const jobject& obj) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->obj = Moona::defaultJNIEnv().NewWeakGlobalRef(obj);
    }
    JavaObject::JavaObject(const JavaObject& obj) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->obj = Moona::defaultJNIEnv().NewWeakGlobalRef(obj.obj);
    }

    JavaObject& JavaObject::operator = (const JavaObject& other) noexcept {
        if (this->obj != nullptr) {
            Moona::defaultJNIEnv().DeleteWeakGlobalRef(this->obj);
        }
        this->obj = Moona::defaultJNIEnv().NewLocalRef(other.getJObject());

        return *this;
    }
    JavaObject& JavaObject::operator = (const jobject& other) {
        if (this->obj != nullptr) {
            Moona::defaultJNIEnv().DeleteWeakGlobalRef(this->obj);
        }
        this->obj = Moona::defaultJNIEnv().NewWeakGlobalRef(other);
        
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
        const char id = jf.getSignature()[0];
        JValue r;

        switch (id) {
            case 'Z' : {
                r = Moona::defaultJNIEnv().GetBooleanField(this->obj, jf.getJField());
                break;
            }
            case 'B' : {
                r = Moona::defaultJNIEnv().GetByteField(this->obj, jf.getJField());
                break;
            }
            case 'S' : {
                r = Moona::defaultJNIEnv().GetShortField(this->obj, jf.getJField());
                break;
            }
            case 'C' : {
                r = Moona::defaultJNIEnv().GetCharField(this->obj, jf.getJField());
                break;
            }
            case 'I' : {
                r = Moona::defaultJNIEnv().GetIntField(this->obj, jf.getJField());
                break;
            }
            case 'J' : {
                r = Moona::defaultJNIEnv().GetLongField(this->obj, jf.getJField());
                break;
            }
            case 'F' : {
                r = Moona::defaultJNIEnv().GetFloatField(this->obj, jf.getJField());
                break;
            }
            case 'D' : {
                r = Moona::defaultJNIEnv().GetDoubleField(this->obj, jf.getJField());
                break;
            }
            default : {
                r = Moona::defaultJNIEnv().GetObjectField(this->obj, jf.getJField());
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            throw JVMException();
        }
        return r;
    }
    void JavaObject::edit(const JavaField& jf, const jvalue& value) {
        const char id = jf.getSignature()[0];

        switch (id) {
            case 'Z' : {
                Moona::defaultJNIEnv().SetBooleanField(this->obj, jf.getJField(), value.z);
                break;
            }
            case 'B' : {
                Moona::defaultJNIEnv().SetByteField(this->obj, jf.getJField(), value.b);
                break;
            }
            case 'S' : {
                Moona::defaultJNIEnv().SetShortField(this->obj, jf.getJField(), value.s);
                break;
            }
            case 'C' : {
                Moona::defaultJNIEnv().SetCharField(this->obj, jf.getJField(), value.s);
                break;
            }
            case 'I' : {
                Moona::defaultJNIEnv().SetIntField(this->obj, jf.getJField(), value.i);
                break;
            }
            case 'J' : {
                Moona::defaultJNIEnv().SetLongField(this->obj, jf.getJField(), value.j);
                break;
            }
            case 'F' : {
                Moona::defaultJNIEnv().SetFloatField(this->obj, jf.getJField(), value.f);
                break;
            }
            case 'D' : {
                Moona::defaultJNIEnv().SetDoubleField(this->obj, jf.getJField(), value.d);
                break;
            }
            default : {
                Moona::defaultJNIEnv().SetObjectField(this->obj, jf.getJField(), value.l);
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            throw JVMException();
        }
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
    jobject JavaObject::getJObject() const noexcept {
        return Moona::defaultJNIEnv().NewLocalRef(this->obj);
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