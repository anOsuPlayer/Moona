#include "../moona.hpp"

namespace moona {

    Quad::Quad() : Decimal<long double>() {
    }

    Quad::Quad(long double value) : Decimal<long double>(value) {
    }

    Quad::~Quad() {
    }
}