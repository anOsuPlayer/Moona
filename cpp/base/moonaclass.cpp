#include "moonaclass.hpp"

#include <cstdint>

namespace moona {

    JavaImpl void Java_moonaframework_base_Moona_nativeInit(JNIEnv* env, jclass clazz) {
        Moona::jinit(env);
    }

    Moona::Moona() {
    }

    Moona::~Moona() {
    }

    void Moona::init() {
        Moona::isOn = true;

        if (Moona::initializeJavaVM) {
            JVM::loadJVMLibraries();
            Moona::jvm = new JVM();
            Moona::jvm->buildJVM();
        }
    }
    void Moona::jinit(JNIEnv* env) {
        Moona::isOn = true;

        Moona::jvm = new JVM(env);
    }

    bool Moona::isJVMinitialized() {
        return Moona::jvm != nullptr;
    }
}