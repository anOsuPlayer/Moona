#include "nullptrexception.hpp"

namespace moona {

    NullPointerException::NullPointerException(const char* message) : Exception(message) {
    }
}