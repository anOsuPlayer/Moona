#pragma once

#include "exception.hpp"

namespace moona {

    class UnsupportedOperationException : public Exception {
        public:
            UnsupportedOperationException() = delete;
            explicit UnsupportedOperationException(const char* message);
    };
}