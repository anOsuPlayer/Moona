#pragma once

#include "../exceptions/exception.hpp"

namespace moona {

    class NoSuchFieldException : public Exception {
        public:
            NoSuchFieldException();
            explicit NoSuchFieldException(const char* message);
    };
}