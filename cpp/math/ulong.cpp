#include "../moona.hpp"

namespace moona {

    UnsignedLong::UnsignedLong() : Integral<unsigned long int>() {
    }

    UnsignedLong::UnsignedLong(unsigned long int value) : Integral<unsigned long int>(value) {
    }

    template <Numeral N> UnsignedLong::UnsignedLong(const Number<N>& value) : Integral<unsigned long int>(value) {
    }

    UnsignedLong::~UnsignedLong() {
    }
}