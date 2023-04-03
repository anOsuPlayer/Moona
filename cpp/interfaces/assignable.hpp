#pragma once

#include "../base/notation.hpp"
#include "../base/entity.hpp"

namespace moona {

    template <typename T> class Assignable : public Entity<Assignable<T>> {
        protected:
            Assignable() = default;
            ~Assignable() = default;

        public:
            virtual const Assignable<T>& operator = (T ref) noexcept abstract;
    };
}