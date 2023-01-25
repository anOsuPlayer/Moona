#pragma once

#ifndef MOONA_INTEGER_NUMBER
    #define MOONA_INTEGER_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class Integer : public Integral<signed int> {
            public:
                Integer();
                Integer(signed int value) ;
                template <Numeral N> Integer(const Number<N>& value);
                ~Integer();
        };
    }

#endif