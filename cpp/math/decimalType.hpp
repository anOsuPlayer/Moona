#pragma once

#ifndef MOONA_DECIMAL_NUMBER_OBJECT
    #define MOONA_DECIMAL_NUMBER_OBJECT

    #include "../moona.hpp"

    namespace moona {

        template <DecimalNumber D> class Decimal : public Number<D> {
            public:
                Decimal() : Number<D>() {
                }
                Decimal(D value) : Number<D>(value) {
                }
                template <Numeral T> Decimal (const Number<T>& value) : Number<D>(value) {
                }
                ~Decimal() {
                }

                friend Decimal<D> operator << (Decimal<D> n, const unsigned int& n2) {
                    n.value /= std::pow(10, n2);
                    return n;
                }
                Decimal<D>& operator <<= (const unsigned int& n2) {
                    this->value /= std::pow(10, n2);
                    return *this;
                }

                friend Decimal<D> operator >> (Decimal<D> n, const unsigned int& n2) {
                    n.value *= std::pow(10, n2);
                    return n;
                }
                Decimal<D>& operator >>= (const unsigned int& n2) {
                    this->value *= std::pow(10, n2);
                    return *this;
                }

                friend Decimal<D> operator ^ (Decimal<D> n, const Decimal<D>& exp) {
                    n.value = std::pow(n->value, exp);
                    return n;
                }
                Decimal<D>& operator ^= (const Decimal<D>& exp) {
                    this->value = std::pow(this->value, exp);
                    return *this;
                }
        };
    }

#endif