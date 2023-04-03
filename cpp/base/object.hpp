#pragma once

#include "entity.hpp"
#include "type.hpp"
#include "../interfaces/printable.hpp"
#include "../interfaces/cloneable.hpp"
#include "../interfaces/equalable.hpp"

namespace moona {

    template <typename O, typename... super> class Object : public Entity<O>, public Printable, public Cloneable, public Equalable {
        public:
            Object() = default;
            ~Object() = default;

            virtual constexpr const Type<O, super...> type() const noexcept final {
                return Type<O, super...>();
            }

            virtual Object<O, super...>* clone() const noexcept override {
                return new Object<O, super...>();
            }

            virtual const char* toString() const noexcept override {
                return this->type().name();
            }

            virtual bool equals(const Equalable& obj2) const noexcept override {
                return this == &obj2;
            }
    };
}