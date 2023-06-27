#include "twinclass.hpp"

namespace moona {

    TwinClass::TwinClass(const char* clazz) : JavaClass(clazz) {
        JavaClass twinnable("moonaframework.hallway.twin.Twinnable");
        if (!this->extends(twinnable)) {
            throw IllegalArgumentException("A TwinClass can only be initialized from a JavaClass implementing the Twinnable Interface.");
        }
    }
    TwinClass::TwinClass(const jclass& clazz) : JavaClass(clazz) {
        JavaClass twinnable("moonaframework.hallway.twin.Twinnable");
        if (!this->extends(twinnable)) {
            throw IllegalArgumentException("A TwinClass can only be initialized from a JavaClass implementing the Twinnable Interface.");
        }
    }
    TwinClass::TwinClass(const JavaClass& clazz) : JavaClass(clazz) {
        JavaClass twinnable("moonaframework.hallway.twin.Twinnable");
        if (!this->extends(twinnable)) {
            throw IllegalArgumentException("A TwinClass can only be initialized from a JavaClass implementing the Twinnable Interface.");
        }
    } 
    TwinClass::TwinClass(const TwinClass& tc) : JavaClass(tc) {
        JavaClass twinnable("moonaframework.hallway.twin.Twinnable");
        if (!this->extends(twinnable)) {
            throw IllegalArgumentException("A TwinClass can only be initialized from a JavaClass implementing the Twinnable Interface.");
        }
    }
}