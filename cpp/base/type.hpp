#pragma once

#ifndef MOONA_TYPE
    #define MOONA_TYPE

    #include <typeinfo>

    namespace moona {

        template <typename T> class Type {
            public:
                Type() {
                }
                ~Type() {
                }

                constexpr const char* name() const {
                    return typeid(T).name();
                }

                constexpr unsigned short int size() const {
                    return sizeof(T);
                }
        };
    }

#endif