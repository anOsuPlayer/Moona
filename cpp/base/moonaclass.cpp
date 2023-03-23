#include "moonaclass.hpp"
#include <iostream>

namespace moona {

    extern "C" {
        JNIEXPORT void JNICALL Java_moonaframework_base_Moona_nativeInit(JNIEnv* env, jclass clazz) {
            #define MOONA_JAVA_APPROACH
            Moona::init();
        }
    }

    Moona::Moona() {
    }

    Moona::~Moona() {
    }

    void Moona::init() {
        JVM::loadJVMLibraries();
        Moona::jvm = new JVM();
        Moona::jvm->buildJVM();
    }

    void Moona::preMain() {

    }

    void Moona::postMain() {
        Moona::jvm->~JVM();
    }
}