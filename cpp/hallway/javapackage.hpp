#pragma once

#include <jni.h>
#include <string.h>

#include "hallwayexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/object.hpp"

namespace moona {

    class JavaPackage : public Object<JavaPackage> {
        private:
            const char* location;

        public:
            JavaPackage() = delete;
            JavaPackage(const char* location);
            ~JavaPackage() = default;

            virtual const char* toString() const noexcept override final;
    };
}