#include "../moona.hpp"

namespace moona {

    UnsignedInteger::UnsignedInteger() : Integral<unsigned int>() {
    }

    UnsignedInteger::UnsignedInteger(unsigned int value) : Integral<unsigned int>(value) {
    }

    template <Numeral N> UnsignedInteger::UnsignedInteger(const Number<N>& value) : Integral<unsigned int>(value) {
    }

    UnsignedInteger::~UnsignedInteger() {
    }
}