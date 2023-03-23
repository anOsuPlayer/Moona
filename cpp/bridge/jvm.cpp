#include "jvm.hpp"

namespace moona {

    JVM::JVM() {
    }
    JVM::JVM(JNIEnv* env) {
        this->env = env;
    }

    JVM::~JVM() {
        if (JVM::jvm != nullptr) {
            JVM::destroyJVM();
        }
        if (JVM::dll != nullptr) {
            FreeLibrary(JVM::dll);
        }
    }

    void JVM::loadJVMLibraries() {
        JVM::dll = LoadLibrary(_T("C:\\Program Files\\Java\\jdk-19.0.1\\bin\\server\\jvm.dll"));
        JVM::jvmbuilder = (_jvmbuilder*)GetProcAddress(JVM::dll, "JNI_CreateJavaVM");
        JVM::jvmfinder = (_jvmfinder*)GetProcAddress(JVM::dll, "JNI_GetCreatedJavaVMs");
    }

    void JVM::buildJVM() {
        if (JVM::dll == nullptr) {
            JVM::loadJVMLibraries();
        }
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
        if (jvm != nullptr) {
            jvm->DestroyJavaVM();
        }
    }

    bool JVM::isBuilt() const {
        return this->env != nullptr;
    }
}