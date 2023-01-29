#pragma once

#ifndef MOONA_NUMERIC_CONCEPTS
    #define MOONA_NUMERIC_CONCEPTS

    #include <concepts>

    namespace moona {
        
        template <typename N> concept Numeral = requires {
            std::is_arithmetic<N>();
        };

        template <typename I> concept IntegralType = requires {
            std::is_integral<I>();
        };

        template <typename D> concept DecimalType = requires {
            std::is_floating_point<D>();
        };
    }

#endif