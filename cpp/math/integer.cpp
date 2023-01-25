#include "../moona.hpp"

namespace moona {

    Int::Int() : Integral<signed int>() {
    }

    Int::Int(signed int value) : Integral<signed int>(value) {
    }

    Int::~Int() {
    }
}