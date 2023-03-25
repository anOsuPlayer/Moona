#include "moonaclass.hpp"

namespace moona {

    JavaImpl void MoonaJava_base_Moona_nativeInit(JNIEnv* env, jclass clazz) {
        Moona::jinit(env);
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
    void Moona::jinit(JNIEnv* env) {
        Moona::jvm = new JVM(env);
    }
}