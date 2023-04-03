#pragma once

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