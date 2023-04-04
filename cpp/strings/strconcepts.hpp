#pragma once

#include <concepts>

#include "../util/any.hpp"

namespace moona {

    template <typename C> concept CharacterType = requires {
        std::is_same<char, C>() || std::is_same<wchar_t, C>();
    };
}