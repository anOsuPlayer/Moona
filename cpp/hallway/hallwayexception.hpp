#pragma once

#include "../base/moonaclass.hpp"
#include "../exceptions/exception.hpp"

namespace moona {

    class HallwayAccessException : public Exception {
        public:
            explicit HallwayAccessException();
            explicit HallwayAccessException(const char* message);
            ~HallwayAccessException();
    };
}