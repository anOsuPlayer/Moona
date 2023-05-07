#include "javaconstructor.hpp"

namespace moona {

    JavaConstructor::JavaConstructor(const JavaClass& clazz, const ConstructorSignature& cs) : JavaMethod("<init>", clazz, cs) {
    }

    JavaConstructor::JavaConstructor(const JavaClass& clazz) : JavaMethod("<init>", clazz, ConstructorSignature::STANDARD) {
    }

    JavaConstructor::JavaConstructor(const JavaConstructor& con) : JavaMethod(con) {
    }
}