#pragma once

#ifndef MOONA_RESOURCES
    #define MOONA_RESOURCES

    #include <iostream>
    #include <concepts>
    #include <string>
    #include <cmath>
    #include <limits>
    #include <any>

    namespace moona {

        template <typename I> concept IntegralNumber = std::is_integral<I>::value;
        template <typename D> concept DecimalNumber = std::is_floating_point<D>::value;

        template <typename N> concept Numeral = IntegralNumber<N> || DecimalNumber<N>;
    }

#endif