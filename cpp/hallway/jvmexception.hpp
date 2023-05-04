#pragma once

#include "../base/moonaclass.hpp"
#include "../exceptions/exception.hpp"

namespace moona {

    class JVMException : public Exception {
        public:
            JVMException();
            JVMException(const char* message);
    };
}