#pragma once

#ifndef MOONA_HANDLING_EXCEPTION
    #define MOONA_HANDLING_EXCEPTION

    #include "../exceptions/exception.hpp"

    namespace moona {

        class MoonaHandlingException : public Exception {
            public:
                explicit MoonaHandlingException();
                explicit MoonaHandlingException(const char* message);
                ~MoonaHandlingException();
        };
    }

#endif