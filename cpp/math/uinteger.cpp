#include "../moona.hpp"

namespace moona {

    UnsignedInt::UnsignedInt() : Integral<unsigned int>() {
    }

    UnsignedInt::UnsignedInt(unsigned int value) : Integral<unsigned int>(value) {
    }

    UnsignedInt::~UnsignedInt() {
    }
}