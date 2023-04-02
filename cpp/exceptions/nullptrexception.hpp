#pragma once

#ifndef MOONA_NULLPTR_EXCEPTION
    #define MOONA_NULLPTR_EXCEPTION

    #include "exception.hpp"
    #include "../base/object.hpp"

    namespace moona {

        class NullPointerException : public Object<NullPointerException>, public Exception {
            public:
                NullPointerException() = delete;
                explicit NullPointerException(const char* message);
        };
    }

#endif