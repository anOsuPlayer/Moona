#pragma once

#include "exception.hpp"

namespace moona {

    class NullPointerException : public Exception {
        public:
            NullPointerException() = delete;
            explicit NullPointerException(const char* message);
    };
}