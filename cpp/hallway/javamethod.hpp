#pragma once

#include <jni.h>

#include "hallwayexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaMethod : public Object<JavaMethod> {
        private:
            const char* name;
            jmethodID method;
    };
}