#pragma once

#include <type_traits>
#include <typeinfo>

namespace moona {
    
    template <typename T, typename... super> class Type {
        public:
            explicit Type() = default;
            ~Type() = default;

            constexpr const char* name() const noexcept {
                return typeid(T).name();
            }

            constexpr const unsigned short int typeSize() const noexcept {
                return sizeof(T);
            }
    };
}