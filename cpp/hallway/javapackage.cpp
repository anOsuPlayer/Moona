#include "javapackage.hpp"

namespace moona {

    JavaPackage::JavaPackage(const char* location) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        this->location = location;
    }
    JavaPackage::JavaPackage(const JavaPackage& pack) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        this->location = pack.location;
    }

    JavaPackage& JavaPackage::operator = (const JavaPackage& other) noexcept {
        this->location = other.location;

        return *this;
    }
    bool JavaPackage::operator == (const JavaPackage& other) const noexcept {
        return (strcmp(this->location, other.location) == 0);
    }
    
    JavaClass JavaPackage::getClass(const char* classname) const {
        if (classname == nullptr) {
            throw NullPointerException("Unable to find a Java Class from a nullptr.");
        }
        return JavaClass(*this, classname);
    }

    const char* JavaPackage::toString() const noexcept {
        return this->location;
    }
    bool JavaPackage::equals(const JavaPackage& other) const noexcept {
        return (strcmp(this->location, other.location) == 0);
    }
}