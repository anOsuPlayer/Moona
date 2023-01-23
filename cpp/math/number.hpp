#pragma once

#ifndef NUMBER_OBJECT
    #define NUMBER_OBJECT

    #include "../moona.hpp"

    namespace moona {

        template <typename N> concept Numeral = std::is_integral<N>::value || std::is_floating_point<N>::value;
        
        template <Numeral N> class Number : public Object<Number<N>> {
            private:
                N value = 0;

            public:
                Number(N value) {
                    this->value = value;
                }
                ~Number() {
                }

                virtual std::string toString() const {
                    return std::to_string(value);
                }

                virtual bool equals(const Number<N>* n2) const override {
                    return (n2 == nullptr && this == nullptr) || (this->value == n2->value);
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