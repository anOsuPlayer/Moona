#pragma once

#ifndef MOONA_BAD_CAST_EXCEPTION
    #define MOONA_BAD_CAST_EXCEPTION

    #include "exception.hpp"
    #include "../base/object.hpp"

    namespace moona {

        class BadCastException : public Object<BadCastException>, public Exception {
            public:
                BadCastException() = delete;
                explicit BadCastException(const char* message);
        };
    }

#endif