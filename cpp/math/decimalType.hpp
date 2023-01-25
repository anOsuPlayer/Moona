#pragma once

#ifndef DECIMAL_NUMBER_OBJECT
    #define DECIMAL_NUMBER_OBJECT

    #include "../moona.hpp"

    namespace moona {

        template <DecimalNumber D> class Decimal : public Number<D> {
            public:
                Decimal() : Number<D>() {
                }
                Decimal(D value) : Number<D>(value) {
                }
                ~Decimal() {
                }

                Decimal<D> operator << (const unsigned int& n2) const {
                    Decimal<D> n;
                    n.value = this->value / std::pow(10, n2);
                    return n;
                }
                Decimal<D> operator <<= (const unsigned int& n2) {
                    this->value /= std::pow(10, n2);
                    return *this;
                }

                Decimal<D> operator >> (const unsigned int& n2) const {
                    Decimal<D> n;
                    n.value = this->value * std::pow(10, n2);
                    return n;
                }
                Decimal<D> operator >>= (const unsigned int& n2) {
                    this->value *= std::pow(10, n2);
                    return *this;
                }

                Decimal<D> operator ^ (const Decimal<D>& exp) const {
                    Decimal<D> n;
                    n.value = std::pow(this->value, exp);
                    return n;
                }
                Decimal<D> operator ^= (const Decimal<D>& exp) {
                    this->value = std::pow(this->value, exp);
                    return *this;
                }
        };

        typedef Decimal<float> Float;

        typedef Decimal<double> Double;
        typedef Decimal<long double> LongDouble;
    }

#endif