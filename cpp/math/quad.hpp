#pragma once

#ifndef MOONA_QUAD_FLOAT_NUMBER
    #define MOONA_QUAD_FLOAT_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class Quad : public Decimal<long double> {
            public:
                Quad();
                Quad(long double value);
                template <Numeral N> Quad(const Number<N>& value) : Decimal<long double>(value) {
                }
                ~Quad();
        };
    }

#endif