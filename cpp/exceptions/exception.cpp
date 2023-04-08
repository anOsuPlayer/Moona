#include "exception.hpp"

namespace moona {

    Exception::Exception(const char* message) {
        this->message = message;
    }

    const char* Exception::what() const noexcept {
        return this->message;
    }
}