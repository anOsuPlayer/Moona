#pragma once

#ifndef INTEGRAL_NUMBER_OBJECT
    #define INTEGRAL_NUMBER_OBJECT

    #include "../moona.hpp"

    namespace moona {
        
        template <typename I> concept Integral = std::is_integral<I>::value;
    }

#endif