#include "exception.hpp"

namespace moona {

    Exception::Exception(const char* message) {
        this->message = message;
    }

    Exception::~Exception() {
    }

    const char* Exception::what() const noexcept {
        return this->message;
    }
}