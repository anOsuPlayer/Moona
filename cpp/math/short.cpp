#include "../moona.hpp"

namespace moona {

    Short::Short() : Integral<signed short int>() {
    }

    Short::Short(signed short int value) : Integral<signed short int>(value) {
    }

    Short::~Short() {
    }
}