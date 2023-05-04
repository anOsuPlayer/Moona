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
        this->obj = Moona::defaultJNIEnv().NewGlobalRef(obj.getJObject());
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
        return JValue();
    }

    JavaObject::operator const jobject&() const noexcept {
        return this->obj;
    }
    const jobject& JavaObject::getJObject() const noexcept {
        return this->obj;
    }
}