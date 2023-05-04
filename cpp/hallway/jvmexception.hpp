#pragma once

#include "../base/moonaclass.hpp"
#include "../exceptions/exception.hpp"

namespace moona {

    class JVMException : public Exception {
        public:
            explicit JVMException();
            explicit JVMException(const char* message);
    };
}