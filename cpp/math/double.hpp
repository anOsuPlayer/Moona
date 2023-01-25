#pragma once

#ifndef MOONA_DOUBLE_NUMBER
    #define MOONA_DOUBLE_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class Double : public Decimal<double> {
            public:
                Double();
                Double(double value);
                template <Numeral N> Double(const Number<N>& value) : Decimal<double>(value) {
                }
                ~Double();
        };
    }

#endif