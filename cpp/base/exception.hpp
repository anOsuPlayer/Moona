#pragma once

#ifndef MOONA_EXCEPTION_TYPE
    #define MOONA_EXCEPTION_TYPE

    #include <string_view>
    #include <exception>

    #include "../base/entity.hpp"

    namespace moona {

        class Exception : public Entity<Exception>, public std::exception {
            private:
                const char* message;

            public:
                Exception();
                Exception(const char* message);
                ~Exception();

                virtual const char* what() const noexcept override final;
        };
    }

#endif