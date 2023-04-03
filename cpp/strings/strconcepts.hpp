#pragma once

#include <concepts>

namespace moona {

    template <typename C> concept CharacterType = requires {
        std::is_same<char, C>() || std::is_same<wchar_t, C>(); 
    };
}