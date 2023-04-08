#pragma once

#include "exception.hpp"

namespace moona {

    class BadCastException : public Exception {
        public:
            BadCastException() = delete;
            explicit BadCastException(const char* message);
    };
}