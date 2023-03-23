#include "jvm.hpp"
#include <iostream>

namespace moona {

    JVM::JVM() {
    }

    JVM::~JVM() {
    }

    void JVM::loadJVMLibraries() {
        HMODULE dll = LoadLibrary(_T("C:\\Program Files\\Java\\jdk-19.0.1\\bin\\server\\jvm.dll"));
        JVM::jvmbuilder = (__jvmbuilder*)GetProcAddress(dll, "JNI_CreateJavaVM");
        JVM::jvmfinder = (__jvmfinder*)GetProcAddress(dll, "JNI_GetCreatedJavaVMs");
        FreeLibrary(dll);
    }

    void JVM::buildJVM() {
        JavaVMInitArgs args;
        JavaVMOption* opts = new JavaVMOption[1];
        opts[0].optionString = (char*)"-Djava.class.path=../java/bin";
        args.version = JNI_VERSION_19;
        args.nOptions = 1;
        args.options = opts;
        args.ignoreUnrecognized = false;

        std::cout << "AAAAAAAAAA";

        jint rc = jvmbuilder(&this->jvm, (void**)&this->env, &args);

        std::cout << rc;
    }

    void JVM::destroyJVM() {
        jvm->DestroyJavaVM();
    }

    bool JVM::isBuilt() const {
        return this->jvm != nullptr;
    }
}