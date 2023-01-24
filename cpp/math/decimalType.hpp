#pragma once

#ifndef DECIMAL_NUMBER_OBJECT
    #define DECIMAL_NUMBER_OBJECT

    #include "../moona.hpp"

    namespace moona {

        template <DecimalNumber D> class Decimal : public Number<D> {
            public:
                Decimal(D value) : Number<D>(value) {
                }
                ~Decimal() {
                }
        };

        typedef Decimal<float> Float;

        typedef Decimal<double> Double;
        typedef Decimal<long double> LongDouble;
    }

#endif