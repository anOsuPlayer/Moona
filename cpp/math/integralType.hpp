#pragma once

#ifndef MOONA_INTEGRAL_NUMBER_OBJECT
    #define MOONA_INTEGRAL_NUMBER_OBJECT

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
    }

#endif