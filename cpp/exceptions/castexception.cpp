#include "castexception.hpp"

namespace moona {

    BadCastException::BadCastException(const char* message) : Exception(message) {
    }
}