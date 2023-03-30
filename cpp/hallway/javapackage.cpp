#include "javapackage.hpp"

namespace moona {

    JavaPackage::JavaPackage(const char* package) {
        if (!Moona::isOn() || !Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        if (package == nullptr) {
            throw NullPointerException("Unable to link a Java Package to a nullptr.");
        }

        this->package = package;
    }

    const char* JavaPackage::toString() const noexcept {
        return this->package;
    }
}