#include "javapackage.hpp"

namespace moona {

    JavaPackage::JavaPackage(const char* location) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        
        size_t len = strlen(location);
        this->location = new char[len+1]; this->location[len] = '\0';
        for (size_t i = 0; i < len; i++) {
            this->location[i] = location[i];
        }
    }
    JavaPackage::JavaPackage(const JavaPackage& pack) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        
        size_t len = strlen(pack.location);
        this->location = new char[len+1]; this->location[len] = '\0';
        for (size_t i = 0; i < len; i++) {
            this->location[i] = pack.location[i];
        }
    }

    JavaPackage& JavaPackage::operator = (const JavaPackage& other) noexcept {
        if (this->location != nullptr) {
            delete[] this->location;
        }

        size_t len = strlen(other.location);
        this->location = new char[len+1]; this->location[len] = '\0';
        for (size_t i = 0; i < len; i++) {
            this->location[i] = other.location[i];
        }

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