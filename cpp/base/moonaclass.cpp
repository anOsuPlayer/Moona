#include "moonaclass.hpp"
#include <iostream>

namespace moona {

    extern "C" {
        JNIEXPORT void JNICALL Java_moonaframework_base_Moona_nativeInit(JNIEnv* env, jclass clazz) {
            Moona::init();
        }
    }

    Moona::Moona() {
    }

    Moona::~Moona() {
    }

    void Moona::init() {

    }
}