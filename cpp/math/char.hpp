#pragma once

#ifndef MOONA_CHAR_NUMBER
    #define MOONA_CHAR_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class Char : public Integral<signed char> {
            public:
                Char();
                Char(signed char value);
                template <Numeral N> Char(const Number<N>& value) : Integral<signed char>(value) {
                }
                ~Char();
        };
    }

#endif