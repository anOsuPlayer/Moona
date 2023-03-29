#include "illegalexception.hpp"

namespace moona {

    IllegalArgumentException::IllegalArgumentException(const char* message) : Exception(message) {
    }
}