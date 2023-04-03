#pragma once

#include "exception.hpp"
#include "../base/object.hpp"

namespace moona {

    class BadCastException : public Object<BadCastException>, public Exception {
        public:
            BadCastException() = delete;
            explicit BadCastException(const char* message);
    };
}