#pragma once

#ifndef MOONA
    #define MOONA

    #include <iostream>
    #include <concepts>
    #include <string>
    #include <sstream>
    #include <any>

    #include "base/base.hpp"
    #include "math/math.hpp"

    namespace moona {

        template <typename O> concept MoonaObject = std::is_base_of<Object<O>, O>::value;
    }

#endif