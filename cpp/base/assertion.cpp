#include "../moona.hpp"

namespace moona {

    Assertion::Assertion() {
    }

    Assertion::~Assertion() {
    }

    BasicString Assertion::toString() const {
        BasicString s = "Assertion";
        return s;
    }
    bool Assertion::equals(const Assertion& ass) const {
        return (this->getType() == ass.getType());
    }
}