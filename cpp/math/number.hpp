#pragma once

#ifndef MOONA_NUMBER_OBJECT
    #define MOONA_NUMBER_OBJECT

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
                template <typename T> Number(const Number<T>& value) {
                    this->value = static_cast<N>(value.get());
                }
                ~Number() {
                }

                N get() const {
                    return this->value;
                }

                friend std::ostream& operator << (std::ostream& os, const Number<N>& n) {
                    os << std::fixed << n.value;
                    return os;
                }

                Number& operator = (const Number& n2) {
                    this->value = n2.value;
                    return *this;
                }

                friend Number<N> operator + (Number<N> n1, const Number<N>& n2) {
                    n1.value += n2.value;
                    return n1;
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
                    Number<N> n = *this;
                    ++*this;
                    return n;
                }

                friend Number<N> operator - (Number<N> n1, const Number<N>& n2) {
                    n1.value -= n2.value;
                    return n1;
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
                    Number<N> n = *this;
                    --*this;
                    return n;
                }

                Number<N> operator - () const {
                    Number<N> n;
                    n.value = -this->value;
                    return n;
                }
                Number<N> operator ~ () const {
                    Number<N> n;
                    n.value = std::abs(this->value);
                    return n;
                }

                friend Number<N> operator * (Number<N> n1, const Number<N>& n2) {
                    n1.value *= n2.value;
                    return n1;
                }
                Number<N> operator *= (const Number<N>& n2) {
                    this->value *= n2.value;
                    return *this;
                }
                
                friend Number<N> operator / (Number<N> n1, const Number<N>& n2) {
                    n1.value /= n2.value;
                    return n1;
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

                template <Numeral T> operator Number<T>() {
                    Number<T> i(this->value);
                    return i;
                }
        };
    }

#endif