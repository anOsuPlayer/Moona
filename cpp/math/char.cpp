#include "../moona.hpp"

namespace moona {

    Char::Char() : Integral<signed char>() {
    }

    Char::Char(signed char value) : Integral<signed char>(value) {
    }

    template <Numeral N> Char::Char(const Number<N>& value) : Integral<signed char>(value) {
    }

    Char::~Char() {
    }
}