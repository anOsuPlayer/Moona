#include "../moona.hpp"

namespace moona {

    Longer::Longer() : Integral<signed long long int>() {
    }

    Longer::Longer(signed long long int value) : Integral<signed long long int>(value) {
    }

    template <Numeral N> Longer::Longer(const Number<N>& value) : Integral<signed long long int>(value) {
    }

    Longer::~Longer() {
    }
}