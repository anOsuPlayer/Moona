#pragma once

#ifndef MOONA_INTEGER_NUMBER
    #define MOONA_INTEGER_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class Int : public Integral<signed int> {
            public:
                Int();
                Int(signed int value);
                template <Numeral N> Int(const Number<N>& value) : Integral<signed int>(value) {
                }
                ~Int();
        };
    }

#endif