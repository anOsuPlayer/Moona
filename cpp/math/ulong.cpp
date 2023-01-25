#include "../moona.hpp"

namespace moona {

    UnsignedLong::UnsignedLong() : Integral<unsigned long int>() {
    }

    UnsignedLong::UnsignedLong(unsigned long int value) : Integral<unsigned long int>(value) {
    }

    UnsignedLong::~UnsignedLong() {
    }
}