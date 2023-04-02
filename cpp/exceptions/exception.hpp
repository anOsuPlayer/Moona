#pragma once

#ifndef MOONA_EXCEPTION_TYPE
    #define MOONA_EXCEPTION_TYPE

    #include <string_view>
    #include <exception>

    #include "../base/object.hpp"
    #include "../interfaces/printable.hpp"

    namespace moona {

        class Exception : public Object<Exception>, public std::exception {
            private:
                const char* message;

            public:
                explicit Exception() = delete;
                explicit Exception(const char* message);
                ~Exception() = default;

                virtual const char* what() const noexcept override final;
                virtual const char* toString() const noexcept override;
        };
    }

#endif