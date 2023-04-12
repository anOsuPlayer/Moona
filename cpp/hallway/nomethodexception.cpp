#include "nomethodexception.hpp"

namespace moona {

    NoSuchMethodException::NoSuchMethodException() : Exception("No such method exists.") {
    }

    NoSuchMethodException::NoSuchMethodException(const char* message) : Exception(message) {
    }
}