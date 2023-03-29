#include "indexexception.hpp"

namespace moona {

    IndexOutOfBoundsException::IndexOutOfBoundsException(const char* message) : Exception(message) {
    }
}