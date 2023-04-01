#pragma once

#ifndef MOONA_COMPARABLE_INTERFACE
    #define MOONA_COMPARABLE_INTERFACE

    #include <compare>

    #include "deducible.hpp"
    #include "../base/entity.hpp"
    #include "../base/notation.hpp"

    namespace moona {

        class Comparable : public Entity<Comparable> {
            protected:
                Comparable() = default;
                ~Comparable() = default;

            public:
                virtual std::strong_ordering operator <=> (const Comparable& other) const abstract;
        };
    }

#endif