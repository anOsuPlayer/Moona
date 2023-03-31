#pragma once

#ifndef MOONA_ASSIGNABLE_INTERFACE
    #define MOONA_ASSIGNABLE_INTERFACE

    #include "../base/notation.hpp"

    namespace moona {

        template <typename T> class Assignable {
            protected:
                Assignable() = default;
                ~Assignable() = default;

            public:
                virtual const Assignable<T>& operator = (const T& ref) const noexcept abstract;
        };
    }

#endif