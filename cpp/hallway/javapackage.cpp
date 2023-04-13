#include "javapackage.hpp"

namespace moona {

    JavaPackage::JavaPackage(const char* location) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        this->location = location;
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
}