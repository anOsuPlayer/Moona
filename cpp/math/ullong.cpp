#include "../moona.hpp"

namespace moona {

    UnsignedLonger::UnsignedLonger() : Integral<unsigned long long int>() {
    }

    UnsignedLonger::UnsignedLonger(unsigned long long int value) : Integral<unsigned long long int>(value) {
    }

    template <Numeral N> UnsignedLonger::UnsignedLonger(const Number<N>& value) : Integral<unsigned long long int>(value) {
    }

    UnsignedLonger::~UnsignedLonger() {
    }
}