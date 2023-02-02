#pragma once

#ifndef MOONA_ENTITY
    #define MOONA_ENTITY

    #include "type.hpp"

    namespace moona {

        template <typename E> class Entity {
            protected:
                Entity() {
                }
                ~Entity() {
                }

            public:
                Type<E>* type() const {
                    return new Type<E>(); 
                }

                template <typename T> constexpr bool instanceof() const {
                    return std::is_base_of<T, E>() || std::is_same<T, E>();
                }
        };
    }

#endif