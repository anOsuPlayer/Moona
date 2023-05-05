#include "javafield.hpp"

namespace moona {

    JavaField::JavaField(const char* name, const JavaClass& clazz, const FieldSignature& sign) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        if (name == nullptr) {
            throw NullPointerException("Unable to find a JavaField from a nullptr.");
        }
        
        this->clazz = &clazz;
        this->name = name;
        this->sign = sign;
        this->field = Moona::defaultJNIEnv().GetFieldID(clazz.getJClass(), name, sign.getSignature());

        if (this->field == nullptr) {
            throw NoSuchFieldException();
        }
    }
    JavaField::JavaField(const JavaField& meth) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->clazz = meth.clazz;
        this->name = meth.name;
        this->sign = meth.sign;
        this->field = meth.field;
    }

    JavaField& JavaField::operator = (const JavaField& other) noexcept {
        this->clazz = other.clazz;
        this->name = other.name;
        this->sign = other.sign;
        this->field = other.field;

        return *this;
    }
    bool JavaField::operator == (const JavaField& other) const noexcept {
        return ((strcmp(this->name, other.name) == 0) && this->sign == other.sign);
    }

    JavaField::operator const char*() const noexcept {
        return this->name;
    }
    JavaField::operator const jfieldID&() const noexcept {
        return this->field;
    }

    const jfieldID& JavaField::getJField() const noexcept {
        return this->field;
    }
    const FieldSignature& JavaField::getSignature() const noexcept {
        return this->sign;
    }

    const char* JavaField::toString() const noexcept {
        return this->name;
    }
    bool JavaField::equals(const JavaField& other) const noexcept {
        return ((strcmp(this->name, other.name) == 0) && this->sign == other.sign);
    }

    JavaStaticField::JavaStaticField(const char* name, const JavaClass& clazz, const FieldSignature& sign) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        if (name == nullptr) {
            throw NullPointerException("Unable to find a JavaMethod from a nullptr.");
        }

        this->clazz = &clazz;
        this->name = name;
        this->sign = sign;
        this->field = Moona::defaultJNIEnv().GetStaticFieldID(clazz.getJClass(), name, sign.getSignature());

        if (this->field == nullptr) {
            throw NoSuchMethodException();
        }
    }
    JavaStaticField::JavaStaticField(const JavaStaticField& meth) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        
        this->clazz = meth.clazz;
        this->name = meth.name;
        this->sign = meth.sign;
        this->field = meth.field;
    }
}