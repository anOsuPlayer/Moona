#include "conditional.hpp"

namespace moona {

    Conditional::Conditional() {
    }

    Conditional::Conditional(const bool& value) {
        this->value = value;
    }

    Conditional::~Conditional() {
    }

    void Conditional::reverse() const noexcept {
        this->value = !value;
    }
    Conditional Conditional::opposite() const noexcept {
        return Conditional(this->value);
    }
}