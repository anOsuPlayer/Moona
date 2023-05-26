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

    JValue JavaObject::_call(jobject obj, const JavaMethod& jm, const jvalue* args) {
        const PureSignature ret = jm.getSignature().returnType();
        JValue r;

        switch (ret[0]) {
            case 'Z' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallBooleanMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallBooleanMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'B' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallByteMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallByteMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'S' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallShortMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallShortMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'C' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallCharMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallCharMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'I' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallIntMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallIntMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'J' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallLongMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallLongMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'F' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallFloatMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallFloatMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'D' : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallDoubleMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallDoubleMethod(obj, jm.getJMethod());
                }
                break;
            }
            case 'V' : {
                if (args != nullptr) {
                    Moona::defaultJNIEnv().CallVoidMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    Moona::defaultJNIEnv().CallVoidMethod(obj, jm.getJMethod());
                }
                break;
            }
            default : {
                if (args != nullptr) {
                    r = Moona::defaultJNIEnv().CallObjectMethodA(obj, jm.getJMethod(), args);
                }
                else {
                    r = Moona::defaultJNIEnv().CallObjectMethod(obj, jm.getJMethod());
                }
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            throw JVMException();
        }
        return r;
    }
    JValue JavaObject::call(const JavaMethod& jm, const jvalue* args) const {
        return JavaObject::_call(this->obj, jm, args);
    }

    JValue JavaObject::_access(jobject obj, const JavaField& jf) {
        const char id = jf.getSignature()[0];
        JValue r;

        switch (id) {
            case 'Z' : {
                r = Moona::defaultJNIEnv().GetBooleanField(obj, jf.getJField());
                break;
            }
            case 'B' : {
                r = Moona::defaultJNIEnv().GetByteField(obj, jf.getJField());
                break;
            }
            case 'S' : {
                r = Moona::defaultJNIEnv().GetShortField(obj, jf.getJField());
                break;
            }
            case 'C' : {
                r = Moona::defaultJNIEnv().GetCharField(obj, jf.getJField());
                break;
            }
            case 'I' : {
                r = Moona::defaultJNIEnv().GetIntField(obj, jf.getJField());
                break;
            }
            case 'J' : {
                r = Moona::defaultJNIEnv().GetLongField(obj, jf.getJField());
                break;
            }
            case 'F' : {
                r = Moona::defaultJNIEnv().GetFloatField(obj, jf.getJField());
                break;
            }
            case 'D' : {
                r = Moona::defaultJNIEnv().GetDoubleField(obj, jf.getJField());
                break;
            }
            default : {
                r = Moona::defaultJNIEnv().GetObjectField(obj, jf.getJField());
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            throw JVMException();
        }
        return r;
    }
    JValue JavaObject::access(const JavaField& jf) const {
        return JavaObject::_access(this->obj, jf);
    }

    void JavaObject::_edit(jobject obj, const JavaField& jf, const jvalue& value) {
        const char id = jf.getSignature()[0];

        switch (id) {
            case 'Z' : {
                Moona::defaultJNIEnv().SetBooleanField(obj, jf.getJField(), value.z);
                break;
            }
            case 'B' : {
                Moona::defaultJNIEnv().SetByteField(obj, jf.getJField(), value.b);
                break;
            }
            case 'S' : {
                Moona::defaultJNIEnv().SetShortField(obj, jf.getJField(), value.s);
                break;
            }
            case 'C' : {
                Moona::defaultJNIEnv().SetCharField(obj, jf.getJField(), value.s);
                break;
            }
            case 'I' : {
                Moona::defaultJNIEnv().SetIntField(obj, jf.getJField(), value.i);
                break;
            }
            case 'J' : {
                Moona::defaultJNIEnv().SetLongField(obj, jf.getJField(), value.j);
                break;
            }
            case 'F' : {
                Moona::defaultJNIEnv().SetFloatField(obj, jf.getJField(), value.f);
                break;
            }
            case 'D' : {
                Moona::defaultJNIEnv().SetDoubleField(obj, jf.getJField(), value.d);
                break;
            }
            default : {
                Moona::defaultJNIEnv().SetObjectField(obj, jf.getJField(), value.l);
                break;
            }
        }

        if (Moona::defaultJNIEnv().ExceptionCheck()) {
            throw JVMException();
        }
    }
    void JavaObject::edit(const JavaField& jf, const jvalue& value) {
        return JavaObject::_edit(this->obj, jf, value);
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