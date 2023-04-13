#pragma once

#include <jni.h>
#include <string.h>

#include "hallwayexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/notation.hpp"
#include "../base/entity.hpp"

namespace moona {

    class PureSignature {
        protected:
            char* signature;
            PureSignature() = default;
            explicit PureSignature(const char* signature);
        
        public:
            PureSignature(const PureSignature& ps);
            virtual ~PureSignature();

            operator const char*() const noexcept;
            const char* getSignature() const noexcept;
    };

    class Signature : public PureSignature {
        protected:
            explicit Signature(const char* signature);

        public:
            static const Signature BOOLEAN;
            static const Signature BYTE;
            static const Signature SHORT;
            static const Signature INT;
            static const Signature LONG;
            static const Signature FLOAT;
            static const Signature DOUBLE;

            static const Signature V0ID;

            Signature() = delete;
            Signature(const Signature& s);
            virtual ~Signature() = default;
    };

    class ObjectSignature : public PureSignature {
        public:
            ObjectSignature() = delete;
            ObjectSignature(const char* obj);
            ObjectSignature(const ObjectSignature& os);
            virtual ~ObjectSignature() = default;
    };

    class ArraySignature : public PureSignature {
        public:
            static const ArraySignature BOOLEAN_ARRAY;
            static const ArraySignature BYTE_ARRAY;
            static const ArraySignature SHORT_ARRAY;
            static const ArraySignature INT_ARRAY;
            static const ArraySignature LONG_ARRAY;
            static const ArraySignature FLOAT_ARRAY;
            static const ArraySignature DOUBLE_ARRAY;

            ArraySignature() = delete;
            ArraySignature(const char* obj);
            ArraySignature(const PureSignature& base);
            virtual ~ArraySignature() = default;
    };

    class MethodSignature : public Entity<MethodSignature> {
        private:
            char* signature;

        public:
            MethodSignature() = delete;
            MethodSignature(const PureSignature& returntype);
            MethodSignature(const PureSignature& returntype, unsigned int argc, const PureSignature* args);
            ~MethodSignature();

            operator const char*() const noexcept;
            const char* getSignature() const noexcept;
    };
}