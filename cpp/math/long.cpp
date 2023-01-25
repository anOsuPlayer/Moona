#include "../moona.hpp"

namespace moona {

    Long::Long() : Integral<signed long int>() {
    }

    Long::Long(signed long int value) : Integral<signed long int>(value) {
    }

    template <Numeral N> Long::Long(const Number<N>& value) : Integral<signed long int>(value) {
    }

    Long::~Long() {
    }
}