#pragma once

#ifndef MOONA_UNSIGNED_SHORT_NUMBER
    #define MOONA_UNSIGNED_SHORT_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class UnsignedShort : public Integral<unsigned short int> {
            public:
                UnsignedShort();
                UnsignedShort(signed short int value) ;
                template <Numeral N> UnsignedShort(const Number<N>& value) : Integral<unsigned short int>(value) {
                }
                ~UnsignedShort();
        };
    }

#endif