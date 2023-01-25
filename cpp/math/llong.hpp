#pragma once

#ifndef MOONA_LLONG_NUMBER
    #define MOONA_LLONG_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class Longer : public Integral<signed long long int> {
            public:
                Longer();
                Longer(signed long long int value) ;
                template <Numeral N> Longer(const Number<N>& value);
                ~Longer();
        };
    }

#endif