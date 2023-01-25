#pragma once

#ifndef MOONA_UNSIGNED_LONG_NUMBER
    #define MOONA_UNSIGNED_LONG_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class UnsignedLong : public Integral<unsigned long int> {
            public:
                UnsignedLong();
                UnsignedLong(unsigned long int value) ;
                template <Numeral N> UnsignedLong(const Number<N>& value) : Integral<unsigned long int>(value) {
                }
                ~UnsignedLong();
        };
    }

#endif