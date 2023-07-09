#include "twinclass.hpp"

namespace moona {

    TwinClass::TwinClass() {
        this->pattern = new int[0];
    }

    TwinClass::TwinClass(const char* clazz) : JavaClass(clazz) {
        JavaClass twinnable("moonaframework.hallway.twin.Twinnable");
        if (!this->extends(twinnable)) {
            throw IllegalArgumentException("A TwinClass can only be initialized from a JavaClass implementing the Twinnable Interface.");
        }
        this->setupPattern();
    }
    TwinClass::TwinClass(const jclass& clazz) : JavaClass(clazz) {
        JavaClass twinnable("moonaframework.hallway.twin.Twinnable");
        if (!this->extends(twinnable)) {
            throw IllegalArgumentException("A TwinClass can only be initialized from a JavaClass implementing the Twinnable Interface.");
        }
        this->setupPattern();
    }
    TwinClass::TwinClass(const JavaClass& clazz) : JavaClass(clazz) {
        JavaClass twinnable("moonaframework.hallway.twin.Twinnable");
        if (!this->extends(twinnable)) {
            throw IllegalArgumentException("A TwinClass can only be initialized from a JavaClass implementing the Twinnable Interface.");
        }
        this->setupPattern();
    } 
    TwinClass::TwinClass(const TwinClass& tc) : JavaClass(tc) {
        JavaClass twinnable("moonaframework.hallway.twin.Twinnable");
        if (!this->extends(twinnable)) {
            throw IllegalArgumentException("A TwinClass can only be initialized from a JavaClass implementing the Twinnable Interface.");
        }
        this->setupPattern();
    }

    TwinClass::~TwinClass() {
        delete[] this->pattern;
    }

    void TwinClass::setupPattern() noexcept {
        JavaMethod pattern = this->getMethod("pattern", ArraySignature::INT_ARRAY);
        JavaIntArray arr = (jintArray) ((jobject) pattern.callOn(Moona::defaultJNIEnv().AllocObject(this->clazz)));
        
        this->pattern = new int[arr.length()];
        for (size_t i = 0; i < arr.length(); i++) {
            this->pattern[i] = arr[i];
        }
    }

    const int* TwinClass::getPattern() const noexcept {
        return this->pattern;
    }
}