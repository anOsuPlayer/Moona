#include "javamethodsignature.hpp"

namespace moona {

    Signature::Signature(const char* sign) {
        this->sign = sign;
    }

    Signature::Signature(const Signature& s) {
        this->sign = s.sign;
    }
}