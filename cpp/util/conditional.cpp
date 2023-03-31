#include "conditional.hpp"

namespace moona {

    Conditional::Conditional() : Property<bool>(false) {
    }

    Conditional::Conditional(bool value) : Property<bool>(value) {
    }

    void Conditional::reverse() const noexcept {
        this->value = !value;
    }
    Conditional Conditional::opposite() const noexcept {
        return Conditional(this->value);
    }
}