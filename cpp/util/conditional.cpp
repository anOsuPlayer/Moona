#include "conditional.hpp"

namespace moona {

    Conditional::Conditional() {
    }

    Conditional::Conditional(const bool& value) {
        this->value = value;
    }

    Conditional::~Conditional() {
    }

    void Conditional::reverse() {
        this->value = !value;
    }
    Conditional Conditional::opposite() const {
        return Conditional(this->value);
    }
}