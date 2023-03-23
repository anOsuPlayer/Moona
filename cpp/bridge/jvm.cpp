#include "jvm.hpp"
#include <iostream>

namespace moona {

    JVM::JVM() {
    }

    JVM::~JVM() {
        JVM::destroyJVM();
        FreeLibrary(JVM::dll);
    }

    void JVM::loadJVMLibraries() {
        JVM::dll = LoadLibrary(_T("C:\\Program Files\\Java\\jdk-19.0.1\\bin\\server\\jvm.dll"));
        JVM::jvmbuilder = (__jvmbuilder*)GetProcAddress(JVM::dll, "JNI_CreateJavaVM");
        JVM::jvmfinder = (__jvmfinder*)GetProcAddress(JVM::dll, "JNI_GetCreatedJavaVMs");
    }

    void JVM::buildJVM() {
        JavaVMInitArgs args;
        JavaVMOption* opts = new JavaVMOption[1];
        opts[0].optionString = (char*)"-Djava.class.path=../java/bin";
        args.version = JNI_VERSION_19;
        args.nOptions = 1;
        args.options = opts;
        args.ignoreUnrecognized = false;

        jint rc = jvmbuilder(&this->jvm, (void**)&this->env, &args);
    }

    void JVM::destroyJVM() {
        jvm->DestroyJavaVM();
    }

    bool JVM::isBuilt() const {
        return this->jvm != nullptr;
    }
}