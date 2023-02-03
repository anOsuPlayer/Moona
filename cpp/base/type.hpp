#pragma once

#ifndef MOONA_TYPE
    #define MOONA_TYPE

    #include <type_traits>

    namespace moona {
        
        template <typename T, typename... super> class Type {
            public:
                Type() {
                }
                ~Type() {
                }

                constexpr const char* name() const {
                    return typeid(T).name();
                }

                constexpr const unsigned short int size() const {
                    return sizeof(T);
                }
        };
    }

#endif