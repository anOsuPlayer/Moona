#pragma once

#include <jni.h>
#include <string.h>

#include "hallwayexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/notation.hpp"
#include "../base/entity.hpp"

namespace moona {

    class Signature {
        protected:
            mutable char* sign = nullptr;
            explicit Signature(const char* sign);

        public:
            static const Signature BOOLEAN;
            static const Signature BYTE;
            static const Signature SHORT;
            static const Signature INT;
            static const Signature LONG;
            static const Signature FLOAT;
            static const Signature DOUBLE;

            static const Signature V0ID;

            Signature() = default;
            Signature(const Signature& s);
            ~Signature();

            const char* get() const noexcept;
    };

    class ObjectSignature : public Signature {
        public:
            ObjectSignature() = delete;
            explicit ObjectSignature(const char* obj);
            ~ObjectSignature();
    };

    class ArraySignature : public Signature {
        public:
            ArraySignature() = delete;
            explicit ArraySignature(const Signature& s);
            explicit ArraySignature(const char* obj);
            ~ArraySignature();
    };

    class MethodSignature : public Entity<MethodSignature> {
        private:
            mutable char* signature = nullptr;

        public:
            MethodSignature() = delete;
            explicit MethodSignature(const Signature& returntype);
            explicit MethodSignature(const Signature& returntype, unsigned int argc, const Signature* args);
            ~MethodSignature();

            const char* get() const noexcept;
    };
}