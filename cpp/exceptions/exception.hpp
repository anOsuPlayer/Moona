#pragma once

#include <exception>

namespace moona {

    class Exception : public std::exception {
        private:
            const char* message;

        public:
            explicit Exception() = delete;
            explicit Exception(const char* message);
            ~Exception() = default;

            virtual const char* what() const noexcept override final;
    };
}