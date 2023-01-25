#pragma once

#ifndef MOONA_FLOAT_NUMBER
    #define MOONA_FLOAT_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class Float : public Decimal<float> {
            public:
                Float();
                Float(float value);
                template <Numeral N> Float(const Number<N>& value) : Decimal<float>(value) {
                }
                ~Float();
        };
    }

#endif