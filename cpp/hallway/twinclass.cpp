#include "twinclass.hpp"

namespace moona {

    TwinClass::TwinClass(const char* clazz) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->clazz = JavaClass(clazz);
        JavaClass twinnable = JavaClass("moonaframework.hallway.twin.Twinnable");

        if (!this->clazz.extends(twinnable)) {
            throw IllegalArgumentException("A TwinClass can only be initialized from a JavaClass implementing the Twinnable Interface.");
        }
    }
    TwinClass::TwinClass(const jclass& clazz) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        this->clazz = clazz;
        JavaClass twinnable = JavaClass("moonaframework.hallway.twin.Twinnable");
        
        if (!this->clazz.extends(twinnable)) {
            throw IllegalArgumentException("A TwinClass can only be initialized from a JavaClass implementing the Twinnable Interface.");
        }
    }
    TwinClass::TwinClass(const JavaClass& clazz) {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }
        
        this->clazz = clazz;
        JavaClass twinnable = JavaClass("moonaframework.hallway.twin.Twinnable");
        
        if (!this->clazz.extends(twinnable)) {
            throw IllegalArgumentException("A TwinClass can only be initialized from a JavaClass implementing the Twinnable Interface.");
        }
    } 
    TwinClass::TwinClass(const TwinClass& tc) {
        this->clazz = tc.clazz;
    }

    TwinClass& TwinClass::operator = (const TwinClass& tc) noexcept {
        this->clazz = tc.clazz;
        return *this;
    }

    bool TwinClass::operator == (const TwinClass& tc) const noexcept {
        return this->clazz.equals(tc.clazz);
    }

    const char* TwinClass::toString() const noexcept {
        return this->clazz.toString();
    }
    bool TwinClass::equals(const TwinClass& tc) const noexcept {
        return this->clazz.equals(tc.clazz);
    }
}