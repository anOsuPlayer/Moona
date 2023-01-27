#include "../moona.hpp"

namespace moona {

    Assertion::Assertion() {
    }

    Assertion::~Assertion() {
    }

    const char* Assertion::toString() const {
        const char* r = "Assertion";
        return r;
    }
    bool Assertion::equals(const Assertion& ass) const {
        return (this->getType() == ass.getType());
    }
}