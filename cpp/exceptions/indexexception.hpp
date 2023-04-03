#pragma once

#include "exception.hpp"
#include "../base/object.hpp"

namespace moona {

    class IndexOutOfBoundsException : public Object<IndexOutOfBoundsException>, public Exception {
        public:
            IndexOutOfBoundsException() = delete;
            explicit IndexOutOfBoundsException(const char* message);
    };
}