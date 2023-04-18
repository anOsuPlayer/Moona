#include "javaobject.hpp"

namespace moona {

    JavaObject::JavaObject(const jobject& obj) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        this->obj = Moona::getMoonaJVM().getJNIEnv().NewGlobalRef(obj);
    }
    JavaObject::JavaObject(const JavaObject& obj) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        this->obj = Moona::getMoonaJVM().getJNIEnv().NewGlobalRef(obj.getJObject());
    }

    JavaObject::~JavaObject() {
        Moona::getMoonaJVM().getJNIEnv().DeleteGlobalRef(this->obj);
    }

    JavaObject& JavaObject::operator = (const JavaObject& other) noexcept {
        this->obj = Moona::getMoonaJVM().getJNIEnv().NewGlobalRef(other.getJObject());

        return *this;
    }
    JavaObject& JavaObject::operator = (const jobject& other) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        
        this->obj = Moona::getMoonaJVM().getJNIEnv().NewGlobalRef(other);
        return *this;
    }

    JavaObject::operator const jobject&() const noexcept {
        return this->obj;
    }
    const jobject& JavaObject::getJObject() const noexcept {
        return this->obj;
    }
}