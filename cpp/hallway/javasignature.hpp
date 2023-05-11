#pragma once

#include <jni.h>
#include <string.h>

#include "hallwayexception.hpp"
#include "../base/moonaclass.hpp"
#include "../base/notation.hpp"
#include "../base/entity.hpp"
#include "../exceptions/illegalexception.hpp"

namespace moona {

    class PureSignature {
        protected:
            char* signature;
            PureSignature() = default;
            explicit PureSignature(const char* signature);
        
        public:
            PureSignature(const PureSignature& ps);
            virtual ~PureSignature();

            virtual PureSignature& operator = (const PureSignature& ps2) noexcept;
            virtual bool operator == (const PureSignature& ps2) const noexcept;

            virtual PureSignature& concat(const PureSignature& ps) noexcept;
            virtual PureSignature& operator + (const PureSignature& ps) noexcept final;
            virtual PureSignature& operator += (const PureSignature& ps) noexcept final;

            operator const char*() const noexcept;
            const char* getSignature() const noexcept;

        friend class ArraySignature;
    };

    class Signature : public PureSignature {
        protected:
            explicit Signature(const char* signature);

        public:
            Signature() = delete;
            Signature(const Signature& s);
            virtual ~Signature() = default;

            static const Signature BOOLEAN;
            static const Signature BYTE;
            static const Signature CHAR;
            static const Signature SHORT;
            static const Signature INT;
            static const Signature LONG;
            static const Signature FLOAT;
            static const Signature DOUBLE;

            static const Signature V0ID;
    };

    class ObjectSignature : public PureSignature {
        public:
            ObjectSignature() = delete;
            ObjectSignature(const char* obj);
            ObjectSignature(const ObjectSignature& os);
            virtual ~ObjectSignature() = default;

            static const ObjectSignature STRING;
    };

    class ArraySignature : public PureSignature {
        public:
            ArraySignature() = delete;
            ArraySignature(const char* obj, size_t order = 1);
            ArraySignature(const PureSignature& base, size_t order = 1);
            virtual ~ArraySignature() = default;

            static const ArraySignature BOOLEAN_ARRAY;
            static const ArraySignature BYTE_ARRAY;
            static const ArraySignature CHAR_ARRAY;
            static const ArraySignature SHORT_ARRAY;
            static const ArraySignature INT_ARRAY;
            static const ArraySignature LONG_ARRAY;
            static const ArraySignature FLOAT_ARRAY;
            static const ArraySignature DOUBLE_ARRAY;

            static const ArraySignature STRING_ARRAY;

            PureSignature getBaseSignature() const noexcept;
    };

    class MethodSignature : public Entity<MethodSignature> {
        private:
            char* signature;

        public:
            MethodSignature() = default;
            MethodSignature(const PureSignature& returntype);
            MethodSignature(const PureSignature& returntype, const PureSignature& args);
            MethodSignature(const MethodSignature& ms);
            ~MethodSignature();

            static const MethodSignature BOOLEAN_METHOD;
            static const MethodSignature BYTE_METHOD;
            static const MethodSignature SHORT_METHOD;
            static const MethodSignature CHAR_METHOD;
            static const MethodSignature INT_METHOD;
            static const MethodSignature LONG_METHOD;
            static const MethodSignature FLOAT_METHOD;
            static const MethodSignature DOUBLE_METHOD;
            
            static const MethodSignature STRING_METHOD;

            virtual MethodSignature& operator = (const MethodSignature& ms) noexcept;
            virtual bool operator == (const MethodSignature& ms) const noexcept;

            operator const char*() const noexcept;
            const char* getSignature() const noexcept;

            const PureSignature returnType() const noexcept;
        
        friend class ConstructorSignature;
    };

    class ConstructorSignature : public Entity<ConstructorSignature>, public MethodSignature {
        public:
            ConstructorSignature();
            ConstructorSignature(const PureSignature& args);
            ConstructorSignature(const ConstructorSignature& cs);
            ~ConstructorSignature() = default;

            static const ConstructorSignature DEFAULT;
    };

    class FieldSignature : public Entity<FieldSignature> {
        private:
            char* signature;

        public:
            FieldSignature() = default;
            FieldSignature(const PureSignature& type);
            FieldSignature(const FieldSignature& fs);
            ~FieldSignature();

            static const FieldSignature BOOLEAN_FIELD;
            static const FieldSignature BYTE_FIELD;
            static const FieldSignature SHORT_FIELD;
            static const FieldSignature CHAR_FIELD;
            static const FieldSignature INT_FIELD;
            static const FieldSignature LONG_FIELD;
            static const FieldSignature FLOAT_FIELD;
            static const FieldSignature DOUBLE_FIELD;

            static const FieldSignature STRING_FIELD;

            virtual FieldSignature& operator = (const FieldSignature& ms) noexcept;
            virtual bool operator == (const FieldSignature& ms) const noexcept;

            operator const char*() const noexcept;
            const char* getSignature() const noexcept;
    };
}