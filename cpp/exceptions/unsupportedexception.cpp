#include "unsupportedexception.hpp"

namespace moona {

    UnsupportedOperationException::UnsupportedOperationException(const char* message) : Exception(message) {
    }
}