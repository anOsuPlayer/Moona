#pragma once

#ifndef MOONA_UNSIGNED_INTEGER_NUMBER
    #define MOONA_UNSIGNED_INTEGER_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class UnsignedInteger : public Integral<unsigned int> {
            public:
                UnsignedInteger();
                UnsignedInteger(unsigned int value) ;
                template <Numeral N> UnsignedInteger(const Number<N>& value);
                ~UnsignedInteger();
        };
    }

#endif