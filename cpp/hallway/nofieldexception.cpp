#include "nofieldexception.hpp"

namespace moona {

    NoSuchFieldException::NoSuchFieldException() : Exception("No such field exists.") {
    }

    NoSuchFieldException::NoSuchFieldException(const char* message) : Exception(message) {
    }
}