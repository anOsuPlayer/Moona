#include "../moona.hpp"

namespace moona {

    Double::Double() : Decimal<double>() {
    }

    Double::Double(double value) : Decimal<double>(value) {
    }

    Double::~Double() {
    }
}