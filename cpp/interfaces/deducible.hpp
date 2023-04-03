#pragma once

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