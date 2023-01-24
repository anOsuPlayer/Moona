#pragma once

#ifndef MOONA
    #define MOONA

    #include <iostream>
    #include <concepts>
    #include <string>
    #include <string.h>
    #include <sstream>
    #include <any>

    namespace moona {
        
        template <typename I> concept IntegralNumber = std::is_integral<I>::value;
        template <typename D> concept DecimalNumber = std::is_floating_point<D>::value;

        template <typename N> concept Numeral = IntegralNumber<N> || DecimalNumber<N>;
    }

    #include "base/base.hpp"
    #include "math/math.hpp"

    namespace moona {

        template <typename O> concept MoonaObject = std::is_base_of<Object<O>, O>::value;
    }

#endif