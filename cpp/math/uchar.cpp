#include "../moona.hpp"

namespace moona {

    UnsignedChar::UnsignedChar() : Integral<unsigned char>() {
    }

    UnsignedChar::UnsignedChar(unsigned char value) : Integral<unsigned char>(value) {
    }

    UnsignedChar::~UnsignedChar() {
    }
}