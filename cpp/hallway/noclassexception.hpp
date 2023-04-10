#pragma once

#include "../exceptions/exception.hpp"

namespace moona {

    class NoSuchClassException : public Exception {
        public:
            NoSuchClassException();
            explicit NoSuchClassException(const char* message);
    };
}