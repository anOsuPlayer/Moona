#pragma once

#ifndef MOONA_SHORT_NUMBER
    #define MOONA_SHORT_NUMBER

    #include "../moona.hpp"

    namespace moona {

        class Short : public Integral<signed short int> {
            public:
                Short();
                Short(signed short int value) ;
                template <Numeral N> Short(const Number<N>& value);
                ~Short();
        };
    }

#endif