#pragma once

#ifndef MOONA_UNSIGNED_INTEGER_NUMBER
    #define MOONA_UNSIGNED_INTEGER_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class UnsignedInt : public Integral<unsigned int> {
            public:
                UnsignedInt();
                UnsignedInt(unsigned int value);
                template <Numeral N> UnsignedInt(const Number<N>& value) : Integral<unsigned int>(value) {
                }
                ~UnsignedInt();
        };
    }

#endif