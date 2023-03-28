#include "moonahandlingexception.hpp"

namespace moona {
    
    MoonaHandlingException::MoonaHandlingException() : Exception("Something went wrong with Moona.") {
    }

    MoonaHandlingException::MoonaHandlingException(const char* message) : Exception(message) {
    }

    MoonaHandlingException::~MoonaHandlingException() {
    }
}