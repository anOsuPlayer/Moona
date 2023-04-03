#pragma once

#include "exception.hpp"
#include "../base/object.hpp"

namespace moona {

    class IllegalArgumentException : public Object<IllegalArgumentException>, public Exception {
        public:
            IllegalArgumentException() = delete;
            explicit IllegalArgumentException(const char* message);
    };
}