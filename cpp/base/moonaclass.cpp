#include "moonaclass.hpp"

#include <cstdint>

namespace moona {

    JavaImpl void Java_moonaframework_base_Moona_nativeInit(JNIEnv* env, jclass clazz) {
        Moona::jinit(env);
    }

    void Moona::commonInit() {
        Moona::isOn = true;
    }

    void Moona::init() {
        if (!Moona::isOn) {
            Moona::commonInit();

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
        if (!Moona::isOn) {
            Moona::commonInit();
            
            if (Moona::jvm == nullptr) {
                Moona::jvm = new JVM(env);
            }
        }
        else {
            throw MoonaHandlingException("Moona::init() method can only be invoked once.");
        }
    }

    bool Moona::isJVMinitialized() noexcept {
        return Moona::jvm != nullptr;
    }
}