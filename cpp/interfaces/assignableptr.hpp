#pragma once

#ifndef MOONA_POINTER_ASSIGNABLE
    #define MOONA_POINTER_ASSIGNABLE

    #include "../base/notation.hpp"

    namespace moona {

        template <typename T> class PointerAssignable {
            protected:
                PointerAssignable() = default;
                ~PointerAssignable() = default;

            public:
                virtual PointerAssignable<T> operator = (const T* ptr) const noexcept abstract;
        };
    }

#endif