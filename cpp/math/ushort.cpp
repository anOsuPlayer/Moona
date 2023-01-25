#include "../moona.hpp"

namespace moona {

    UnsignedShort::UnsignedShort() : Integral<unsigned short int>() {
    }

    UnsignedShort::UnsignedShort(signed short int value) : Integral<unsigned short int>(value) {
    }

    UnsignedShort::~UnsignedShort() {
    }
}