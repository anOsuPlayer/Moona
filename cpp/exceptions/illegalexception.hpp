#pragma once

#ifndef MOONA_ILLEGAL_ARGUMENT_EXCEPTION
    #define MOONA_ILLEGAL_ARGUMENT_EXCEPTION

    #include "exception.hpp"
    #include "../base/object.hpp"

    namespace moona {

        class IllegalArgumentException : public Object<IllegalArgumentException>, public Exception {
            public:
                IllegalArgumentException() = delete;
                explicit IllegalArgumentException(const char* message);
        };
    }

#endif