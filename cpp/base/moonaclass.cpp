#include "moonaclass.hpp"

#include <cstdint>

namespace moona {

    JavaImpl void Java_moonaframework_base_Moona_nativeInit(StaticArgs) {
        if (!Moona::isOn()) {
            Moona::jinit(env);
        }
    }

    void Moona::commonInit() {
        Moona::on = true;
    }

    void Moona::init() {
        if (!Moona::on) {
            Moona::commonInit();

            std::atexit(Moona::finalize);

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
            throw MoonaHandlingException("Moona::init() method can only be invoked once.");
        }
    }
    void Moona::jinit(JNIEnv* env) {
        if (!Moona::on) {
            Moona::commonInit();
            
            if (Moona::jvm == nullptr) {
                Moona::jvm = new JVM(env);
            }
        }
        else {
            throw MoonaHandlingException("Moona::init() method can only be invoked once.");
        }
    }

    void Moona::finalize() noexcept {
        if (jvm != nullptr) {
            delete jvm;
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
}