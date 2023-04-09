#pragma once

#include "entity.hpp"
#include "type.hpp"

namespace moona {

    template <typename O, typename... super> class Object : public Entity<O> {
        public:
            Object() = default;
            ~Object() = default;

            virtual constexpr const Type<O, super...> type() const noexcept final {
                return Type<O, super...>();
            }

            virtual Object<O, super...>* clone() const noexcept {
                return new Object<O, super...>();
            }

            virtual const char* toString() const noexcept {
                return this->type().name();
            }

            virtual bool equals(const Object<O, super...>& obj2) const noexcept {
                return this == &obj2;
            }
    };
}