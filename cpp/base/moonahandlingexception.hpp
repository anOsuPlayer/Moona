#pragma once

#include "../exceptions/exception.hpp"

namespace moona {

    class MoonaHandlingException : public Exception {
        public:
            explicit MoonaHandlingException();
            explicit MoonaHandlingException(const char* message);
            ~MoonaHandlingException();
    };
}
