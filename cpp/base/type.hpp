#pragma once

#ifndef MOONA_TYPE
    #define MOONA_TYPE

    #include <type_traits>
    #include <typeinfo>

    namespace moona {
        
        template <typename T, typename... super> class Type {
            public:
                explicit Type() {
                }
                ~Type() {
                }

                constexpr const char* name() const noexcept {
                    return typeid(T).name();
                }

                constexpr const unsigned short int typeSize() const noexcept {
                    return sizeof(T);
                }
        };
    }

#endif