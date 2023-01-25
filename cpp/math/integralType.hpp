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
                template <Numeral T> Integral (const Number<T>& value) : Number<I>(value) {
                }
                ~Integral() {
                }

                Integral<I> operator % (const Integral<I>& n2) const {
                    Integral<I> n;
                    n.value = this->value % n2.value;
                    return n;
                }
                Integral<I> operator %= (const Integral<I>& n2) {
                    this->value %= n2.value;
                    return *this;
                }

                Integral<I> operator & (const Integral<I>& n2) const {
                    Integral<I> n;
                    n.value = this->value & n2.value;
                    return n;
                }
                Integral<I> operator &= (const Integral<I>& n2) {
                    this->value &= n2.value;
                    return *this;
                }

                Integral<I> operator | (const Integral<I>& n2) const {
                    Integral<I> n;
                    n.value = this->value | n2.value;
                    return n;
                }
                Integral<I> operator |= (const Integral<I>& n2) {
                    this->value |= n2.value;
                    return *this;
                }

                Integral<I> operator ^ (const Integral<I>& n2) const {
                    Integral<I> n;
                    n.value = this->value ^ n2.value;
                    return n;
                }
                Integral<I> operator ^= (const Integral<I>& n2) {
                    this->value ^= n2.value;
                    return *this;
                }

                Integral<I> operator << (const Integral<I>& n2) const {
                    Integral<I> n;
                    n.value = this->value << n2.value;
                    return n;
                }
                Integral<I> operator <<= (const Integral<I>& n2) {
                    this->value <<= n2.value;
                    return *this;
                }

                Integral<I> operator >> (const Integral<I>& n2) const {
                    Integral<I> n;
                    n.value = this->value >> n2.value;
                    return n;
                }
                Integral<I> operator >>= (const Integral<I>& n2) {
                    this->value <<= n2.value;
                    return *this;
                }

                Integral<I> operator ! () {
                    this->value = !this->value;
                    return *this;
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