#include "../moona.hpp"

namespace moona {

    Float::Float() : Decimal<float>() {
    }

    Float::Float(float value) : Decimal<float>(value) {
    }

    Float::~Float() {
    }
}