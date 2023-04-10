#include "javapackage.hpp"

namespace moona {

    JavaPackage::JavaPackage(const char* location) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        this->location = location;
    }
    
    const char* JavaPackage::toString() const noexcept {
        return this->location;
    }
}