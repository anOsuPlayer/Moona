#pragma once

#ifndef MOONA_NULLPTR_EXCEPTION
    #define MOONA_NULLPTR_EXCEPTION

    #include "exception.hpp"

    namespace moona {

        class NullPointerException : public Exception {
            public:
                NullPointerException() = delete;
                explicit NullPointerException(const char* message);
        };
    }

#endif