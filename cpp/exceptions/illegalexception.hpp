#pragma once

#include "exception.hpp"

namespace moona {

    class IllegalArgumentException : public Exception {
        public:
            IllegalArgumentException() = delete;
            explicit IllegalArgumentException(const char* message);
    };
}