#include "conditional.hpp"

namespace moona {

    Conditional::Conditional() {
    }

    Conditional::~Conditional() {
    }

    Conditional::operator bool() const {
        return this->value;
    }
}