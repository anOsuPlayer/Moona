#include "jvmexception.hpp"

namespace moona {

    JVMException::JVMException() : Exception("An Exception occurred in the JVM.") {
        Moona::defaultJNIEnv().ExceptionDescribe();
        Moona::interrupt();
    }

    JVMException::JVMException(const char* message) : Exception(message) {
        Moona::defaultJNIEnv().ExceptionDescribe();
        Moona::interrupt();
    }
}