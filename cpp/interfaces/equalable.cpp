#include "equalable.hpp"

namespace moona {

    bool Equalable::equals(const Equalable& eq) const noexcept {
        return this == &eq;
    }

    bool Equalable::operator == (const Equalable& eq) const noexcept {
        return this->equals(eq);
    }
}