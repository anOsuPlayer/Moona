#pragma once

#ifndef MOONA_ENTITY
    #define MOONA_ENTITY

    #include <iostream>

    #include "type.hpp"

    namespace moona {

        template <typename E> class Entity {
            private:
                Entity() {
                }
                ~Entity() {
                }

            public:
                constexpr Type<E>* type() const {
                    return new Type<E>();
                }

                template <typename T> constexpr bool instanceof() const {
                    return std::is_base_of<T, E>() || std::is_same<T, E>();
                }

            template <typename T> friend class Object;
        };
    }

#endif