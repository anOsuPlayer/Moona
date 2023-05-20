#include "javafield.hpp"

namespace moona {

    JavaField::JavaField(const char* name, JavaClass& clazz, const FieldSignature& sign) {
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
    JavaField::JavaField(const JavaField& field) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->clazz = field.clazz;
        this->name = field.name;
        this->sign = field.sign;
        this->field = field.field;
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

    JavaField::operator const jfieldID&() const noexcept {
        return this->field;
    }

    const jfieldID& JavaField::getJField() const noexcept {
        return this->field;
    }
    const FieldSignature& JavaField::getSignature() const noexcept {
        return this->sign;
    }

    JValue JavaField::accessOn(const JavaObject& obj) const {
        return obj.access(*this);
    }
    void JavaField::editOn(JavaObject& obj, const jvalue& value) {
        obj.edit(*this, value);
    }

    const char* JavaField::toString() const noexcept {
        return this->name;
    }
    bool JavaField::equals(const JavaField& other) const noexcept {
        return ((strcmp(this->name, other.name) == 0) && this->sign == other.sign);
    }

    JavaStaticField::JavaStaticField(const char* name, JavaClass& clazz, const FieldSignature& sign) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        if (name == nullptr) {
            throw NullPointerException("Unable to find a Javafieldod from a nullptr.");
        }

        this->clazz = &clazz;
        this->name = name;
        this->sign = sign;
        this->field = Moona::defaultJNIEnv().GetStaticFieldID(clazz.getJClass(), name, sign.getSignature());

        if (this->field == nullptr) {
            throw NoSuchFieldException();
        }
    }
    JavaStaticField::JavaStaticField(const JavaStaticField& field) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        
        this->clazz = field.clazz;
        this->name = field.name;
        this->sign = field.sign;
        this->field = field.field;
    }

    JValue JavaStaticField::accessOn(const JavaObject& obj) const {
        throw UnsupportedOperationException("Unable to access a JavaStaticField from a JavaObject.");
    }
    void JavaStaticField::editOn(JavaObject& obj, const jvalue& value) {
        throw UnsupportedOperationException("Unable to edit a JavaStaticField from a JavaObject.");
    }

    JValue JavaStaticField::access() const {
        return this->clazz->access(*this);
    }
    void JavaStaticField::edit(const jvalue& value) const {
        this->clazz->edit(*this, value);
    }
}