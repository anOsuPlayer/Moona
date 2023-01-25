#include "../moona.hpp"

namespace moona {

    Integer::Integer() : Integral<signed int>() {
    }

    Integer::Integer(signed int value) : Integral<signed int>(value) {
    }

    template <Numeral N> Integer::Integer(const Number<N>& value) : Integral<signed int>(value) {
    }

    Integer::~Integer() {
    }
}