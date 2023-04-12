#pragma once

#include <jni.h>

#include "hallwayexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/notation.hpp"
#include "../base/entity.hpp"

namespace moona {

    class Signature {
        private:
            const char* sign;
            explicit Signature(const char* sign);

        public:
            static const Signature INT;

            Signature() = delete;
            Signature(const Signature& s);
    };

    class MethodSignature : public Entity<MethodSignature> {
        private:
            const char* signature;

        public:

    };
}