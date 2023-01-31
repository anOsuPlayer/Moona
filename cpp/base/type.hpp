#pragma once

#ifndef MOONA_TYPE
    #define MOONA_TYPE

    namespace moona {

        template <typename T> class Type {
            private:
                Type() {
                }
                ~Type() {
                }

            public:
                constexpr unsigned short int size() {
                    return sizeof(T);
                }

            template <typename E> friend class Entity;
        };
    }

#endif