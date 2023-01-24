#pragma once

#ifndef NUMBER_OBJECT
    #define NUMBER_OBJECT

    #include "../moona.hpp"

    namespace moona {
        
        template <Numeral N> class Number : public Entity<Number<N>> {
            protected:
                N value = 0;

            public:
                Number() {
                    this->value = 0;
                }
                Number(N value) {
                    this->value = value;
                }
                ~Number() {
                }

                friend std::ostream& operator << (std::ostream& os, const Number<N>& n) {
                    os << n.value;
                    return os;
                }

                Number<N> operator + (const Number<N>& n2) const {
                    Number<N> n;
                    n.value = this->value + n2.value;
                    return n;
                }
                Number<N> operator += (const Number<N>& n2) {
                    this->value += n2.value;
                    return *this;
                }
                Number<N>& operator ++ () {
                    this->value++;
                    return *this;
                }
                Number<N> operator ++ (int) {
                    Number<N> temp = *this;
                    ++*this;
                    return temp;
                }

                Number<N> operator - (const Number<N>& n2) const {
                    Number<N> n;
                    n.value = this->value - n2.value;
                    return n;
                }
                Number<N> operator -= (const Number<N>& n2) {
                    this->value -= n2.value;
                    return *this;
                }
                Number<N>& operator -- () {
                    this->value--;
                    return *this;
                }
                Number<N> operator -- (int) {
                    Number<N> temp = *this;
                    --*this;
                    return temp;
                }

                Number<N> operator * (const Number<N>& n2) const {
                    Number<N> n;
                    n.value = this->value * n2.value;
                    return n;
                }
                Number<N> operator *= (const Number<N>& n2) {
                    this->value *= n2.value;
                    return *this;
                }
                
                Number<N> operator / (const Number<N>& n2) const {
                    Number<N> n;
                    n.value = this->value / n2.value;
                    return n;
                }
                Number<N> operator /= (const Number<N>& n2) {
                    this->value /= n2.value;
                    return *this;
                }

                bool operator == (const Number<N>& n2) const {
                    return this->value == n2.value;
                }
                std::strong_ordering operator <=> (const Number<N>& n2) const {
                    return this->value <=> n2.value;
                }


        };
    }

#endif