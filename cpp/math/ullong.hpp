#pragma once

#ifndef MOONA_UNSIGNED_LLONG_NUMBER
    #define MOONA_UNSIGNED_LLONG_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class UnsignedLonger : public Integral<unsigned long long int> {
            public:
                UnsignedLonger();
                UnsignedLonger(unsigned long long int value) ;
                template <Numeral N> UnsignedLonger(const Number<N>& value);
                ~UnsignedLonger();
        };
    }

#endif