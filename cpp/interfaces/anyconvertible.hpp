#pragma once

#ifndef MOONA_ANY_CONVERTIBLE_INTERFACE
    #define MOONA_ANY_CONVERTIBLE_INTERFACE

    #include "deducible.hpp"
    #include "../base/notation.hpp"
    #include "../base/entity.hpp"
    #include "../util/any.hpp"

    namespace moona {

        template <template <typename> typename T> class AnyConvertible : public Deducible<T<Any>> {
            protected:
                AnyConvertible() = default;
                ~AnyConvertible() = default;
        };
    }

#endif