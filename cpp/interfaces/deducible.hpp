#pragma once

#ifndef MOONA_DEDUCIBLE_INTERFACE
    #define MOONA_DEDUCIBLE_INTERFACE

    #include "../base/notation.hpp"
    #include "../base/entity.hpp"

    namespace moona {

        template <typename T> class Deducible : public Entity<Deducible<T>> {
            protected:
                Deducible() = default;
                ~Deducible() = default;

            public:
                virtual operator T() const noexcept abstract;
        };
    }

#endif