#pragma once

#ifndef MOONA_LONG_NUMBER
    #define MOONA_LONG_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class Long : public Integral<signed long int> {
            public:
                Long();
                Long(signed long int value) ;
                template <Numeral N> Long(const Number<N>& value) : Integral<signed long int>(value) {
                }
                ~Long();
        };
    }

#endif