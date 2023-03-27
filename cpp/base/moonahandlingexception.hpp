#pragma once

#ifndef MOONA_HANDLING_EXCEPTION
    #define MOONA_HANDLING_EXCEPTION

    #include "exception.hpp"

    namespace moona {

        class MoonaHandlingException : public Exception {
            public:
                MoonaHandlingException();
                MoonaHandlingException(const char* message);
                ~MoonaHandlingException();
        };
    }

#endif