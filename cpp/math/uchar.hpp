#pragma once

#ifndef MOONA__UNSIGNED_CHAR_NUMBER
    #define MOONA_UNSIGNED_CHAR_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class UnsignedChar : public Integral<unsigned char> {
            public:
                UnsignedChar();
                UnsignedChar(unsigned char value) ;
                template <Numeral N> UnsignedChar(const Number<N>& value);
                ~UnsignedChar();
        };
    }

#endif