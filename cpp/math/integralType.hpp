#pragma once

#ifndef INTEGRAL_NUMBER_OBJECT
    #define INTEGRAL_NUMBER_OBJECT

    #include "../moona.hpp"

    namespace moona {

        template <IntegralNumber I> class Integral : public Number<I> {
            public:
                Integral() : Number<I>() {
                }
                Integral(I value) : Number<I>(value) {
                }
                ~Integral() {
                }
        };

        typedef Integral<signed short int> Short;
        typedef Integral<unsigned short int> UnsignedShort;

        typedef Integral<signed char> Char;
        typedef Integral<unsigned char> UnsignedChar;

        typedef Integral<signed int> Int;
        typedef Integral<unsigned int> UnsignedInt;

        typedef Integral<signed long int> Long;
        typedef Integral<unsigned long int> UnsignedLong;

        typedef Integral<signed long long int> LLong;
        typedef Integral<unsigned long long int> UnsignedLLong;
    }

#endif