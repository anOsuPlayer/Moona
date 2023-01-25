#pragma once

#ifndef MOONA_ENTITY
    #define MOONA_ENTITY

    #ifndef MOONA
        #include <iostream>
        #include <concepts>
    #endif

    namespace moona {

        template <typename E> class Entity {
            private:
                Entity() {
                };
                ~Entity() {
                };

            public:
                template <typename T> bool instanceof() const {
                    return std::is_assignable<T, E>() || std::is_same<T, E>();
                }

                unsigned short int getSize() const {
                    return sizeof(E);
                }

            template <typename O> friend class Object;
            template <Numeral N> friend class Number;
        };
    }

#endif