#pragma once

#ifndef DECIMAL_NUMBER_OBJECT
    #define DECIMAL_NUMBER_OBJECT

    #include "../moona.hpp"

    namespace moona {

        template <typename D> concept Decimal = std::is_floating_point<D>::value;
    }

#endif