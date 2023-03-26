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

    void Moona::commonInit() {
        Moona::isOn = true;
    }

    void Moona::init() {
        Moona::commonInit();

        if (Moona::initializeJavaVM) {
            JVM::loadJVMLibraries();
            Moona::jvm = new JVM();
            Moona::jvm->buildJVM();
        }
    }
    void Moona::jinit(JNIEnv* env) {
        Moona::commonInit();
        
        Moona::jvm = new JVM(env);
    }

    bool Moona::isJVMinitialized() {
        return Moona::jvm != nullptr;
    }
}