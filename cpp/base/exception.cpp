#include "exception.hpp"

namespace moona {

    Exception::Exception() {
        this->message = "An exception occurred.";
    }

    Exception::Exception(const char* message) {
        this->message = message;
    }

    Exception::~Exception() {

    }

    const char* Exception::what() const noexcept {
        return this->message;
    }
}