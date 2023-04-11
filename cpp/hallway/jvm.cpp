#include "jvm.hpp"
#include <iostream>

namespace moona {

    JVM::JVM(JNIEnv* env) {
        this->JNIStatus = JNI_OK;
        this->env = env;
    }

    JVM::~JVM() {
        JVM::destroyJVM();
    }

    void JVM::loadJVMLibraries() {
        if (JVM::source == nullptr) {
            JVM::source = LoadLibrary(_T("C:\\Program Files\\Java\\jdk-19.0.1\\bin\\server\\jvm.dll"));
            JVM::jvmbuilder = (_jvmbuilder*)GetProcAddress(JVM::source, "JNI_CreateJavaVM");
            JVM::jvmfinder = (_jvmfinder*)GetProcAddress(JVM::source, "JNI_GetCreatedJavaVMs");
        }
    }
    void JVM::unloadJVMLibraries() {
        if (JVM::source != nullptr) {
            FreeLibrary(JVM::source);
        }
    }

    void JVM::buildJVM() {
        if (JVM::source == nullptr) {
            JVM::loadJVMLibraries();
        }
        JavaVMInitArgs args;
        JavaVMOption* opts = new JavaVMOption[1];
        opts[0].optionString = (char*)"-Djava.class.path=../java/bin";
        args.version = JNI_VERSION_19;
        args.nOptions = 1;
        args.options = opts;
        args.ignoreUnrecognized = false;

        this->JNIStatus = jvmbuilder(&this->jvm, (void**)&this->env, &args);
        delete opts;
    }
    void JVM::destroyJVM() {
        if (this->jvm != nullptr) {
            jvm->DestroyJavaVM();
        }
    }

    JavaVM& JVM::getJavaVM() const noexcept {
        return *this->jvm;
    }
    JNIEnv& JVM::getJNIEnv() const noexcept {
        return *this->env;
    }

    bool JVM::isSafe() const noexcept {
        return this->JNIStatus == JNI_OK;
    }
    bool JVM::isBuilt() const noexcept {
        return this->env != nullptr;
    }

    bool JVM::isLoaded() const noexcept {
        return this->source != nullptr;
    }
}