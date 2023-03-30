#pragma once

#ifndef MOONA_ILLEGAL_ARGUMENT_EXCEPTION
    #define MOONA_ILLEGAL_ARGUMENT_EXCEPTION

    #include "exception.hpp"

    namespace moona {

        class IllegalArgumentException : public Exception {
            public:
                IllegalArgumentException() = delete;
                explicit IllegalArgumentException(const char* message);
        };
    }

#endif