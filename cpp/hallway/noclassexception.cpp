#include "noclassexception.hpp"

namespace moona {

    NoSuchClassException::NoSuchClassException() : Exception("No such class exists.") {
    }

    NoSuchClassException::NoSuchClassException(const char* message) : Exception(message) {
    }
}