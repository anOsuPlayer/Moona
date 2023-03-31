#pragma once

#ifndef MOONA_DEDUCIBLE_INTERFACE
    #define MOONA_DEDUCIBLE_INTERFACE

    #include "../base/notation.hpp"

    namespace moona {

        template <typename T> class Deducible {
            protected:
                Deducible() = default;
                ~Deducible() = default;

            public:
                virtual operator T() const noexcept abstract;
        };
    }

#endif