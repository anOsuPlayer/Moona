#pragma once

#include "../exceptions/exception.hpp"

namespace moona {

    class NoSuchMethodException : public Exception {
        public:
            NoSuchMethodException();
            explicit NoSuchMethodException(const char* message);
    };
}