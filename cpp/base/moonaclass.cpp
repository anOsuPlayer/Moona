#include "moonaclass.hpp"

#include <cstdint>

namespace moona {

    JavaImpl void Java_moonaframework_base_Moona_nativeInit(StaticArgs) {
        Moona::enableHallwayAccess.enable();
        
        if (!Moona::isOn()) {
            Moona::jinit(env);
        }
    }

    JavaImpl void Java_moonaframework_base_Moona_nativeInterrupt(StaticArgs) {
        if (Moona::isOn()) {
            Moona::interrupt();
        }
    }

    void Moona::commonInit() {
        Moona::on = true;
        Moona::wasOn = true;
    }

    void Moona::init() {
        if (!Moona::on) {
            if (Moona::wasOn) {
                throw MoonaHandlingException("Cannot invoke Moona::init() function after invoking Moona::interrupt().");
            }
            Moona::commonInit();

            std::atexit(Moona::interrupt);

            if (Moona::enableHallwayAccess) {
                JVM::loadJVMLibraries();
                DefaultJVM = new JVM();
                DefaultJVM->buildJVM();

                jclass moonaclass = DefaultENV->FindClass("moonaframework/base/Moona");
                jmethodID id = DefaultENV->GetStaticMethodID(moonaclass, "init", "()V");
                DefaultENV->CallStaticVoidMethod(moonaclass, id);
            }
        }
        else {
            throw MoonaHandlingException("Moona::init() function can only be invoked once.");
        }
    }
    void Moona::jinit(JNIEnv* env) {
        if (!Moona::on) {
            if (Moona::wasOn) {
                throw MoonaHandlingException("Cannot invoke Moona::init() function after invoking Moona::interrupt().");
            }
            Moona::commonInit();
            
            if (Moona::jvm == nullptr) {
                Moona::jvm = new JVM(env);
            }
        }
        else {
            throw MoonaHandlingException("Moona::init() method can only be invoked once.");
        }
    }

    void Moona::interrupt() {
        if (!Moona::on) {
            throw MoonaHandlingException("Moona cannot be interrupted if not previously started.");
        }
        if (jvm != nullptr) {
            delete jvm;
            JVM::unloadJVMLibraries();
        }
    }

    bool Moona::isOn() noexcept {
        return Moona::on;
    }

    bool Moona::isJVMinitialized() noexcept {
        return Moona::jvm != nullptr;
    }

    const JVM& Moona::getMoonaJVM() {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        return *Moona::jvm;
    }
    JNIEnv& Moona::defaultJNIEnv() {
        if (!Moona::enableHallwayAccess) {
            throw HallwayAccessException();
        }

        return Moona::jvm->getJNIEnv();
    }
}