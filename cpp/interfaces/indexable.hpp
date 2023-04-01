#pragma once

#ifndef MOONA_INDEXABLE_INTERFACE
    #define MOONA_INDEXABLE_INTERFACE

    #include "../base/entity.hpp"
    #include "../base/notation.hpp"

    namespace moona {

        template <typename T, typename I> class Indexable : Entity<Indexable<T, I>> {
            protected:
                Indexable() = default;
                ~Indexable() = default;

            public:
                virtual T operator [] (I index) const abstract;
        };
    }

#endif