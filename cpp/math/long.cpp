#include "../moona.hpp"

namespace moona {

    Long::Long() : Integral<signed long int>() {
    }

    Long::Long(signed long int value) : Integral<signed long int>(value) {
    }

    Long::~Long() {
    }
}