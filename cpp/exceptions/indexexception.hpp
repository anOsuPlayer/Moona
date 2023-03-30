#pragma once

#ifndef MOONA_INDEX_OUT_OF_BOUNDS_EXCEPTION
    #define MOONA_INDEX_OUT_OF_BOUNDS_EXCEPTION

    #include "exception.hpp"

    namespace moona {

        class IndexOutOfBoundsException : public Exception {
            public:
                IndexOutOfBoundsException() = delete;
                explicit IndexOutOfBoundsException(const char* message);
        };
    }

#endif