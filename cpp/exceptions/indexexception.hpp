#pragma once

#include "exception.hpp"

namespace moona {

    class IndexOutOfBoundsException : public Exception {
        public:
            IndexOutOfBoundsException() = delete;
            explicit IndexOutOfBoundsException(const char* message);
    };
}