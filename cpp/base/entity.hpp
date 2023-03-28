#pragma once

#ifndef MOONA_ENTITY
    #define MOONA_ENTITY

    #include "type.hpp"

    namespace moona {

        template <typename B> class Base {
            private:
                Base() {
                }
                ~Base() {
                }
            
            template <typename E, typename... super> friend class Entity;
        };

        template <typename E, typename... super> class Entity : public Base<E>, super... {
            protected:
                Entity() {
                }
                ~Entity() {
                }

            public:
                constexpr Type<E, super...>* type() const noexcept {
                    return new Type<E, super...>();
                }

                template <typename T> constexpr bool instanceof() const noexcept {
                    return std::is_base_of<T, E>() || std::is_same<T, E>();
                }

                constexpr const unsigned short int thisSize() const noexcept {
                    return sizeof(*this);
                }
        };
    }

#endif