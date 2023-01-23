#pragma once

#ifndef INTEGRAL_NUMBER_OBJECT
    #define INTEGRAL_NUMBER_OBJECT

    namespace moona {
        
        template <typename I> concept Integral = std::is_integral<I>::value;
    }

#endif