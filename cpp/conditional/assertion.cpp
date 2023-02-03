#include "assertion.hpp"

namespace moona {

    Assertion::Assertion() {
    }

    Assertion::~Assertion() {
    }

    Assertion::operator bool() const {
        return this->value;
    }
}