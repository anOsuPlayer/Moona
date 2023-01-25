#pragma once

#ifndef MOONA_OBJECT
    #define MOONA_OBJECT

    #include "../moona.hpp"

    namespace moona {

        template <typename O> class Object {
            public:
                Object() {
                };
                ~Object() {
                };

                friend std::ostream& operator << (std::ostream& os, const Object& e) {
                    os << "Object-" << &e;
                    return os;
                }

                template <typename T> bool instanceof() const {
                    return std::is_assignable<T, O>() || std::is_same<T, O>();
                }

                unsigned short int getSize() const {
                    return sizeof(O);
                }
        };
    }

#endif