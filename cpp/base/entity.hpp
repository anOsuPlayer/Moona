#pragma once

#ifndef MOONA_ENTITY
    #define MOONA_ENTITY

    #include "../moona.hpp"

    namespace moona {

        template <typename E> class Entity {
            private:
                Entity() {
                };
                ~Entity() {
                };

            public:
                friend std::ostream& operator << (std::ostream& os, const Entity& e) {
                    os << "Object-" << &e;
                    return os;
                }

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